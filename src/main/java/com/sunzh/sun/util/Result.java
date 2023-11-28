package com.sunzh.sun.util;

import org.springframework.stereotype.Component;


public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public  Result (Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {

    }

    public static <T> Result<T> Success(T data){
        return new Result<>(200,"success", data);
    }
    public static <T> Result<T> Success(){
        return new Result<>(200,"success");
    }
    public static <T> Result<T> Eorr (T data){
        return new Result<>(500,"eorr", data);
    }
    public static <T> Result<T> Eorrqian (T data){
        return new Result<>(403,"请求参数缺失", data);
    }

}
