package com.demo.common;

/**
 * @ClassName ResultBean
 * @Description 返回封装对象
 * @Auther ll
 **/
public class ResultBean {

    /**
     * 返回码
     */
    private Integer resultCode;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public ResultBean() {

    }

    public ResultBean(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public ResultBean(Integer resultCode, String message, Object data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public ResultBean(Integer resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultBean(Integer resultCode, Object data) {
        this.resultCode = resultCode;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
