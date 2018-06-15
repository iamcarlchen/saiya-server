package com.greatbee.product.weixin.impl;

import com.greatbee.base.util.StringUtil;
import com.greatbee.core.bean.view.RestApiResponse;
import com.greatbee.core.lego.LegoException;
import com.greatbee.core.util.HttpClientUtil;
import com.greatbee.product.response.WeiXinOpenIdResponse;
import com.greatbee.product.weixin.WeiXinIntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class WeiXinIntServiceImpl implements WeiXinIntService{
    private static Logger logger = LoggerFactory.getLogger(WeiXinIntServiceImpl.class);
    //获取openid接口
    public static final String OPEN_ID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APP_ID&secret=APP_SECRET&code=CODE&grant_type=authorization_code";

    @Override
    public WeiXinOpenIdResponse queryOpenId(String code, String appId, String appSecret) {
        String url = OPEN_ID_URL.replace("APP_ID", appId).replace("APP_SECRET", appSecret).replace("CODE", code);
        WeiXinOpenIdResponse openIdResponse = null;

        try{
            RestApiResponse response = HttpClientUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=", new HashMap<String,String>());
            if(StringUtil.isInvalid(response.getResponseBody())){
                throw new LegoException("请求openId 失败", 1000002L);
            }

        }catch (Exception e){
            logger.error("请求openId 失败, {}",e.toString());
        }
        return null;
    }
}
