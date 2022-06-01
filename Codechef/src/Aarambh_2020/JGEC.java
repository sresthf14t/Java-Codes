/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aarambh_2020;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class JGEC {
     static Scanner input=new Scanner(System.in);
     public static void main(String args[]) {
         int test=input.nextInt();
         for(int t=0;t<test;t++) {
             int n=input.nextInt();
             String str=input.next();
             int count=0;
             
             for(int i=0;i<n-3;i++) {
                 if(str.charAt(i)=='J') {
                     if(str.charAt(i+1)=='G' && str.charAt(i+2)=='E' && str.charAt(i+3)=='C') {
                         count++;
                         i+=3;
                     }
                 }
             }
             System.out.println(count);
         }
         
     }
}
