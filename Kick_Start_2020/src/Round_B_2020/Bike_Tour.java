/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_B_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Bike_Tour {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            int count=0;
            for(int i=1;i<n-1;i++) {
                if(arr[i]>arr[i-1] && arr[i]>arr[i+1]) {
                    count++;
                }
            }
            System.out.println("Case #"+t+": "+count);
        }
    }
}
