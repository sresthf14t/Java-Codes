/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C_Football {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=input.nextInt();
        if(n==1) {
            System.out.println(-1);
            System.exit(0);
        }
        int arr[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=-1;
            }
        }
        boolean is_pos=true;
        for(int i=1;i<=k;i++) {
            for(int j=0;j<n;j++) {
                if(arr[j][(j+i)%n]==0 || arr[j][(j+i)%n]==1) {
                    is_pos=false;
                    break;
                }
                arr[j][(j+i)%n]=1;
                arr[(j+i)%n][j]=0;
            }
            if(!is_pos) {
                break;
            }
        }
        if(!is_pos) {
            System.out.println(-1);
            System.exit(0);
        }
        int count=0;
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]==1) {
                    count++;
                    ans.append((i+1)+" "+(j+1)+"\n");
                }
            }
        }
        System.out.println(count+"\n"+ans);
    }
}
