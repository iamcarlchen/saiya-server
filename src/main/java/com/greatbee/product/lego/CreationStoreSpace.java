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

import java.util.HashMap;

/**
 * 创建储存空间
 */
public class CreationStoreSpace implements Lego {
    @Override
    public void execute(Input input, Output output) throws LegoException {
        //储存空间名称
        String Bucket = input.getInputValue("Bucket");
        String Region = input.getInputValue("oss-cn-shanghai-internal.aliyuncs.com");
        String Endpoint = input.getInputValue("oss-cn-shanghai-internal.aliyuncs.com");
        String accessKeyId = input.getInputValue("accessKeyId");
        String accessKeySecret = input.getInputValue("accessKeySecret");
        String store_type = input.getInputValue("store_type");
        String read_write_jurisdiction = input.getInputValue("read_write_jurisdiction");

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(Bucket)){
            throw new LegoException("Bucket不能为空",1000001L);
        }

        if(StringUtil.isInvalid(Region)){
            throw new LegoException("Region不能为空",1000001L);
        }

        if(StringUtil.isInvalid(Endpoint)){
            throw new LegoException("Endpoint不能为空",1000001L);
        }

        if(StringUtil.isInvalid(accessKeyId)){
            throw new LegoException("accessKeyId不能为空",1000001L);
        }

        if(StringUtil.isInvalid(accessKeySecret)){
            throw new LegoException("accessKeySecret不能为空",1000001L);
        }

        if(StringUtil.isInvalid(store_type)){
            throw new LegoException("store_type不能为空",1000001L);
        }

        if(StringUtil.isInvalid(read_write_jurisdiction)){
            throw new LegoException("read_write_jurisdiction不能为空",1000001L);
        }

        //发起http请求调用oss的api
        try{
            RestApiResponse response = HttpClientUtil.get("https://oss-example.oss-cn-shanghai-internal.aliyuncs.com/oss-api.pdf?Bucket="+Bucket+"&accessKeyId"+accessKeyId+"&accessKeySecret"+accessKeySecret+"&store_type"+store_type+"&read_write_jurisdiction"+read_write_jurisdiction,new HashMap<String,String>());
            if(StringUtil.isInvalid(response.getResponseBody())){
                throw new LegoException("调用OSS接口无返回", 1000002L);
            }
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            output.setOutputValue("response", jsonObject);
        } catch (DBException e){
            throw new LegoException("调用OSS接口失败", 1000002L);
        }

    }
}
