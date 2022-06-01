/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subhum;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        StringBuilder str=new StringBuilder(input.next());
        int n=input.nextInt();
        int sum=0;
        for(int i=0;i<n;i++) {
            int indx=input.nextInt();
            for(int j=0;j<str.length();j+=indx) {
                sum+=str.charAt(j)-'0';
            }
        }
        System.out.println(sum);
    }
}
