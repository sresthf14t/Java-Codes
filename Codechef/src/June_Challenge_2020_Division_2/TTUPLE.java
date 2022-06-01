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
public class TTUPLE {
    static int min;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int p=input.nextInt();
            int q=input.nextInt();
            int r=input.nextInt();
            int a=input.nextInt();
            int b=input.nextInt();
            int c=input.nextInt();
            min=3;
            solve(0,-100,p,q,r,a,b,c);
            System.out.println(min);
        }
    }
    public static void solve(int cnt, int ele,int p,int q,int r,int a,int b,int c) {
        if(p==a && q==b && r==c) {
            min=Math.min(min,cnt);
            return;
        }
        if(cnt==2) {
            if(p==a && q==b && r==c) {
                min=Math.min(min,cnt);
            }
            return;
        }
        if(ele>100) {
            return;
        }
        solve(cnt,ele+1,p,q,r,a,b,c);
        //add
        solve(cnt+1,ele,p+ele,q,r,a,b,c);
        solve(cnt+1,ele,p,q+ele,r,a,b,c);
        solve(cnt+1,ele,p,q,r+ele,a,b,c);
        solve(cnt+1,ele,p+ele,q+ele,r,a,b,c);
        solve(cnt+1,ele,p,q+ele,r+ele,a,b,c);
        solve(cnt+1,ele,p+ele,q,r+ele,a,b,c);
        solve(cnt+1,ele,p+ele,q+ele,r+ele,a,b,c);
        
        //sub
//        solve(cnt+1,ele,p-ele,q,r,a,b,c);
//        solve(cnt+1,ele,p,q-ele,r,a,b,c);
//        solve(cnt+1,ele,p,q,r-ele,a,b,c);
//        solve(cnt+1,ele,p-ele,q-ele,r,a,b,c);
//        solve(cnt+1,ele,p,q-ele,r-ele,a,b,c);
//        solve(cnt+1,ele,p-ele,q,r-ele,a,b,c);
//        solve(cnt+1,ele,p-ele,q-ele,r-ele,a,b,c);
        
        //mul
        solve(cnt+1,ele,p*ele,q,r,a,b,c);
        solve(cnt+1,ele,p,q*ele,r,a,b,c);
        solve(cnt+1,ele,p,q,r*ele,a,b,c);
        solve(cnt+1,ele,p*ele,q*ele,r,a,b,c);
        solve(cnt+1,ele,p,q*ele,r*ele,a,b,c);
        solve(cnt+1,ele,p*ele,q,r*ele,a,b,c);
        solve(cnt+1,ele,p*ele,q*ele,r*ele,a,b,c);
    }
}
