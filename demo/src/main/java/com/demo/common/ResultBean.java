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

    public ResultBean(String result) {
        String[] results = result.split(",");
        this.resultCode = Integer.parseInt(results[0]);
        this.message = results[1];
    }

    public ResultBean(String result, Object data) {
        String[] results = result.split(",");
        this.resultCode = Integer.parseInt(results[0]);
        this.message = results[1];
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
