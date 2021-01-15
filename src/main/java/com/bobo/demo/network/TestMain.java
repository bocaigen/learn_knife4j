package com.bobo.demo.network;

import com.bobo.demo.network.hub.ComputerOne;
import com.bobo.demo.network.hub.HubEquipment;
import com.bobo.demo.network.hub.MsgBody;
import com.bobo.demo.network.original.Computer;
import com.bobo.demo.network.switchs.ComputerTwo;
import com.bobo.demo.network.switchs.SwitchEquipmentTwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        TestMain tm = new TestMain();

//        tm.originalTest();

        tm.hubTest();
    }

    /**
     * 原始网络信息接收流程
     * 两台计算机，一根网线
     */
    public void originalTest(){
        //准备一台电脑 a，并接入网线
        Computer a = new Computer("A",new Cable());
        //准备第二台电脑 b，并接入第一台电脑 a的网线
        Computer b = new Computer("B",a.getCable());
        //电脑 a发送一条数据
        a.sendMessage("你好，计算机B");
        //电脑 b接收一条数据
        b.reciveMessage();
    }

    /**
     * 集线器，多台计算机相连
     */
    public void hubTest(){

        //1、创建4台计算机, 每台计算机绑定一条网线
        ComputerOne c1 = new ComputerOne("A","aa-aa-aa-aa-aa-aa",new Cable());
        ComputerOne c2 = new ComputerOne("B","bb-bb-bb-bb-bb-bb",new Cable());
        ComputerOne c3 = new ComputerOne("C","cc-cc-cc-cc-cc-cc",new Cable());
        ComputerOne c4 = new ComputerOne("D","dd-dd-dd-dd-dd-dd",new Cable());
        //2、创建一台集线器
        HubEquipment hub = new HubEquipment();
        //3、将四台计算机的网线都连接到集线器
        hub.accessIn(c1);
        hub.accessIn(c2);
        hub.accessIn(c3);
        hub.accessIn(c4);
        //4、c1电脑给c3电脑发一条数据
        MsgBody msgBody = new MsgBody();
        Map<String,String> map = new HashMap<>();
        map.put("sourceMac","aa-aa-aa-aa-aa-aa");
        map.put("targetMac","cc-cc-cc-cc-cc-cc");
        msgBody.setMac(map);
        msgBody.setData("你好，计算机C");
        //4.1 c1将数据传入网线
        Cable cable = c1.sendInfo(msgBody);
        //4.2 集线器接收网线中的数据，并传给所有接入的线路，返回接入线路电脑的集合
        List<ComputerOne> computerOnes = hub.reciveInfo(cable);
        for (int i = 0;i < computerOnes.size();i ++){
            ComputerOne computerOne = computerOnes.get(i);
            //4.3 各个线路终端电脑接收数据，判断目标mac
            boolean b = computerOne.reciveInfo();
            if(b){
                System.out.println("给电脑"+computerOne.getName()+"传输数据成功");
            }else{
                System.out.println("电脑"+computerOne.getName()+"拒绝接收");
            }
        }
    }

    public void switchTest(){
        //1、创建4台计算机, 每台计算机绑定一条网线
        ComputerTwo c1 = new ComputerTwo("A","aa-aa-aa-aa-aa-aa");
        ComputerTwo c2 = new ComputerTwo("B","bb-bb-bb-bb-bb-bb");
        ComputerTwo c3 = new ComputerTwo("C","cc-cc-cc-cc-cc-cc");
        ComputerTwo c4 = new ComputerTwo("D","dd-dd-dd-dd-dd-dd");
        //2、创建一台交换机
        SwitchEquipmentTwo switchEquipmentTwo = new SwitchEquipmentTwo();
        //3、将四台电脑连接到交换机
        int access = switchEquipmentTwo.access(c1);
        int access1 = switchEquipmentTwo.access(c2);
        int access2 = switchEquipmentTwo.access(c3);
        int access3 = switchEquipmentTwo.access(c4);
        //4、c1 给c4 发一条信息
        MsgBody msgBody = new MsgBody();
        Map<String,String> map = new HashMap<>();
        map.put("sourceMac","aa-aa-aa-aa-aa-aa");
        map.put("targetMac","dd-dd-dd-dd-dd-dd");
        msgBody.setMac(map);
        msgBody.setData("你好，计算机C");
        c1.sendInfo(msgBody);
        //5、交换机从传入网口转发数据
        Integer integer = switchEquipmentTwo.forwardData(access);
        List<ComputerTwo> list = switchEquipmentTwo.getList();
        if(integer != null && integer > 0){
            ComputerTwo computerTwo = list.get(integer);
            System.out.println(computerTwo.getName()+"接收到的数据为："+computerTwo.reciveInfo());
        }else{

        }



    }
}
