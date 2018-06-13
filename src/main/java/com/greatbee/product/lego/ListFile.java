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
 * 列举文件
 */
public class ListFile implements Lego{

    @Autowired
    public CodeInterface codeInterface;

    @Override
    public void execute(Input input, Output output) throws LegoException {
        String delimiter = input.getInputValue("delimiter");
        String marker = input.getInputValue("marker");
        String maxKeys = input.getInputValue("maxKeys");
        String prefix = input.getInputValue("prefix");

        if(StringUtil.isInvalid(delimiter)){
            throw new LegoException("delimiter不能为空",codeInterface.ERROR_WX_DELIMITER_ENCODE);
        }

        if(StringUtil.isInvalid(marker)){
            throw new LegoException("marker不能为空",codeInterface.ERROR_WXMARKER_ENCODE);
        }

        if(StringUtil.isInvalid(maxKeys)){
            throw new LegoException("maxKeys不能为空",codeInterface.ERROR_WX_MAXKEYS_ENCODE);
        }

        if(StringUtil.isInvalid(prefix)){
            throw new LegoException("prefix不能为空",codeInterface.ERROR_WX_PREFIX_ENCODE);
        }

        try{
            RestApiResponse response = HttpClientUtil.get("https://oss-example.oss-cn-shanghai-internal.aliyuncs.com/oss-api.pdf?delimiter="+delimiter+"&marker"+marker+"&maxKeys"+maxKeys+"&prefix"+prefix, new HashMap<String,String>());
            if(StringUtil.isInvalid(response.getResponseBody())){
                //为空就抛出异常
                throw new LegoException("调用OSS接口无返回", 1000002L);
            }
            //不为空就转换为json格式
            JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
            output.setOutputValue("delimiter", jsonObject);
        }catch (DBException e){
            //接口请求失败
            throw new LegoException("调用OSS接口失败", 1000002L);
        }
    }
}
