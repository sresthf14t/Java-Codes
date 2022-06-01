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
public class Bus_Routes {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            long days=input.nextLong();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextLong();
            }
            long min=Long.MAX_VALUE;
            for(int i=n-1;i>=0;i--) {
                long tmp=days/arr[i];
                days=tmp*arr[i];
                min=Math.min(min,days);
            }
            System.out.println("Case #"+t+": "+min);
        }
    }
}
