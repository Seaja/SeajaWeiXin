package com.demo.seajia.seajiaweixin.bean;

import java.io.Serializable;

/**
 * Created by S.F.S on 2016/4/5.
 */
public class Msg {
    public final static int TYPE_RECEIVED = 0;
    public final static int TYPE_SEND = 1;

    private String content;
    private int type;
    private int headImg;

    public Msg(String content,int type,int headImg){
        this.type=type;
        this.content=content;
        this.headImg=headImg;
    }

    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
    public int getHeadImg(){
        return headImg;
    }
}
