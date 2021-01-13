package com.bobo.demo.network.hub;

import java.util.Map;

/**
 * 发送信息的数据结构
 */
public class MsgBody {
    private Map<String,String> mac;
    private String data;

    public Map<String, String> getMac() {
        return mac;
    }

    public void setMac(Map<String, String> mac) {
        this.mac = mac;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "mac=" + mac +
                ", data='" + data + '\'' +
                '}';
    }
}
