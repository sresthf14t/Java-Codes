/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ozon_Tech_Challenge_2020;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class A {
    public static void main(String rags[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int a[]=new int[n];
            int b[]=new int[n];
            for(int i=0;i<n;i++) {
                a[i]=input.nextInt();
            }
            for(int i=0;i<n;i++) {
                b[i]=input.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            for(int i=0;i<n;i++) {
                if(i==0) {
                    System.out.print(a[i]);
                }
                else {
                    System.out.print(" "+a[i]);
                }
            }
            System.out.println();
            for(int i=0;i<n;i++) {
                if(i==0) {
                    System.out.print(b[i]);
                }
                else {
                    System.out.print(" "+b[i]);
                }
            }
            System.out.println();
        }
    }
}
