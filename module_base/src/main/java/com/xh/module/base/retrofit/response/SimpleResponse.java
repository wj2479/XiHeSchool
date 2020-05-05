package com.xh.module.base.retrofit.response;

/**
 * 简单的返回数据
 */
public class SimpleResponse<T> {
    /**
     * 返回状态码
     */
    private int code;
    /**
     * 消息提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    /**
     * 加密使用的Token
     */
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
