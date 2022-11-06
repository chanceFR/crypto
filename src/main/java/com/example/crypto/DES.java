package com.example.crypto;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.Integer.valueOf;

public class DES {

    ArrayList<Integer> masterKey= new ArrayList<>();

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

    public static int[] partieBloc(int[] bloc, int debut, int fin) {
        int[] partBloc = Arrays.copyOfRange(bloc, debut, fin);

        return partBloc;
    }
public static int[][] decoupage(int[] bloc, int nbBlocs) {
        int[][] blocFinal;
        blocFinal= new int[2][];
        int[] debut1=partieBloc(bloc,0,bloc.length/2);
        int[] debut2=partieBloc(bloc,bloc.length/2,(bloc.length));
        blocFinal[0]=debut1;
        blocFinal[1]=debut2;
        nbBlocs=nbBlocs/2-1;
        while (nbBlocs>0){
            int[][] blocModif= new int[blocFinal.length*2][];
            for (int i = 0; i < blocFinal.length; i++) {
                int[] partieBloc1=partieBloc(blocFinal[i],0,(blocFinal[1].length/2));
                int[] partieBloc2=partieBloc(blocFinal[i],(blocFinal[1].length/2),(blocFinal[1].length));
                blocModif[0+(2*i)]=partieBloc1;
                blocModif[1+(2*i)]=partieBloc2;
            }
            blocFinal=blocModif;
            nbBlocs=nbBlocs/2-1;
        }
        return blocFinal;
}
    public static int[] generePermut(int n){
        //Genere une table de permutation de taille n
        Random rng = new Random();
        rng.nextInt(2);
        ArrayList<Integer> tab= new ArrayList<>();
        for (int i = 0; i<n; i++) tab.add(i);
        Collections.shuffle(tab, rng);
        int[] res = tab.stream().mapToInt(i -> i).toArray();
        return res;
    }
    public static int[] permutation(int[] tab_permutation, int[] bloc) {
        int n = bloc.length;
        int[] tab = new int[bloc.length];
        for (int i = 0; i<n; i++) {
            tab[i]=bloc[tab_permutation[i]];
        }

        return tab;
    }

    public static int[] invPermutation(int[] tab_permutation, int[] bloc) {

        int n = bloc.length;
        int[] tab = new int[bloc.length];
        for (int i = 0; i<n; i++) {
            tab[tab_permutation[i]]=bloc[i];
        }
        return tab;
    }
    //recolle tous les blocs
    static int[] recollage_bloc(int[][] blocs){
        int[] blocRecolle=new int[blocs.length*blocs[0].length];
        int index=0;
        for (int[] tab: blocs) {
            for (int s: tab) {
                blocRecolle[index]=s;
                index+=1;
            }
            System.out.println("\n");
        }
        return blocRecolle;
    }
    //calcule tab1 XOR tab2
    public int[] xor (int[] tab1, int[] tab2 )  {
        int[] blocXOR= new int[tab1.length];
        for (int i =0; i<tab1.length;i++) {
            if (tab1[i]==tab2[i]){
                blocXOR[i]=0;
            }
            else {
                blocXOR[i] = 1;
            }
        }
        return blocXOR;
    }
    static int[] decalle_gauche(int[] bloc, int nbCran){
        int[] blocdecal= new int[bloc.length];
        int n=bloc.length;
        for (int i =0; i<n;i++) {
            if (i+nbCran>=n){
                blocdecal[i]=bloc[(i+nbCran)-n];
            }
            else{
                blocdecal[i]=bloc[i+nbCran];
            }

        }
        return blocdecal;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(bitsToString(stringToBits("Motdepassedelamortquitue!")));
        int[] tabletest={1,2,3,4,5,6,7,8};
        int[][] testdecoupeFinal=decoupage(tabletest,4);

        for (int[] tab: testdecoupeFinal) {
            for (int s: tab) {
                System.out.print(s + "\t");
            }
            System.out.println("\n");
        }
        int[] testrecolle=recollage_bloc(testdecoupeFinal);

        for (int s: testrecolle) {
            System.out.print(s + "\t");
        }
        System.out.println("\n");
        int[] testpermutG= generePermut(8);
        for (int s: testpermutG) {
            System.out.print(s + "\t");
        }
        System.out.println("BLOC GENERE PERMUTE");
        int[] testpermutation=permutation(testpermutG,tabletest);

        for (int s: testpermutation) {
            System.out.print(s + "\t");
        }
        System.out.println("BLOC PERMUTE");

        int[] testINVpermutation=invPermutation(testpermutG,tabletest);

        for (int s: testINVpermutation) {
            System.out.print(s + "\t");
        }
        System.out.println("BLOC INV PERMUTE");

        int[] testdecal=decalle_gauche(tabletest,2);

        for (int s: testdecal) {
            System.out.print(s + "\t");
        }
        System.out.println("BLOC GAUCHE");

    }
}
