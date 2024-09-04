package com.example.common;

public class Result {
    private Integer status;
    private String statusText;
    private Object data;

    private Result(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public static Result success() {
        Result result = new Result();
        result.setStatus(200);
        result.setStatusText("请求成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(500);
        result.setStatusText("请求失败");
        return result;
    }

    public static Result error(String statusText) {
        Result result = new Result();
        result.setStatus(500);
        result.setStatusText(statusText);
        return result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
