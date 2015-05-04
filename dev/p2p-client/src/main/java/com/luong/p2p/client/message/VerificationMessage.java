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
public class VerificationMessage extends Message {

    private String id;
    private String verificationCode;

    public VerificationMessage() {
        this.setType(Message.TYPE_VERIFICATION);
    }

    public VerificationMessage(String id, String verificationCode) {
        this.id = id;
        this.verificationCode = verificationCode;        
        this.setType(Message.TYPE_VERIFICATION);
    }

    @Override
    public VerificationMessage deserialize(String jsonString) {
        return (new Gson()).fromJson(jsonString, VerificationMessage.class);
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

    /**
     * @return the verificationCode
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * @param verificationCode the verificationCode to set
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
