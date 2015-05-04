/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.connection;

import com.google.gson.Gson;
import com.luong.p2p.client.entity.Client;
import com.luong.p2p.client.message.ClientTableMessage;
import com.luong.p2p.client.message.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phult
 */
public class ConnectorWorker extends Thread {

    private final Socket client;
    DataInputStream streamToMe;
    DataOutputStream streamFromMe;

    public ConnectorWorker(Socket client) throws IOException {
        this.client = client;
        streamToMe = new DataInputStream(this.client.getInputStream());
        streamFromMe = new DataOutputStream(this.client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = streamToMe.readUTF();
                onMessage(message);
            }
        } catch (IOException ex) {            
            Logger.getLogger(ConnectorWorker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("===============================");
            System.out.println("Server disconnected");
            System.out.println("-------------------------------");
        }
    }

    public void onMessage(String message) {
        if (Message.getMessageType(message) == Message.TYPE_VERIFICATION) {
//            System.out.println("On message: TYPE_VERIFICATION" + message);
            P2PClient.getInstance().sendMessage(message, client.getInetAddress().getHostAddress(), client.getPort());
        } else if (Message.getMessageType(message) == Message.TYPE_CLIENT_TABLE) {
//            System.out.println("On message: TYPE_CLIENT_TABLE" + message);
            ClientTableMessage fromJson = (new Gson()).fromJson(message, ClientTableMessage.class);
            ClientManager.getInstance().removeAll();
            for (Client item : fromJson.getClients()) {
                ClientManager.getInstance().add(item);
            }
        }
    }
}
