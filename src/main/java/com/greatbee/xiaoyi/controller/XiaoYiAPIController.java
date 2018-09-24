package com.greatbee.xiaoyi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.util.DataUtil;
import com.greatbee.base.util.StringUtil;
import com.greatbee.core.bean.constant.DBMT;
import com.greatbee.core.bean.oi.Field;
import com.greatbee.core.bean.oi.OI;
import com.greatbee.core.db.RelationalDataManager;
import com.greatbee.core.manager.TYDriver;
import com.greatbee.core.util.SpringContextUtil;
import com.greatbee.procut.Response;
import com.greatbee.procut.TYController;
import com.greatbee.xiaoyi.bean.WeidianResponse;
import com.greatbee.xiaoyi.util.XiaoyiSQLAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/xiaoyi")
public class XiaoYiAPIController extends TYController {
    private static final Logger logger = Logger.getLogger(com.greatbee.product.controller.Index.class);
    @Autowired
    private TYDriver tyDriver;
    @Autowired
    private XiaoyiSQLAdapter xiaoyiSQLAdapter;

    /**
     * 用于接收微店推送的订单消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/weidian/message", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    WeidianResponse weidianMessage(HttpServletRequest request, HttpServletResponse response) throws DBException {

        RelationalDataManager dataManager = (RelationalDataManager) SpringContextUtil.getBean(DBMT.Mysql.getType());

        logger.info("[weidian][message_push]");

        String content = request.getParameter("content");
        if (StringUtil.isValid(content)) {
            JSONObject contentObject = JSONObject.parseObject(content);
            String type = contentObject.getString("type");
            String message = contentObject.getString("message");
            String orderSN = contentObject.getJSONObject("message").getString("order_id");
            logger.info("[weidian][message_push_body][" + type + "]" + message);

            try {
                //插入log数据
                xiaoyiSQLAdapter.updateQuery("insert into tb_weidian_order_log(`createDate`,`orderSN`,`message`,`status`) values (?,?,?,?)",
                        new Object[]{DataUtil.formatDateTime(new Date(System.currentTimeMillis())), orderSN, message, type});

            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (StringUtil.isValid(type)) {
                switch (type) {
                    case "weidian.order.non_payment":
                        //待付款（直接到账+担保交易）
                        try {
                            //创建订单
                            String buyerMobilephone = contentObject.getJSONObject("message").getString("user_phone");
                            Double amount = DataUtil.getDouble(contentObject.getJSONObject("message").getString("total"), 0);
                            Integer count = DataUtil.getInt(contentObject.getJSONObject("message").getString("quantity"), 0);

                            String createOrderQuery = "insert into `tb_order` (`name`,`description`,`createDate`,`buyerMobilephone`,`amount`,`orderStatus`,`orderStatusDesc`,`buyCount`,`serialNumber`,`partner`) values (?,?,?,?,?,?,?,?,?,?)";
                            Object[] createOrderParams = new Object[]{"合伙人订单", "来自合伙人微店订单", DataUtil.formatDateTime(new Date(System.currentTimeMillis())), buyerMobilephone, amount, 1, "下单未付款", count, orderSN, ""};
                            xiaoyiSQLAdapter.updateQuery(createOrderQuery, createOrderParams);
                            //创建订单明细
                            JSONArray itemJSONArray = contentObject.getJSONObject("message").getJSONArray("items");
                            if (itemJSONArray != null && itemJSONArray.size() > 0) {
                                for (int i = 0; i < itemJSONArray.size(); i++) {
                                    JSONObject itemObject = itemJSONArray.getJSONObject(i);
                                    String createOrderDetailQuery = "INSERT INTO `db_xiaoyi_partner`.`tb_order_detail` (`name`,`createDate`,`orderSerialNumber`,`count`,`price`,`itemSerialNumber`,`cover`) VALUES (?,?,?,?,?,?,?);";
                                    Object[] createOrderDetailParams = new Object[]{
                                            itemObject.getString("item_name"),
                                            DataUtil.formatDateTime(new Date(System.currentTimeMillis())),
                                            orderSN,
                                            itemObject.getInteger("quantity"),
                                            itemObject.getDouble("price"),
                                            itemObject.getString("merchant_code"),
                                            ""
                                    };
                                    xiaoyiSQLAdapter.updateQuery(createOrderDetailQuery, createOrderDetailParams);
                                }
                            }

                            String updateQuery = "update tb_order o set o.partner = (select serialNumber from tb_b_user_info e where e.mobilephone=?) where o.buyerMobilephone=?;";
                            Object[] updateParams = new Object[]{buyerMobilephone, buyerMobilephone};
                            xiaoyiSQLAdapter.updateQuery(updateQuery, updateParams);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "weidian.order.already_payment":
                        //已付款（直接到账）/已付款待发货（担保交易）
                        //更新订单状态
                        try {
                            String updateOrderQuery = "update `tb_order` set `orderStatus`=?,`orderStatusDesc`=?,`updateDate`=? where serialNumber=?;";
                            Object[] updateOrderParams = new Object[]{
                                    2,
                                    "已付款"
                                    , DataUtil.formatDateTime(new Date(System.currentTimeMillis())),
                                    orderSN
                            };

                            xiaoyiSQLAdapter.updateQuery(updateOrderQuery, updateOrderParams);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "weidian.order.delivered":
                        //已发货（担保交易）/已完成（直接到账+货到付款）
                        try {
                            String updateOrderQuery = "update `tb_order` set `orderStatus`=?,`orderStatusDesc`=?,`updateDate`=? where serialNumber=?;";
                            Object[] updateOrderParams = new Object[]{
                                    3,
                                    "已发货"
                                    , DataUtil.formatDateTime(new Date(System.currentTimeMillis())),
                                    orderSN
                            };
                            xiaoyiSQLAdapter.updateQuery(updateOrderQuery, updateOrderParams);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "weidian.order.confirm_eceipt":
                        //已确认收货（担保交易）
                        try {
                            String updateOrderQuery = "update `tb_order` set `orderStatus`=?,`orderStatusDesc`=?,`updateDate`=? where serialNumber=?;";
                            Object[] updateOrderParams = new Object[]{
                                    4,
                                    "已确认收货"
                                    , DataUtil.formatDateTime(new Date(System.currentTimeMillis())),
                                    orderSN
                            };
                            xiaoyiSQLAdapter.updateQuery(updateOrderQuery, updateOrderParams);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "weidian.order.finished":
                        //交易成功
                        try {
                            String updateOrderQuery = "update `tb_order` set `orderStatus`=?,`orderStatusDesc`=?,`updateDate`=? where serialNumber=?;";
                            Object[] updateOrderParams = new Object[]{
                                    0,
                                    "交易成功"
                                    , DataUtil.formatDateTime(new Date(System.currentTimeMillis())),
                                    orderSN
                            };
                            xiaoyiSQLAdapter.updateQuery(updateOrderQuery, updateOrderParams);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "weidian.order.refund_byweidian":
                        //微店退款中

                        break;
                    case "weidian.order.refund_suc":
                        //退款成功

                        break;
                }
            }
        }


        logger.info("[weidian][message_push_end]");
        return new WeidianResponse();
    }
}
