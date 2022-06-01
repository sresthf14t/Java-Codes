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
public class B_Make_Product_Equal_One {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        System.out.println(solve(n,arr));
    }
    public static long solve(int n,long[] arr) {
        long count=0,zero=0,pos=0,neg=0;
        for(int i=0;i<n;i++) {
            if(arr[i]==0) {
                zero++;
                continue;
            }
            if(arr[i]<0) {
                count+=Math.abs(-1-arr[i]);
                arr[i]=-1;
                neg++;
            }
            else {
                count+=Math.abs(1-arr[i]);
                arr[i]=1;
                pos++;
            }
        }
        if(zero==0 && neg%2==1) {
            count+=2;
        }
        return count+zero;
    }
}
