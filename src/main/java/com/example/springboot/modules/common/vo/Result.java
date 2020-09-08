package com.example.springboot.modules.common.vo;

public class Result<T> {

//    private final static Integer SUCCESS=200;
//    private final static Integer Fail=500;
//    status 状态
    private int status;
    private String message;
    private T object;


    public Result() {
    }

    public Result(int status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(String message, T object) {
        this.message = message;
        this.object = object;
    }

    public Result(int status, T object) {
        this.status = status;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public enum ResultStatus {
        SUCCESS(200), FAIL(500);

        public int status;

        ResultStatus(int status) {
            this.status = status;
        }
    }
}
