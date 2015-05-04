/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server;

import com.google.gson.Gson;
import com.luong.p2p.server.connection.Server;
import com.luong.p2p.server.entity.Client;
import com.luong.p2p.server.service.Service;
import java.io.IOException;

/**
 *
 * @author phult
 */
public class App {

    public static void main(String[] args) throws IOException {
//                Service.getClientServiceInstance().add(new Client("1", "ip", 123, -1));
//        Service.getClientServiceInstance().add(new Client("2", "ip", 123, -1));
//        Service.getClientServiceInstance().add(new Client("3", "ip", 123, -1));
//        Client[] clients = Service.getClientServiceInstance().getAll();
//        Gson gson = new Gson();
//        String toJson = gson.toJson(clients);
////        Type listType = new TypeToken<Client[]>() {}.getType();
//        Client[] clients1 = new Client[]{};
//        clients1 = gson.fromJson(toJson, clients1.getClass());
        Server server = new Server();
        server.start(8800);
    }
}
