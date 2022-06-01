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
public class C_Classy_Numbers {
    static int min[],max[],lim;
    static long dp[][][][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long L=input.nextLong();
            long R=input.nextLong();
            min=new int[(""+L).length()];
            max=new int[(""+R).length()];
            long tmp=L;
            for(int i=min.length-1;i>=0;i--) {
                min[i]=(int)(tmp%10);
                tmp/=10;
            }
            tmp=R;
            for(int i=max.length-1;i>=0;i--) {
                max[i]=(int)(tmp%10);
                tmp/=10;
            }
            long ans=0;
            for(int i=min.length;i<=max.length;i++) {
                lim=i;
                dp=new long[i][4][2][2];
                fill();
                ans+=solve(0,0,(i==max.length),(i==min.length));
            }
            System.out.println(ans);
        }
    }
    public static long solve(int indx,int n_zero,boolean u_lim,boolean l_lim) {
//        System.out.println(indx+" "+n_zero+" "+u_lim+" "+l_lim);
        if(indx==lim) {
            return 1;
        }
        if(dp[indx][n_zero][u_lim?0:1][l_lim?0:1]!=-1) {
            return dp[indx][n_zero][u_lim?0:1][l_lim?0:1];
        }
        long cnt=0;
        for(int i=0;i<10;i++) {
            if(indx==0 && i==0) {
                continue;
            }
            if(n_zero==3 && i>0) {
                break;
            }
            if(l_lim && i<min[indx]) {
                continue;
            }
            if(u_lim && i>max[indx]) {
                continue;
            }
            if(l_lim && i==min[indx] && u_lim && i==max[indx]) {
                cnt+=solve(indx+1,(i!=0?n_zero+1:n_zero),true,true);
                continue;
            }
            if(l_lim && i==min[indx]) {
                cnt+=solve(indx+1,(i!=0?n_zero+1:n_zero),false,true);
                continue;
            }
            if(u_lim && i==max[indx]) {
                cnt+=solve(indx+1,(i!=0?n_zero+1:n_zero),true,false);
                continue;
            }
            cnt+=solve(indx+1,(i!=0?n_zero+1:n_zero),false,false);
        }
        dp[indx][n_zero][u_lim?0:1][l_lim?0:1]=cnt;
        return cnt;
    }
    public static void fill() {
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[i].length;j++) {
                for(int k=0;k<dp[i][j].length;k++) {
                    for(int l=0;l<dp[i][j][k].length;l++) {
                        dp[i][j][k][l]=-1;
                    }
                }
            }
        }
    }
}
