package com.bobo.demo.network;

import com.bobo.demo.network.hub.ComputerOne;
import com.bobo.demo.network.hub.HubEquipment;
import com.bobo.demo.network.hub.MsgBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        //两台计算机，一根网线
//        Computer a = new Computer("A");
//        Computer b = new Computer("B");
//        boolean status = a.sendMessage(b, "你好，计算机B");

        /**
         * 集线器，多台计算机相连
         */
        //1、创建4台计算机, 每台计算机绑定一条网线
        ComputerOne c1 = new ComputerOne("A","aa-aa-aa-aa-aa-aa",new Cable());
        ComputerOne c2 = new ComputerOne("B","bb-bb-bb-bb-bb-bb",new Cable());
        ComputerOne c3 = new ComputerOne("C","cc-cc-cc-cc-cc-cc",new Cable());
        ComputerOne c4 = new ComputerOne("D","dd-dd-dd-dd-dd-dd",new Cable());
        //2、创建一台集线器
        HubEquipment hub = new HubEquipment();
        //3、将四台计算机都连接到集线器
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
        //4.2 集线器接收网线中的数据，并传给所有接入的线路，返回接入线路集合
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

    public void test(){

    }
}
