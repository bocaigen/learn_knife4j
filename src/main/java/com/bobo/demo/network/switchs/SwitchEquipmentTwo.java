package com.bobo.demo.network.switchs;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交换器
 * 功能：1、接收数据；2、检测目标mac地址对应的出口；3、将数据发送给对应目标mac地址的出口
 *
 */
public class SwitchEquipmentTwo {

    //接收的数据
//    private String data;
    //交换机上的接口列表
    private List<ComputerTwo> list = new  ArrayList<>(6);

    public List<ComputerTwo> getList() {
        return list;
    }

    //Mac地址表
    private Map<String,Integer> map = new HashMap<>(list.size());

    //交换机转发功能
    public Integer forwardData(int port){
        //读取对应端口接入的网线中的数据
        String read = this.read(port);
        //解析数据，返回目标网口
        Integer integer = this.parseMsgBody(port,read);
        //如果目标网口为null,则转发给所有网口，否则只发给目标网口
        if(integer == null){
            for (int i = 0; i < list.size(); i++){
                this.write(i + 1,read);
            }
        }else{
            this.write(integer,read);
        }
        return integer;
    }

    //电脑网线接入交换机
    public int access(ComputerTwo computerOne){
        boolean b = list.add(computerOne);
        int index = -1;
        if(b){
            index = list.indexOf(computerOne);
        }
        return index+1;
    }
    
    //交换机从对应网口的网线中读取数据
    public String read(int port){
        ComputerTwo computerTwo = list.get(port - 1);
        String data = computerTwo.getData();
        return data;
    }

    //解析消息体,维护mac对应网口列表
    private Integer parseMsgBody(int port,String data){
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
    private void write(int port,String data){
        ComputerTwo computerTwo = list.get(port - 1);
        computerTwo.setData(data);
//        System.out.println(computerTwo.getName()+"交换机将数据写入"+port+"端口网线");
    }
}
