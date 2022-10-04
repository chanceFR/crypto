package com.example.crypto;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

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
            tab[i]=valueOf(Integer.toBinaryString(b));
            i+=1;
        }
        return tab;
    }

    public static String bitsToString(int[] s){
    String str = Arrays.stream(s)
                .mapToObj(String::valueOf)
                .reduce((x, y) -> x + " " + y)
                .get();
        return str;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(bitsToString(stringToBits("Motdepassedelamortquitue!")));
    }
}
