/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.connection;

import com.luong.p2p.client.entity.Client;
import java.util.HashMap;

/**
 *
 * @author phult
 */
public class ClientManager {

    HashMap<String, Client> clients = new HashMap<>();
    private static ClientManager instance;

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public Client get(String id) {
        return clients.get(id);
    }

    public Client[] getAll() {
        Client[] retval = new Client[clients.size()];
        retval = clients.values().toArray(retval);
        return retval;
    }

    public void removeAll() {
        clients.clear();
    }

    public boolean add(Client client) {
        return clients.put(client.getId(), client) != null;
    }

    public boolean remove(String id) {
        return clients.remove(id) != null;
    }
}
