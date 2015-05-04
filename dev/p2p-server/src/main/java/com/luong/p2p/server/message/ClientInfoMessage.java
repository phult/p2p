/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.message;

import com.google.gson.Gson;

/**
 *
 * @author phult
 */
public class ClientInfoMessage extends Message {

    private String id;
    public ClientInfoMessage(){
        this.setType(Message.TYPE_CLIENT_INFO);
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
