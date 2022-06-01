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
public class D_Relatively_Prime_Graph {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        if(m<n-1) {
            System.out.println("Impossible");
            System.exit(0);
        }
        for(int i=1;i<=n;i++) {
            for(int j=i+1;j<=n;j++) {
                if(GCD(i,j)!=1) {
                    continue;
                }
                ans.append(i+" "+j+"\n");
                m--;
                if(m==0) {
                    break;
                }
            }
            if(m==0) {
                break;
            }
        }
        if(m==0) {
            System.out.println("Possible\n"+ans);
        }
        else {
            System.out.println("Impossible");
        }
    }
    
    
    public static int GCD(int a,int n) {
        int q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
            return r1;
    }
}
