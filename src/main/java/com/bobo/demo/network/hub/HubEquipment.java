package com.bobo.demo.network.hub;

import com.bobo.demo.network.Cable;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称：集线器
 * 网络分层：物理层
 * 功能：1、多个电脑网线接入功能
 *      2、转发功能，将接收到的数据转发给所有出口
 *
 */

public class HubEquipment {

    private String data;

    //集线器网口列表，用于多个电脑接入
    private List<ComputerOne> list = new ArrayList();

    //电脑接入集线器
    public boolean accessIn(ComputerOne computerOne){
        list.add(computerOne);
        return true;
    }

    /**
     * 集线器的转发功能
     * 接收消息,并发送给所有接入的线路
     */
    public List<ComputerOne> reciveInfo(Cable cable){
        //1、从网线接收信息
        if(cable != null && cable.getSize() > 0){
            String export = cable.export();
            this.data = export;
            System.out.println("集线器接收到消息");
        }
        //2、将接收到的信息转发给所有接入的电脑网线
        for (int i = 0;i < list.size();i ++){
            ComputerOne computerOne = list.get(i);
            Cable cable1 = computerOne.getCable();
            boolean access = cable1.access(data);
            if(access){
                System.out.println("集线器消息已转发出给"+computerOne.getName());
            }
        }
        return list;
    }

}
