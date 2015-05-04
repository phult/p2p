/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.service;

import com.luong.p2p.server.entity.Client;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author phult
 */
public class ClientService implements com.luong.p2p.server.service.iface.Service {

    HashMap<String, Client> clients = new HashMap<>();

    public ClientService() {
    }

    public Client get(String id) {
        return clients.get(id);
    }

    public Client[] getAll() {
        Client[] retval = new Client[clients.size()];
        retval = clients.values().toArray(retval);
        return retval;
    }

    public boolean add(Client client) {
        return clients.put(client.getId(), client) != null;
    }

    public boolean remove(String id) {
        return clients.remove(id) != null;
    }

    public boolean removeByIP(String ip) {
        boolean retval = false;
        String[] keys = clients.keySet().toArray(new String[]{});
        for (String key : keys) {
            Client client = clients.get(key);
            if (client.getIp().equals(ip)) {
                clients.remove(key);
                retval = true;
            }
        }
        return retval;
    }
}
