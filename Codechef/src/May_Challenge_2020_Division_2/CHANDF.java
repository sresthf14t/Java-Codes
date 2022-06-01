/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package May_Challenge_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CHANDF {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long pow[]=new long[63];
        long p=1;
        for(int i=0;i<pow.length;i++) {
            pow[i]=p-1;
            p*=2L;
        }
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long x=input.nextLong();
            long y=input.nextLong();
            long l=input.nextLong();
            long r=input.nextLong();
            long z=x|y;
            System.out.println(z);
        }
    }
}
