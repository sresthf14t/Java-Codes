/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programming_Contest_1_Chapter_Ramayan;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class STIMEMCH {
    static int n,arr[][],x,y,k;
    static double dp[][][];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.nextInt();
            }
        }
        StringBuilder ans=new StringBuilder("");
        int query=input.nextInt();
        dp=new double[10][10][100001];   //dp[x][y][k]
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                for(int K=0;K<100001;K++) {
                    dp[i][j][K]=-1;
                }
            }
        }
        for(int q=1;q<=query;q++) {
            x=input.nextInt()-1;
            y=input.nextInt()-1;
            k=input.nextInt();
            ans.append(solve(x,k));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static double solve(int curr,int rem) {
        if(rem==0) {
            if(curr==y) {
                return 1;
            }
            return 0;
        }
        if(dp[curr][y][rem]!=-1) {
            return dp[curr][y][rem];
        }
        float sum=0,tot=0;
        for(int i=0;i<n;i++) {
            tot+=arr[curr][i];
        }
        for(int i=0;i<n;i++) {
            if(i==curr) {
                continue;
            }
            if(arr[curr][i]!=0) {
                sum+=(arr[curr][i]/tot)*solve(i,rem-1);
            }
        }
        dp[curr][y][rem]=sum;
        return sum;
    }
}
