package com.bobo.demo.network.original;

import com.bobo.demo.network.Cable;

public class Computer {
    //计算机名称
    private String name;

    public Computer(String name) {
        this.name = name;
    }

    //发消息的能力
    public boolean sendMessage(Computer reciveComputer,String message){
        Cable cable = new Cable();//拿一根网线
        boolean access = cable.access(message);// 将数据传入网线
        if(access){
            System.out.println(this.name+"已将数据发出");
        }
        String reciveMessage = reciveComputer.reciveMessage(cable);//接收端电脑从网线中接收数据
        if(reciveMessage != null){
            System.out.println(this.name+"发送消息，并被成功接收");
            return true;
        }else{
            System.out.println(this.name+"发送消息,未被成功接收");
        }
        return false;
    }

    //接收消息的能力
    public String reciveMessage(Cable cable){
        if(cable != null && cable.getSize() > 0){//如果网线中有数据
            String export = cable.export();//将网线中的数据拿出来
            System.out.println(this.name+"接收到的消息为："+export);
            return export;
        }else{
            System.out.println(this.name+"接收到的消息为空");
            return null;
        }
    }

}
