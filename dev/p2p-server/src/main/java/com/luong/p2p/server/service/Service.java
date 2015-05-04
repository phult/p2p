/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.service;

/**
 *
 * @author phult
 */
public class Service {

    private static com.luong.p2p.server.service.iface.Service clientService;

    public static ClientService getClientServiceInstance() {
        if (clientService == null) {
            clientService = new ClientService();
        }
        return (ClientService) clientService;
    }
}