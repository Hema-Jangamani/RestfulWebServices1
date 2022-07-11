package com.cedrus.RestfulWebservices1.helloworld;

public class HelloWordBean {

    private String msg;

    public HelloWordBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloWordBean{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
