/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_635_Div_2;

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
            int arr[]=new int[4];
            for(int i=0;i<4;i++) {
                arr[i]=input.nextInt();
            }
            Arrays.sort(arr);
            System.out.println((arr[0])+" "+(arr[2])+" "+(arr[2]));
        }
    }
}
