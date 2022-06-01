/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_648_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
    static int n;
    static long arr[],pow[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        pow=new long[63];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2L*pow[i-1];
        }
        System.out.println(solve(0,new int[63],0));
    }
    public static long solve(int indx,int sel[],int cnt) {
        if(indx==n) {
            return check(sel,cnt);
        }
        long ans=solve(indx+1,sel,cnt);
        for(int i=0;i<sel.length;i++) {
            if((arr[indx]/pow[i])%2==1) {
                sel[i]++;
            }
        }
        
        ans=Math.max(ans,solve(indx+1,sel,cnt+1));
        
        for(int i=0;i<sel.length;i++) {
            if((arr[indx]/pow[i])%2==1) {
                sel[i]--;
            }
        }
        return ans;
    }
    public static long check(int sel[],int cnt) {
        long ans=0;
        for(int i=0;i<sel.length;i++) {
            if(sel[i]>=Math.max(1,cnt-2)) {
                ans+=pow[i];
            }
        }
        return ans;
    }
}
