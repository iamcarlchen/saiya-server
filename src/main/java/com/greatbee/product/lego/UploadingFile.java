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
 * 上传文件
 */
public class UploadingFile implements Lego{

    @Autowired
    public CodeInterface codeInterface;

    @Override
    public void execute(Input input, Output output) throws LegoException {

        String uploadingCatalog = input.getInputValue("uploadingCatalog");

        String catalogSite = input.getInputValue("catalogSite");

        String fileVisitStatus = input.getInputValue("fileVisitStatus");

        String uploadingFile = input.getInputValue("uploadingFile");

        String endpoint = "oss-cn-shanghai-internal.aliyuncs.com";

        String accessKeyId = input.getInputValue("accessKeyId");

        String accessKeySecret = input.getInputValue("accessKeySecret");

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(uploadingCatalog)){
            throw new LegoException("uploadingCatalog不能为空",codeInterface.ERROR_WX_UPLOADINGCATALOG_ENCODE);
        }

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(catalogSite)){
            throw new LegoException("catalogSite不能为空",codeInterface.ERROR_WX_CATALOGSITE_ENCODE);
        }

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(fileVisitStatus)){
            throw new LegoException("fileVisitStatus不能为空",codeInterface.ERROR_WX_FILEVISITSTATUS_ENCODE);
        }

        //判断传入的参数是否都有，没有就抛出异常
        if(StringUtil.isInvalid(uploadingFile)){
            throw new LegoException("uploadingFile不能为空",codeInterface.ERROR_WX_UPLOADINGFILE_ENCODE);
        }

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

        //发起http请求调用api
        try{
            RestApiResponse response = HttpClientUtil.get("https://oss-example.oss-cn-shanghai-internal.aliyuncs.com/oss-api.pdf?uploadingCatalog="+uploadingCatalog+"&catalogSite"+catalogSite+"&fileVisitStatus"+fileVisitStatus+"&uploadingFile"+uploadingFile,new HashMap<String,String>());
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
