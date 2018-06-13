package com.greatbee.product.lego;

import com.alibaba.fastjson.JSONObject;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.util.StringUtil;
import com.greatbee.core.bean.view.RestApiResponse;
import com.greatbee.core.lego.Input;
import com.greatbee.core.lego.Lego;
import com.greatbee.core.lego.LegoException;
import com.greatbee.core.lego.Output;
import com.greatbee.core.util.HttpClientUtil;
import com.greatbee.product.code.CodeInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class WeChatLogin implements Lego{

    @Autowired
    public CodeInterface codeInterface;

    @Override
    public void execute(Input input, Output output) throws LegoException {
        String appid = input.getInputValue("appid");
        String secret = input.getInputValue("secret");
        String code = input.getInputValue("code");
        String grantType = input.getInputValue("grantType");

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(appid)){
            throw new LegoException("appid不能为空", codeInterface.ERROR_WX_APPID_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(secret)){
            throw new LegoException("secret不能为空",codeInterface.ERROR_WX_SECRET_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(code)){
            throw new LegoException("code不能为空",codeInterface.ERROR_WX_CODE_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(grantType)){
            throw new LegoException("grantType不能为空",codeInterface.ERROR_WX_GRANTTYPE_ENCODE);
        }
        //发起http请求调用微信的api
        try{
            RestApiResponse response = HttpClientUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code"+code+"&grantType"+grantType, new HashMap<String,String>());
            if(StringUtil.isInvalid(response.getResponseBody())){
                throw new LegoException("调用微信接口无返回", 1000002L);
            }
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            //把返回结果塞到output中
            output.setOutputValue("response",jsonObject);
        }catch (DBException e){
            throw new LegoException("调用微信接口失败", 1000002L);
        }
    }
}
