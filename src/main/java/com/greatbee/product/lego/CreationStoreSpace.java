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

/**
 * 创建储存空间
 */
public class CreationStoreSpace implements Lego {

    @Autowired
    public CodeInterface codeInterface;
    @Override
    public void execute(Input input, Output output) throws LegoException {
        String bucKet = input.getInputValue("bucKet");
        String region = input.getInputValue("oss-cn-shanghai-internal.aliyuncs.com");
        String endPoint = input.getInputValue("oss-cn-shanghai-internal.aliyuncs.com");
        String accessKeyId = input.getInputValue("accessKeyId");
        String accessKeySecret = input.getInputValue("accessKeySecret");
        String storeType = input.getInputValue("storeType");
        String readWriteJurisdiction = input.getInputValue("readWriteJurisdiction");

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(bucKet)){
            throw new LegoException("bucket不能为空",codeInterface.ERROR_WX_BUCKET_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(region)){
            throw new LegoException("region不能为空",codeInterface.ERROR_WX_REGION_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(endPoint)){
            throw new LegoException("endPoint不能为空",codeInterface.ERROR_WX_ENDPOINT_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(accessKeyId)){
            throw new LegoException("accessKeyId不能为空",codeInterface.ERROR_WX_ACCESSKEYID_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(accessKeySecret)){
            throw new LegoException("accessKeySecret不能为空",codeInterface.ERROR_WX_ACCESSKEYSECRET_ENCODE);
        }
        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(storeType)){
            throw new LegoException("storeType不能为空",codeInterface.ERROR_WX_STORETYPE_ENCODE);
        }

        if(StringUtil.isInvalid(readWriteJurisdiction)){
            throw new LegoException("readWriteJurisdiction不能为空",codeInterface.ERROR_WX_READWRITEJURISDICTION_ENCODE);
        }

        //发起http请求调用oss的api
        try{
            RestApiResponse response = HttpClientUtil.get("https://oss-example.oss-cn-shanghai-internal.aliyuncs.com/oss-api.pdf?bucKet="+bucKet+"&accessKeyId"+accessKeyId+"&accessKeySecret"+accessKeySecret+"&storeType"+storeType+"&readWriteJurisdiction"+readWriteJurisdiction,new HashMap<String,String>());
            //判断接口是否为空
            if(StringUtil.isInvalid(response.getResponseBody())){
                //为空就抛出异常
                throw new LegoException("调用OSS接口无返回", 1000002L);
            }
            //不为空就转换为json格式
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            output.setOutputValue("response", jsonObject);
        } catch (DBException e){
            //接口请求失败
            throw new LegoException("调用OSS接口失败", 1000002L);
        }

    }
}
