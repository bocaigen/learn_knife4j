package com.bobo.demo.network.switchs;

import com.alibaba.fastjson.JSONObject;
import com.bobo.demo.network.hub.MsgBody;

/**
 * 名称：电脑
 * 功能：1、发送信息
 *      2、接收信息
 */
public class ComputerTwo {
    private String name;
    private String mac;
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ComputerTwo(String name, String mac) {
        this.name = name;
        this.mac = mac;
    }

    /**
     * 电脑发送消息
     * @param msg
     * @return
     */
    public void sendInfo(MsgBody msg){
        String message = JSONObject.toJSONString(msg);
        this.setData(message);
    }

    /**
     * 电脑接收数据
     * @return
     */
    public String reciveInfo(){
        String data = this.getData();
        return data;
    }

    /**
     * 数据解析
     */
    public boolean parseMessageBody(String export){
        JSONObject jsonObject = JSONObject.parseObject(export);
        JSONObject mac = jsonObject.getJSONObject("mac");
        String targetMac = mac.getString("targetMac");
        if(this.mac.equals(targetMac)){
            String data = jsonObject.getString("data");
            System.out.println(this.name+"接收到的数据为："+data);
            return true;
        }else{
            System.out.println(this.name+"这不是我需要的数据");
            return false;
        }
    }
}
