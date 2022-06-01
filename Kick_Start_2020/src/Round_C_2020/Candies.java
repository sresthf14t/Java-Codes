/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_C_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Candies {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int query=input.nextInt();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            long sum=0;
            ArrayList<Integer> arrli[]=new ArrayList[n+1];
            ArrayList<Long> sm[]=new ArrayList[n+1];
            for(int i=0;i<arrli.length;i++) {
                arrli[i]=new ArrayList<>();
                sm[i]=new ArrayList<>();
            }
            for(int q=0;q<query;q++) {
                char chr=input.next().charAt(0);
                if(chr=='U') {
                    int indx=input.nextInt()-1;
                    long val=input.nextLong();
                    arr[indx]=val;
                    allocate(arrli,sm,n);
                }
                else {
                    int l=input.nextInt()-1;
                    int r=input.nextInt()-1;
                    int indx=-1;
                    for(int i=arrli[l].size()-1;i>=0;i--) {
                        if(arrli[l].get(i)<r) {
                            indx=i;
                            break;
                        }
                    }
                    if(indx==-1) {
                        long tmp=sum(arr,l,r,l);
                        sum+=tmp;
                        arrli[l].add(r);
                        sm[l].add(tmp);
                    }
                    else {
                        long tmp=sum(arr,l,r,arrli[l].get(indx))+sm[l].get(indx);
                        sum+=tmp;
                        arrli[l].add(r);
                        sm[l].add(tmp);
                    }
                }
            }
            ans.append("Case #"+t+": ");
            ans.append(sum);
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long sum(long arr[],int l,int r,int strt) {
        long sum=0,cnt=(strt-l)+1;
        for(int i=strt;i<=r;i++,cnt++) {
            if(cnt%2==1) {
                sum+=(cnt*arr[i]);
            }
            else {
                sum-=(cnt*arr[i]);
            }
        }
        return sum;
    }
    public static void allocate(ArrayList<Integer> arrli[],ArrayList<Long> sum[],int n) {
        arrli=new ArrayList[n+1];
        sum=new ArrayList[n+1];
        for(int i=0;i<arrli.length;i++) {
            arrli[i]=new ArrayList<>();
            sum[i]=new ArrayList<>();
        }
    }
}
