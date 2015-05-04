/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client;

import com.luong.p2p.client.connection.ClientManager;
import com.luong.p2p.client.connection.Connector;
import com.luong.p2p.client.connection.P2PClient;
import com.luong.p2p.client.entity.Client;
import com.luong.p2p.client.message.MessageListerner;
import com.luong.p2p.client.util.Utils;
import java.io.IOException;

/**
 *
 * @author phult
 */
public class CLI {

    public static void main(String[] args) throws IOException {
        Connector connector = new Connector();
        String command = "";
        P2PClient.getInstance().addListener(new MessageListerner() {
            @Override
            public void onMessage(String message, String ip, int port) {
                System.out.println("===============================");
                System.out.println("Receive a message");
                System.out.println("-------------------------------");
                System.out.println("ip: " + ip);
                System.out.println("port: " + port);
                System.out.println("message: " + message);
                // Received message from a strange computer
            }
        });
        System.out.println("P2P Client CLI - phult.contact@gmail.com");
        System.out.println("---------------------------------------------");
        while (!command.equalsIgnoreCase("exit")) {
            try {
                command = Utils.readLine();
                String[] commandArgs = command.split(" ");
                if (commandArgs.length > 0) {
                    switch (commandArgs[0].toLowerCase()) {
                        case "connect": {
                            // Connect to P2P Server
                            if (connector.isConnected()) {
                                System.out.println("Connection was established earlier!");
                            } else {
                                String id = connector.start(commandArgs[1], Integer.parseInt(commandArgs[2]));
                                if (id != null) {
                                    System.out.println("Client " + id + " connected to P2P server!");
                                } else {
                                    System.out.println("Can NOT connect to P2P server!");
                                }
                            }
                            break;
                        }
                        case "peer": {
                            if (commandArgs[1].equalsIgnoreCase("get")) {
                                // Get P2P table
                                if (connector.isConnected()) {
                                    connector.getClientTable();
                                    System.out.println("Updated Client table successfully");
                                } else {
                                    System.out.println("Require connect to P2P server");
                                }
                            } else if (commandArgs[1].equalsIgnoreCase("show")) {
                                // Show P2P table
                                Client[] clients = ClientManager.getInstance().getAll();
                                System.out.format("%-10s|%-20s|%-10s|%-8s\n", "ID", "IP", "port", "time");
                                for (Client item : clients) {
                                    System.out.format("%-10s|%-20s|%-10s|%-8s\n", item.getId(), item.getIp(), item.getP2pPort(), item.getCreatedTime());
                                }
                            } else if (commandArgs[1].equalsIgnoreCase("send")) {
                                // Send P2P message
                                String ip = "";
                                int port = -1;
                                String message = "";
                                if (commandArgs.length == 4) {
                                    message = commandArgs[3];
                                    String clientID = commandArgs[2];
                                    Client receiver = ClientManager.getInstance().get(clientID);
                                    if (receiver != null) {
                                        ip = receiver.getIp();
                                        port = receiver.getP2pPort();
                                    } else {
                                        System.out.println("Receiver is not available!");
                                        break;
                                    }
                                } else if (commandArgs.length == 5) {
                                    ip = commandArgs[2];
                                    port = Integer.parseInt(commandArgs[3]);
                                    message = commandArgs[4];
                                }
                                if (P2PClient.getInstance().sendMessage(message, ip, port)) {
                                    System.out.println("The message sent successfully!");
                                } else {
                                    System.out.println("Sent message failed!");
                                }
                            } else {
                                System.out.println("Invalid command!");
                            }
                            break;
                        }
                        case "help": {
                            System.out.format("%-30s%s\n", "connect -host -port", "Connect to P2P server");
                            System.out.format("%-30s%s\n", "peer get", "Update client table");
                            System.out.format("%-30s%s\n", "peer show", "Print client table");
                            System.out.format("%-30s%s\n", "peer send -clientID -message", "Send message to a P2P client");
                            System.out.format("%-30s%s\n", "peer send -ip -port -message", "Send message to a P2P client");
                            System.out.format("%-30s%s\n", "exit", "Exit CLI");
                            break;
                        }
                        case "exit": {
                            break;
                        }
                        default: {
                            System.out.println("Invalid command!3");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid command!2");
            }
        }

        System.exit(0);
    }

}
