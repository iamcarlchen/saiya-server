package com.chinaredstar.cms.api.component;

import java.io.Serializable;

/**
 * Created by sunny on 16-8-16.
 */
public class ServiceResult<T> implements Serializable{
    private static final long serialVersionUID = 1112149670302251011L;

    /** 是否成功获取数据 */
    private boolean isSuccess;

    /** 具体详细msg */
    private String msg;

    /** 服务端返回数据 */
    private T data;

    /** 返回操作code */
    private String code;

    public ServiceResult() {

    }

    public ServiceResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ServiceResult(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceResult{");
        sb.append("isSuccess=").append(isSuccess);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
