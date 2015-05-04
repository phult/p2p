/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.message;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 *
 * @author phult
 */
public abstract class Message {

    public static final int TYPE_CLIENT_INFO = 1;
    public static final int TYPE_CLIENT_TABLE = 2;
    public static final int TYPE_VERIFICATION = 3;
    private int type = -1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    abstract Message deserialize(String jsonString);

    public static int getMessageType(String jsonString) {
        JsonElement parse = (new JsonParser()).parse(jsonString);
        return Integer.parseInt(parse.getAsJsonObject().get("type").getAsString());
    }
}
