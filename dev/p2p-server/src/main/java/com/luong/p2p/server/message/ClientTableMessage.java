/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.message;

import com.luong.p2p.server.entity.Client;

/**
 *
 * @author phult
 */
public class ClientTableMessage extends Message {

    private Client[] clients;

    public ClientTableMessage(Client[] clients) {
        this.setType(Message.TYPE_CLIENT_TABLE);
        this.clients = clients;
    }

    public ClientTableMessage() {
        this.setType(Message.TYPE_CLIENT_TABLE);
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    @Override
    Message deserialize(String jsonString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
