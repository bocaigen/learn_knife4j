package com.bobo.demo.network.switchs;

import com.alibaba.fastjson.JSONObject;
import com.bobo.demo.network.Cable;
import com.bobo.demo.network.hub.ComputerOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交换器
 * 功能：1、接收数据；2、检测目标mac地址对应的出口；3、将数据发送给对应目标mac地址的出口
 *
 */
public class SwitchEquipment {

    //接收的数据
    private String data;
    //交换机上的接口列表
    private List<ComputerOne> list = new  ArrayList<>(6);
    //Mac地址表
    private Map<String,Integer> map = new HashMap<>(list.size());

    //交换机转发功能
    public Integer forwardData(int port){
        //读取对应端口接入的网线中的数据
        this.read(port);
        //解析数据，返回目标网口
        Integer integer = this.parseMsgBody(port);
        //如果目标网口为null,则转发给所有网口，否则只发给目标网口
        if(integer == null){
            for (int i = 0; i < list.size(); i++){
                this.write(i + 1);
            }
        }else{
            this.write(integer);
        }
        return integer;
    }

    //电脑网线接入交换机
    public int access(ComputerOne computerOne){
        boolean b = list.add(computerOne);
        int index = -1;
        if(b){
            index = list.indexOf(computerOne);
        }
        return index+1;
    }
    
    //交换机从对应网口的网线中读取数据
    public boolean read(int port){
        ComputerOne computerOne = list.get(port - 1);
        Cable cable = computerOne.getCable();
        if(cable != null && cable.getSize() > 0){
            String export = cable.export();
            this.data = export;
        }
        return true;
    }

    //解析消息体,维护mac对应网口列表
    private Integer parseMsgBody(int port){
        Integer integer = null;
        if(data != null){
            JSONObject jsonObject = JSONObject.parseObject(data);
            JSONObject mac = jsonObject.getJSONObject("mac");
            String sourceMac = mac.getString("sourceMac");
            String targetMac = mac.getString("targetMac");
            map.put(sourceMac,port);
            integer = map.get(targetMac);
        }
        return integer;
    }

    //交换机往目标网口对应的网线中写数据
    private boolean write(int port){
        ComputerOne computerOne = list.get(port - 1);
        Cable cable = computerOne.getCable();
        boolean access = cable.access(data);
        if(access){
            System.out.println(computerOne.getName()+"交换机将数据写入"+port+"端口网线");
        }
        return access;
    }
}
