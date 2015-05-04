/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.client.entity;

/**
 *
 * @author phult
 */
public class Client {

    private String id;    
    private String ip;
    private int port;
    private int p2pPort;
    private String verificationCode;
    private boolean verified;
    private long createdTime;

    public Client() {
        this.verified = false;
    }

    public Client(String id, String ip, int port, int p2pPort) {
        this.verified = false;
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.p2pPort = p2pPort;
        this.createdTime = System.currentTimeMillis();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the p2pPort
     */
    public int getP2pPort() {
        return p2pPort;
    }

    /**
     * @param p2pPort the p2pPort to set
     */
    public void setP2pPort(int p2pPort) {
        this.p2pPort = p2pPort;
    }

    /**
     * @return the createdTime
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(int createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the verified
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * @return the verificationCode
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * @param verificationCode the verificationCode to set
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}