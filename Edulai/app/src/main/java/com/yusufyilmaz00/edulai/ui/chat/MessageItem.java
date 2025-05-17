package com.yusufyilmaz00.edulai.ui.chat;

public class MessageItem {
    public static final int TYPE_USER = 0;
    public static final int TYPE_AI = 1;

    private final String content;
    private final int type;

    public MessageItem(String content, int type) {
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
