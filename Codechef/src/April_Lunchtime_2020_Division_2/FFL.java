/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class FFL {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int p=100-input.nextInt();
            int min1=999999,min2=99999;
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            for(int i=0;i<n;i++) {
                int tmp=input.nextInt();
                if(tmp==1) {
                    min1=Math.min(min1,arr[i]);
                }
                else {
                    min2=Math.min(min2,arr[i]);
                }
            }
            if(min1+min2<=p) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }
        }
    }
}
