/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_E_2020;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            int max=0;
            for(int i=0;i<n-1;i++) {
                int j=i,tmp=arr[i+1]-arr[i];
                while(j<n-1 && arr[j+1]-arr[j]==tmp) {
                    j++;
                }
                max=Math.max(max,(j-i)+1);
                i=j-1;
            }
            ans.append("Case #"+t+": ");
            ans.append(max);
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
