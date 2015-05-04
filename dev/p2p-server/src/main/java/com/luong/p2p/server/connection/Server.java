/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author phult
 */
public class Server {

    private DatagramSocket datagramServer;
    private ServerSocket server;

    public void start(int port) throws IOException {
        // Open a UDP socket to listen for P2P connection info from client
        datagramServer = new DatagramSocket(port);
        P2PClientWorker p2pClientWorker = new P2PClientWorker(datagramServer);
        p2pClientWorker.start();
        // Open a TCP Server socket to listen for joining from client
        server = new ServerSocket(port);
        System.out.println("===============================");
        System.out.println("Server started");
        System.out.println("-------------------------------");
        System.out.println("port: " + port);             
        while (true) {
            Socket client = server.accept();
            (new ClientWorker(client)).start();
        }

    }
}
