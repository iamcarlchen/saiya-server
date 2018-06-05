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

public class DownloadFile implements Lego{

    @Autowired
    public CodeInterface codeInterface;

    @Override
    public void execute(Input input, Output output) throws LegoException {

        String endpoint = input.getInputValue("oss-cn-shanghai-internal.aliyuncs.com");

        String accessKeyId = input.getInputValue("accessKeyId");

        String accessKeySecret = input.getInputValue("accessKeySecret");

        String bucketName = input.getInputValue("bucketName");

        String objectName = input.getInputValue("objectName");

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(endpoint)){
            throw new LegoException("endpoint不能为空",codeInterface.ERROR_WX_ENDPOINT_ENCODE);
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
        if(StringUtil.isInvalid(bucketName)){
            throw new LegoException("bucketName不能为空",codeInterface.ERROR_WX_BUCKETNAME_ENCODE);
        }

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(objectName)){
            throw new LegoException("objectName不能为空",codeInterface.ERROR_WX_OBJECTNAME_ENCODE);
        }

        //发起http请求调用api
        try{
            RestApiResponse response = HttpClientUtil.get("https://oss-example.oss-cn-shanghai-internal.aliyuncs.com/oss-api.pdf?endpoint="+endpoint+"&accessKeyId"+accessKeyId+"&accessKeySecret"+accessKeySecret+"&bucketName"+bucketName+"&objectName"+objectName,new HashMap<String,String>());
            if(StringUtil.isInvalid(response.getResponseBody())){
                throw new LegoException("调用OSS接口无返回", 1000002L);
            }
            //不为空就转换为json格式
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            output.setOutputValue("response",jsonObject);
        }catch(DBException e){
            //接口请求失败
            throw new LegoException("调用OSS接口失败", 1000002L);
        }
    }
}
