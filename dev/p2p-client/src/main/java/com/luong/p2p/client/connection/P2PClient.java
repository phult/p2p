/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.connection;

import com.luong.p2p.client.message.MessageListerner;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author phult
 */
public class P2PClient {

    private static P2PClient instance;
    private DatagramSocket socket;
    private static final int BUFFER_SIZE = 1024;
    private static final ArrayList<MessageListerner> listeners = new ArrayList<>();

    public static P2PClient getInstance() {
        if (instance == null) {
            instance = new P2PClient();
        }
        return instance;
    }

    public P2PClient() {
        try {
            socket = new DatagramSocket();
            (new P2PWorker(socket, listeners)).start();
        } catch (SocketException ex) {
            Logger.getLogger(P2PClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListener(MessageListerner listener) {        
        listeners.add(listener);
    }

    public void removeListener(MessageListerner listener) {
        listeners.remove(listener);
    }

    public boolean sendMessage(String message, String ip, int port) {
        boolean reval = true;
        try {
            DatagramPacket out_datagramPacket = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getByName(ip),
                    port);
            socket.send(out_datagramPacket);
        } catch (IOException ex) {
            reval = false;
            Logger.getLogger(P2PClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reval;
    }
}
