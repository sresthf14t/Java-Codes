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
public class C {
    static int n;
    static long play[],rem[],max,min;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.nextInt();
            play=new long[n];
            rem=new long[n];
            long diff[]=new long[n];
            boolean inf=false;
            for(int i=0;i<n;i++) {
                play[i]=input.nextInt();
                rem[i]=input.nextInt();
                diff[i]=rem[i]-play[i];
            }
            if(n==1) {
                ans.append("Case #"+t+": ");
                ans.append(0+" "+play[0]);
                ans.append("\n");
                continue;
            }
            Arrays.sort(diff);
            long sum=0;
            int cnt=0;
            for(int i=n-1;i>=0;i--) {
                sum+=diff[i];
//                System.out.println(diff[i]);
                if(sum>=0) {
                    cnt++;
                }
                else {
                    break;
                }
            }
//            System.out.println(cnt);
            ans.append("Case #"+t+": ");
            long max=check(new boolean[n]);
            if(max==Long.MAX_VALUE) {
                ans.append(0+" "+"INDEFINITELY");
                ans.append("\n");
            }
            else if(cnt>0) {
                ans.append((n-cnt)+" "+"INDEFINITELY");
                ans.append("\n");
            }
            else {
                ans.append(0+" "+max);
                ans.append("\n");
            }
        }
        System.out.print(ans);
    }
    public static long check(boolean vis[]) {
        boolean tmp=true;
        for(int i=0;i<n;i++) {
            tmp&=vis[i];
        }
        if(tmp) {
            return 0;
        }
        long last[]=new long[n];
        Arrays.fill(last, -1000000000000L);
        long time=0;
        boolean inf=true;
        for(int t=0;t<10;t++) {
            for(int i=0;i<n;i++) {
                if(vis[i]) {
                    continue;
                }
//                System.out.println(time+" "+last[i]);
                if(Math.abs(last[i]-time)<rem[i]) {
                    inf=false;
                    break;
                }
                time+=play[i];
                last[i]=time;
            }
            if(!inf) {
                break;
            }
        }
        if(!inf) {
            return time;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(vis[i]+" ");
//        }
//        System.out.println();
        return Long.MAX_VALUE;
    }
}