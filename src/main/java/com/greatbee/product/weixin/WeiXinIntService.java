package com.greatbee.product.weixin;

import com.greatbee.product.response.WeiXinOpenIdResponse;

public interface WeiXinIntService {
    WeiXinOpenIdResponse queryOpenId(String code, String appId, String appSecret);
}
