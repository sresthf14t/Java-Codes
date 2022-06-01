/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2019;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Parcels {
    static int n,m;
    static boolean arr[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            n=input.nextInt();
            m=input.nextInt();
            arr=new boolean[n][m];
            for(int i=0;i<n;i++) {
                String str=input.next();
                for(int j=0;j<m;j++) {
                    if(str.charAt(j)=='1') {
                        arr[i][j]=true;
                    }
                }
            }
            System.out.println("Case #"+t+": "+solve());
        }
    }
    public static int solve() {
        int r=-1,c=-1,dist=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]) {
                    continue;
                }
                int dis=min_dist(i,j);
                if(dis>dist) {
                    dist=dis;
                    r=i;
                    c=j;
                }
            }
        }
//        System.out.println(dist+" "+r+" "+c);
        if(r==-1 && c==-1) {
            return 0;
        }
        arr[r][c]=true;
        dist=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                dist=Math.max(dist,min_dist(i,j));
            }
        }
        return dist;
    }
    public static int min_dist(int r,int c) {
        int dist=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]) {
                    int dis=Math.abs(r-i)+Math.abs(c-j);
                    dist=Math.min(dist, dis);
                }
            }
        }
        return dist;
    }
}
