package com.bobo.demo.network.hub;

import com.alibaba.fastjson.JSONObject;
import com.bobo.demo.network.Cable;

/**
 * 名称：电脑
 * 功能：1、发送信息
 *      2、接收信息
 */
public class ComputerOne {
    private String name;
    private String mac;
    private Cable cable;//每个电脑实例对应一根网线实例

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

    public Cable getCable() {
        return cable;
    }

    public void setCable(Cable cable) {
        this.cable = cable;
    }

    public ComputerOne(Cable cable) {
        this.cable = cable;
    }

    public ComputerOne(String name, String mac,Cable cable) {
        this.name = name;
        this.mac = mac;
        this.cable = cable;
    }

    /**
     * 电脑发送消息-将消息传入网线
     * @param msg
     * @return
     */
    public Cable sendInfo(MsgBody msg){
        boolean access = cable.access(JSONObject.toJSONString(msg));
        if(access){
            System.out.println(this.name+"消息已发出");
        }
        return cable;
    }

    /**
     * 电脑接收数据-从网线接收数据
     * @return
     */
    public boolean reciveInfo(){
        if(cable != null && cable.getSize() > 0){
            String export = cable.export();
            JSONObject jsonObject = JSONObject.parseObject(export);
            JSONObject mac = jsonObject.getJSONObject("mac");
//            String sourceMac = mac.getString("sourceMac");
            String targetMac = mac.getString("targetMac");
            if(this.mac.equals(targetMac)){
                System.out.println(this.name+"接收到的数据为："+export);
                return true;
            }else{
                System.out.println(this.name+"不是我需要的数据");
                return false;
            }
        }else{
            System.out.println(this.name+"接收数据失败");
            return false;
        }
    }
}
