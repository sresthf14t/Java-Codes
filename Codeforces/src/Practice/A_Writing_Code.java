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
public class A_Writing_Code {
    static int n,m,b,mod,arr[];
    public static void main(String args[]) {
        for(int i=0;i<500;i++) {
            if(i%2==0) {
                System.out.print(1+" ");
            }
            else {
                System.out.print(2+" ");
            }
        }
        System.out.println();
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        m=input.nextInt();
        b=input.nextInt();
        mod=input.nextInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        System.out.println(solve(0,0,0));
    }
    public static long solve(int line,int indx,int error) {
        if(line==m) {
            if(error<=b) {
                return 1;
            }
            return 0;
        }
        if(indx==n-1) {
            error+=(m-line)*arr[indx];
            if(error<=b) {
                return 1;
            }
            return 0;
        }
        long cnt=0;
        if(error+arr[indx]<=b) {
            cnt+=solve(line+1,indx,error+arr[indx]);
        }
        cnt+=solve(line,indx+1,error);
        cnt%=mod;
        return cnt;
    }
}
