package com.xw.code_generate.model;

public class ResBean {

    private Integer code;

    private Object data;

    private String msg;

    // 成功
    public static ResBean ok(Object data, String msg) {
        return new ResBean(200, data, msg);
    }

    // 成功
    public static ResBean ok(String msg) {
        return new ResBean(200, null, msg);
    }

    // 失败
    public static ResBean error(Object data, String msg) {
        return new ResBean(500, data, msg);
    }

    // 失败
    public static ResBean error(String msg) {
        return new ResBean(500, null, msg);
    }

    public ResBean() {
    }

    public ResBean(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
