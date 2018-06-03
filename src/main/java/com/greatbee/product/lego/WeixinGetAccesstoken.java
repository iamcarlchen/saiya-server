package com.greatbee.product.lego;

import com.alibaba.fastjson.JSONObject;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.util.StringUtil;
import com.greatbee.core.ExceptionCode;
import com.greatbee.core.bean.view.RestApiResponse;
import com.greatbee.core.lego.Input;
import com.greatbee.core.lego.Lego;
import com.greatbee.core.lego.LegoException;
import com.greatbee.core.lego.Output;
import com.greatbee.core.util.HttpClientUtil;

import java.util.HashMap;

/**
 * 获取微信的accesstoken
 */
public class WeixinGetAccesstoken implements Lego, ExceptionCode {
    @Override
    public void execute(Input input, Output output) throws LegoException {
        String grant_type = input.getInputValue("grant_type");
        String appid = input.getInputValue("appid");
        String secret = input.getInputValue("secret");

        //判断传入的参数是否都有，没有就抛出异常
        if (StringUtil.isInvalid(grant_type)) {
            throw new LegoException("grant_type不能为空", 1000001L);
        }

        if(StringUtil.isInvalid(appid)){
            throw new LegoException("appid不能为空",1000001L);
        }

        if(StringUtil.isInvalid(secret)){
            throw new LegoException("secret不能为空",1000001L);
        }

        //发起http请求调用微信的api
        try {
            RestApiResponse response = HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret, new HashMap<String,String>());

            if (StringUtil.isInvalid(response.getResponseBody())) {
                throw new LegoException("调用微信接口无返回", 1000002L);
            }
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            //把返回结果塞到output中
            output.setOutputValue("response", jsonObject);
        } catch (DBException e) {
            throw new LegoException("调用微信接口失败", 1000002L);
        }


    }
}
