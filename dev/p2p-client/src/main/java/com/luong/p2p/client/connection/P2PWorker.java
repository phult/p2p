/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.connection;

import com.luong.p2p.client.message.MessageListerner;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Thread listen for datagram message
 *
 * @author phult
 */
public class P2PWorker extends Thread {

    private final DatagramSocket socket;
    private final int BUFFER_SIZE = 1024;
    private ArrayList<MessageListerner> listeners;

    public P2PWorker(DatagramSocket socket, ArrayList<MessageListerner> listeners) {
        this.socket = socket;
        this.listeners = listeners;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[BUFFER_SIZE];
        final DatagramPacket datagramPacket = new DatagramPacket(buffer, BUFFER_SIZE);
        while (true) {
            try {
                socket.receive(datagramPacket);
                onMessage(datagramPacket);
            } catch (IOException ex) {
                Logger.getLogger(P2PClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onMessage(DatagramPacket datagramPacket) {
        String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
//        System.out.println("Receive message from " + datagramPacket.getAddress().getHostAddress() + ": " + message);
        for (MessageListerner listener : listeners) {
            listener.onMessage(message,datagramPacket.getAddress().getHostAddress(),datagramPacket.getPort());
        }
    }
}
