package com.example.crypto;

import java.io.UnsupportedEncodingException;

import static java.lang.Integer.valueOf;

public class DES {

    public DES(){

    }
    public static int[] stringToBits(String s) throws UnsupportedEncodingException {
        byte[] infoBin = null;
        int[] tab = new int[s.length()];
        infoBin = s.getBytes("UTF-8");
        int i=0;
        for (byte b : infoBin) {
            tab[i]=valueOf(Integer.toBinaryString(b).);
            i+=1;
        }
        return tab;
    }
/*
    public String bitsToString(int[] s){
    } */

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(stringToBits("oui"));
    }
}
