/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.connection;

import com.luong.p2p.server.message.Message;
import com.luong.p2p.server.message.VerificationMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.luong.p2p.server.entity.Client;
import com.luong.p2p.server.service.Service;

/**
 *
 * @author phult
 */
public class P2PClientWorker extends Thread {

    DatagramSocket client;
    private static final int BUFFER_SIZE = 1024;

    public P2PClientWorker(DatagramSocket client) {
        this.client = client;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket datagramPacket = new DatagramPacket(
                buffer, BUFFER_SIZE);
        while (true) {
            try {
                client.receive(datagramPacket);
                this.onMesssage(datagramPacket);
            } catch (IOException ex) {
                Logger.getLogger(P2PClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onMesssage(DatagramPacket datagramPacket) {
        System.out.println("===============================");
        System.out.println("Client send verification message");
        System.out.println("-------------------------------");
        System.out.println("host: " + datagramPacket.getAddress().getHostAddress());
        System.out.println("port: " + datagramPacket.getPort());
        String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        System.out.println("Receive UDP Message: " + message);
        if (Message.getMessageType(message) == Message.TYPE_VERIFICATION) {
            VerificationMessage vmessage = (new Gson()).fromJson(message, VerificationMessage.class);
            Client joinedClient = Service.getClientServiceInstance().get(vmessage.getId());
            if (joinedClient != null && (joinedClient.getVerificationCode().equals(vmessage.getVerificationCode()))) {
                joinedClient.setVerified(true);
                joinedClient.setP2pPort(datagramPacket.getPort());
                System.out.println("-> Client is verified");
            }else{
                System.out.println("-> Client is NOT verified");
            }
        }

    }
}
