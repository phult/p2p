/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luong.p2p.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author phult
 */
public class Utils {

    static Random random = new Random();

    public static String readLine() throws IOException {
        String retval = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        retval = br.readLine();
        try {
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }
        return retval;
    }

    public static String generateString(int length) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
