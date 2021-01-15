package com.bobo.demo.network.original;

import com.bobo.demo.network.Cable;

/**
 * 名称：电脑
 * 功能：1、接收消息；2、发送消息
 *
 */
public class Computer {
    //计算机名称
    private String name;
    //电脑连接一根网线
    private Cable cable;

    public Cable getCable() {
        return cable;
    }

    public Computer(String name, Cable cable) {
        this.name = name;
        this.cable = cable;
    }

    //发消息的能力
    public Cable sendMessage(String message){
        boolean access = cable.access(message);// 将数据传入网线
        if(access){
            System.out.println(this.name+"已将数据发出");
        }
        return cable;
    }

    //接收消息的能力
    public boolean reciveMessage(){
        if(cable != null && cable.getSize() > 0){//如果网线中有数据
            String export = cable.export();//将网线中的数据拿出来
            System.out.println(this.name+"接收到的消息为："+export);
            return true;
        }else{
            System.out.println(this.name+"接收到的消息为空");
            return false;
        }
    }

}
