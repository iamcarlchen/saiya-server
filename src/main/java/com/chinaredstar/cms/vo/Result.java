package com.chinaredstar.cms.vo;

import com.greatbee.base.util.StringUtil;

import java.io.Serializable;

/**
 * @author:杨果
 * @date:15/12/21 下午2:24
 * <p/>
 * <p>
 * 200
 * 成功
 * 301
 * 请求失败
 * 401
 * 未授权
 * 403
 * 无权访问该资源
 * 415
 * 请求格式错误
 * 500
 * 系统错误
 * 422
 * 校验错误
 * Description:
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4696898674758059398L;
    private String code;

    private String message;

    private T dataMap;

    public Result() {

    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, T dataMap) {
        this.code = code;
        this.dataMap = dataMap;
    }

    public Result(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDataMap() {
        return dataMap;
    }

    public void setDataMap(T dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", dataMap=" + dataMap +
                '}';
    }

    public static Result error(ResultCode code,String message,Object value){
        Result result = new Result();
        result.setCode(code.getCode());
        if(StringUtil.isInvalid(message)){
            result.setMessage(code.getDesc());
        }else{
            result.setMessage(message);
        }
        if(value != null){
            result.setDataMap(value);
        }
       return result;
    }

    public static Result success(Object value){
        Result result = new Result();
        result.setCode(ResultCode.C200.getCode());
        result.setMessage(ResultCode.C200.getDesc());
        result.setDataMap(value);
        return result;
    }
}
