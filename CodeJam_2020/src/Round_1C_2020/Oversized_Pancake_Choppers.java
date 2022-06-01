/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_1C_2020;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class Oversized_Pancake_Choppers {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int d=input.nextInt();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextLong();
            }
            System.out.println("Case #"+t+": "+solve(n,d,arr));
        }
    }
    public static int solve(int n,int d,long arr[]) {
        Arrays.sort(arr);
        if(d==2) {
            for(int i=0;i<n;i++) {
                for(int j=i+1;j<n;j++) {
                    if(arr[i]==arr[j]) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                for(int k=j+1;k<n;k++) {
                    if(arr[i]==arr[j] && arr[j]==arr[k]) {
                        return 0;
                    }
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(2*arr[i]==arr[j]) {
                    return 1;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(arr[i]==arr[j] && j!=n-1) {
                    return 1;
                }
            }
        }
         return 2;
    }
}
