package com.greatbee.product.code;

import com.greatbee.core.ExceptionCode;

public interface CodeInterface  extends ExceptionCode{

    long ERROR_WX_BUCKET_ENCODE = 100001L;  //判断bucket字段不能为空

    long ERROR_WX_REGION_ENCODE = 100002L;  //判断region字段不能为空

    long ERROR_WX_ENDPOINT_ENCODE = 100003L;  //判断endPoint字段不能为空

    long ERROR_WX_ACCESSKEYID_ENCODE = 100004L;  //判断accessKeyId字段不能为空

    long ERROR_WX_ACCESSKEYSECRET_ENCODE = 100005L;  //判断accessKeySecret字段不能为空

    long ERROR_WX_STORETYPE_ENCODE = 100006L;  //判断storeType字段不能为空

    long ERROR_WX_READWRITEJURISDICTION_ENCODE = 100007L;  //判断readWriteJurisdiction字段不能为空

    long ERROR_WX_UPLOADINGCATALOG_ENCODE = 100007L;  //判断uploadingCatalog字段不能为空

    long ERROR_WX_CATALOGSITE_ENCODE = 100008L;   //判断catalogSite字段不能为空

    long ERROR_WX_FILEVISITSTATUS_ENCODE = 100008L;   //判断fileVisitStatus字段不能为空

    long ERROR_WX_UPLOADINGFILE_ENCODE = 100008L;   //判断uploadingFile字段不能为空

    long ERROR_WX_BUCKETNAME_ENCODE = 100008L;   //判断bucketName字段不能为空

    long ERROR_WX_OBJECTNAME_ENCODE = 100008L;   //判断objectName字段不能为空
}
