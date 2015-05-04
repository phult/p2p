/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.connection;

import com.google.gson.Gson;
import com.luong.p2p.server.entity.Client;
import com.luong.p2p.server.message.ClientInfoMessage;
import com.luong.p2p.server.message.ClientTableMessage;
import com.luong.p2p.server.message.Message;
import com.luong.p2p.server.message.VerificationMessage;
import com.luong.p2p.server.service.Service;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

/**
 *
 * @author phult
 */
public class ClientWorker extends Thread {

    private final Socket client;
    DataInputStream streamFromClient;
    DataOutputStream streamToClient;

    public ClientWorker(Socket client) throws IOException {
        this.client = client;
        streamFromClient = new DataInputStream(this.client.getInputStream());
        streamToClient = new DataOutputStream(this.client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("===============================");
            System.out.println("Client connected");
            System.out.println("-------------------------------");
            System.out.println("ip: " + client.getInetAddress().getHostAddress());
            System.out.println("port: " + client.getPort());
            while (true) {
                String message = streamFromClient.readUTF();
                onMessage(message);
            }
        } catch (IOException ex) {
//            System.out.println("");
//            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("===============================");
            System.out.println("Client disconnected");
            System.out.println("-------------------------------");
            System.out.println("ip: " + client.getInetAddress().getHostAddress());
            System.out.println("port: " + client.getPort());
            Service.getClientServiceInstance().removeByIP(client.getInetAddress().getHostAddress());
        }
    }

    // Send client table is infos about all of clients joined
    public void sendClientTable() throws IOException {
        Client[] clients = Service.getClientServiceInstance().getAll();
        String message = (new Gson()).toJson(clients);
        sendMessage(message);
    }

    // Send a message to client
    public void sendMessage(String message) throws IOException {
        System.out.println("Send TCP Message: " + message);
        streamToClient.writeUTF(message);
        streamToClient.flush();
    }

    // Receive a message from client
    public void onMessage(String message) throws IOException {
        System.out.println("Receive TCP Message: " + message);
        if (Message.getMessageType(message) == Message.TYPE_CLIENT_INFO) {
            ClientInfoMessage clientInfoMessage = (new Gson()).fromJson(message, ClientInfoMessage.class);
            System.out.println("===============================");
            System.out.println("Receive client info");
            System.out.println("-------------------------------");
            System.out.println("ip: " + client.getLocalAddress().getHostAddress());
            System.out.println("port: " + client.getLocalPort());
            System.out.println("id: " + clientInfoMessage.getId());
            Client clientObject = new Client(clientInfoMessage.getId(), client.getInetAddress().getHostAddress(), client.getPort(), -1);
            clientObject.setVerificationCode(UUID.randomUUID().toString());
            Service.getClientServiceInstance().add(clientObject);
            sendMessage((new Gson()).toJson(new VerificationMessage(clientObject.getId(), clientObject.getVerificationCode())));
        } else if (Message.getMessageType(message) == Message.TYPE_CLIENT_TABLE) {
            String clientTableMessage = (new Gson()).toJson(new ClientTableMessage(Service.getClientServiceInstance().getAll()));
            sendMessage(clientTableMessage);
        }
    }
}
