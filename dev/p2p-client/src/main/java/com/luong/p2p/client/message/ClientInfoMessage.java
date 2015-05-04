/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.message;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author phult
 */
public class ClientInfoMessage extends Message {

    private String id;

    public ClientInfoMessage() {
        this.setType(Message.TYPE_CLIENT_INFO);
    }

    public ClientInfoMessage(String id) {
        this.setType(Message.TYPE_CLIENT_INFO);
        this.id = id;
    }

    @Override
    public ClientInfoMessage deserialize(String jsonString) {
        return (new Gson()).fromJson(jsonString, ClientInfoMessage.class);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
