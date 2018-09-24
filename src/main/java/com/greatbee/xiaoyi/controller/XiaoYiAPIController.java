package com.greatbee.xiaoyi.controller;

import com.alibaba.fastjson.JSONObject;
import com.greatbee.procut.Response;
import com.greatbee.procut.TYController;
import com.greatbee.xiaoyi.bean.WeidianResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/xiaoyi")
public class XiaoYiAPIController extends TYController {
    private static final Logger logger = Logger.getLogger(com.greatbee.product.controller.Index.class);

    /**
     * 用于接收微店推送的订单消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/weidian/message", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    WeidianResponse weidianMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonParam) {
        logger.info("[weidian][message_push]");
        logger.info("[weidian][message_push_body]" + jsonParam.toJSONString());
        logger.info("[weidian][message_push_end]");
        return new WeidianResponse();
    }
}
