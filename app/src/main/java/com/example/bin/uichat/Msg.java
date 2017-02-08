package com.example.bin.uichat;

/**
 * Created by bin on 2017/2/8.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0; //收的消息
    public static final int TYPE_SENT = 1; //发出消息
    private String content;
    private int type;

    /**
     * @param content 消息内容
     * @param type 消息类型
     */
    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
