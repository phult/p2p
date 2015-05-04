/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.connection;

import com.luong.p2p.client.entity.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;
import com.google.gson.Gson;
import com.luong.p2p.client.message.ClientInfoMessage;
import com.luong.p2p.client.message.Message;
import com.luong.p2p.client.util.Utils;
import com.sun.webkit.network.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phult
 */
public class Connector {

    private Socket socket;
    private DatagramSocket datagramSocket;
    private DataInputStream input;
    private DataOutputStream output;

    public String start(String host, int port) {
        String retval = null;
        Client client = new Client();
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            datagramSocket = new DatagramSocket();
            (new ConnectorWorker(socket)).start();
            retval = Utils.generateString(5);
            authen(new Client(retval, null, -1, -1));
        } catch (IOException ex) {
            retval = null;
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retval;
    }

    public boolean isConnected() {
        boolean retval = false;
        if (socket != null) {
            retval = socket.isConnected();
        }
        return retval;
    }

    public void authen(Client client) throws IOException {
        sendMessage((new Gson()).toJson(new ClientInfoMessage(client.getId())));
    }

    protected void sendMessage(String message) throws IOException {
        output.writeUTF(message);
        output.flush();
    }

    public void getClientTable() throws IOException {
        sendMessage("{\"type\":" + Message.TYPE_CLIENT_TABLE + "}");
    }
}
