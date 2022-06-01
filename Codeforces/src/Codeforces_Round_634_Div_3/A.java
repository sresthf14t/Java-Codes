/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_634_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long n=input.nextLong();
            if(n%2==0) {
                System.out.println((n/2)-1);
            }
            else {
                System.out.println(n/2);
            }
        }
    }
}
