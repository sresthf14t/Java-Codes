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
public class Template {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            ans.append("Case #"+t+": "+solve(n,k,arr));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int k,int arr[]) {
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(arr[i]!=k) {
                continue;
            }
            int check=k;
            boolean is_pos=false;
            int j=i;
            while(j<n) {
                if(arr[j]!=check) {
                    break;
                }
                if(check==1) {
                    is_pos=true;
                    break;
                }
                j++;
                check--;
            }
            if(is_pos) {
                cnt++;
                i=j-1;
            }
        }
        return cnt;
    }
}
