package com.example.spring.cloud.config.vault.client.api;

public class ConfigProps {

    private String msg;

    private String mysecret;

    public ConfigProps() {
    }

    public ConfigProps(String msg, String mysecret) {
        this.msg = msg;
        this.mysecret = mysecret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMysecret() {
        return mysecret;
    }

    public void setMysecret(String mysecret) {
        this.mysecret = mysecret;
    }
}
