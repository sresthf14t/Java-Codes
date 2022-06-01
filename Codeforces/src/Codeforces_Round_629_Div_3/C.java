/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_629_Div_3;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*;
public class C {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            String str=input.next();
            StringBuilder a=new StringBuilder("1");
            StringBuilder b=new StringBuilder("1");
            boolean enc=false;
            for(int i=1;i<n;i++) {
                if(!enc) {
                    if(str.charAt(i)=='0') {
                        a.append('0');
                        b.append('0');
                    }
                    else if(str.charAt(i)=='1') {
                        a.append('0');
                        b.append('1');
                        enc=true;
                    }
                    else {
                        a.append('1');
                        b.append('1');
                    }
                }
                else {
                    if(str.charAt(i)=='0') {
                        a.append('0');
                        b.append('0');
                    }
                    else if(str.charAt(i)=='1') {
                        a.append('1');
                        b.append('0');
                        enc=true;
                    }
                    else {
                        a.append('2');
                        b.append('0');
                    }
                }
            }
            System.out.println(a+"\n"+b);
        }
        
    }
}
