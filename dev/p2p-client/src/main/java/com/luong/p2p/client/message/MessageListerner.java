/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.message;

/**
 *
 * @author phult
 */
public interface MessageListerner {

    public void onMessage(String message, String ip, int port);
}
