/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_608_Div_2_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
    static long pow[];
    public static void main(String args[]) throws IOException {
        pow=new long[63];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
//        System.out.println(lim(11,4));
        Scanner input=new Scanner(System.in);
        
        long n=input.nextLong();
        long k=input.nextLong();
        System.out.println(search(n,k));
    }
    public static long search(long n,long k) {
        long ans=-1;
        long l=1,r=n;
        while(l<=r) {
            long mid=(l+r)/2;
            long tmp=lim(n,mid);
            if(mid%2==0) {
                tmp+=lim(n,mid+1);
            }
            System.out.println(mid+" "+tmp);
            if(tmp>=k) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
    public static long lim(long n,long ele) {
        long tmp=n/ele;
        int indx=-1;
        for(int i=pow.length-1;i>=0;i--) {
            if(pow[i]<=tmp) {
                indx=i;
                break;
            }
        }
//        System.out.println(pow[indx]);
//        System.out.println(ele+" "+tmp+" "+indx);
        if(indx==0) {
            return 1;
        }
        long lim=pow[indx]-1;
        long add;
        long total=0;
        if(n>=ele*pow[indx]+lim) {
            total+=pow[indx];
        }
        else {
            total+=n-(pow[indx]*ele)+1;
        }
//        System.out.println(total);
        total+=pow[indx]-1;
        return total;
    }
}
