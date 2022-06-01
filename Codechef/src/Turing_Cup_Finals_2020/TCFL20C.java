/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turing_Cup_Finals_2020;

/**
 *
 * @author User
 */

import java.util.Scanner;
public class TCFL20C {
    
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            String str=input.next();
            long mul=1;
            long mod=(long)(Math.pow(10,9)+7);
            for(int i=0;i<str.length();i++) {
                int tmp=str.charAt(i)-48;
                if((tmp>=2 && tmp<=6) || tmp==8) {
                    mul*=3;
                }
                else if(tmp==7 || tmp==9) {
                    mul*=4;
                }
                mul%=mod;
            }
            System.out.println(mul);
        }
    }
}
