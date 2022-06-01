/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_DP_Contest;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class T {
    static int n;
    static String s;
    static long dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        s=input.next();
        dp=new long[3001][3001];
        for(int i=0;i<3001;i++) {
            for(int j=0;j<3001;j++) {
                dp[i][j]=-1L;
            }
        }
        System.out.println(solve(-1,new ArrayList<Integer>()));
    }
    public static int solve(int indx,ArrayList<Integer> arr) {
        if(indx==n-1) {
            if(check(arr)) {
                return 1;
            }
            return 0;
        }
        int strt,end;
        if(indx==-1) {
            strt=1;
            end=n;
        }
        else {
            if(s.charAt(indx)=='>') {
                strt=1;
                end=arr.get(indx)-1;
            }
            else {
                strt=arr.get(indx)+1;
                end=n;
            }
        }
        int ways=0;
        for(int i=strt;i<=end;i++) {
            if(dp[indx+1][i]!=-1) {
                ways+=dp[indx+1][i];
            }
            indx++;
            arr.add(i);
            dp[indx][i]=solve(indx,arr);
            ways+=dp[indx][i];
            arr.remove(indx);
            indx--;
        }
        return ways;
    }
    public static boolean check(ArrayList<Integer> arr) {
        boolean bool[]=new boolean[3001];
        for(int i=0;i<arr.size();i++) {
            int indx=arr.get(i);
            if(bool[indx]) {
                return false;
            }
            bool[indx]=true;
        }
        return true;
    }
}
