package com.bobo.demo.network;

/**
 * 网线
 */
public class Cable {

    private int size = 0;
    private String data;

    public int getSize() {
        return size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 网络信号入口
     * @param info
     * @return
     */
    public boolean access(String info){
        //添加元素
        this.setData(info);
        this.size ++;
        return true;
    }

    /**
     * 网络信号出口
     * @return
     */
    public String export(){
        String data = this.getData();
        this.size --;
        return data;
    }
}
