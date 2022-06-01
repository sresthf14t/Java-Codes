/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Challenge_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class COVDSMPL {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int p=input.nextInt();
            solve(n,p);
        }
    }
    public static void solve(int n,int p) {
        int arr[][]=new int[n][n];
        int cnt=0;
        System.out.println(1+" "+1+" "+1+" "+n+" "+n);
        System.out.flush();
        int total=input.nextInt();
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                int rem=0;
                if(j!=n) {
                    System.out.println(1+" "+(i)+" "+(j+1)+" "+(i)+" "+(n));
                    System.out.flush();
                    rem+=input.nextInt();
                }
                
                if(i!=n) {
                    System.out.println(1+" "+(i+1)+" "+(1)+" "+(n)+" "+(n));
                    System.out.flush();
                    rem+=input.nextInt();
                }
                arr[i-1][j-1]=total-cnt-rem;
                cnt+=arr[i-1][j-1];
                if(cnt==total) {
                    break;
                }
            }
        }
        System.out.println(2);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.flush();
        int x=input.nextInt();
        if(x==-1) {
            System.exit(0);
        }
    }
}
