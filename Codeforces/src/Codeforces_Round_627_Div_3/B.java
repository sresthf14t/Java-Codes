/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_627_Div_3;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class B {
    static int table[][];
    static int arr[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            table=new int[n+1][n+1];
            System.out.println(lps(0,n-1));
            if(lps(0,n-1)>=3) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
            for(int i=0;i<=n;i++) {
                for(int j=0;j<=n;j++) {
                    System.out.print(table[i][j]+" ");
                }
                System.out.println();
            }
        }
        
    }
    
    static int lps(int l,int r) {
        System.out.println(l+" "+r+" ");
        if(l==r) {
            System.out.println("return 1");
            return 1;
        }
        if(l+1==r && arr[l]==arr[r]) {
            System.out.println("return 2");
            return 2;
        }
        if(arr[l]==arr[r]) {
            System.out.println("2+lps(l+1,r-1)");
            if(table[l+1][r-1]!=0) {
                return table[l+1][r-1];
            } 
            table[l+1][r-1]=2+lps(l+1,r-1);
            return table[l+1][r-1];
        }
        else {
            if(table[l+1][r]==0) {
                System.out.println("lps(l+1,r)");
                table[l+1][r]=lps(l+1,r);
            }
            if(table[l][r-1]==0) {
                System.out.println("lps(l,r-1)");
                table[l][r-1]=lps(l,r-1);
            }
            return Math.max(table[l+1][r], table[l][r-1]);
        }
    }
}
