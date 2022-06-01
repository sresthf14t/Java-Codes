/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_605_Div_3_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int a[]=new int[3];
            a[0]=input.nextInt();
            a[1]=input.nextInt();
            a[2]=input.nextInt();
            Arrays.sort(a);
            int n=Math.abs(a[0]-a[1])+Math.abs(a[1]-a[2])+Math.abs(a[0]-a[2]);
            System.out.println(Math.max(0, n-4));
        }
    }
}
