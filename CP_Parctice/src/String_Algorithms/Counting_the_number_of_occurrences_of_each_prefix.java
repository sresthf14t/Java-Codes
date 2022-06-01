/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String_Algorithms;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Counting_the_number_of_occurrences_of_each_prefix {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        count_prefixes(str.length(), str);
    }
    public static void count_prefixes(int n,String str) {
        int ans[]=new int[n+1];
        int kmp[]=create_KMP_Array(n,str);
        for(int i=0;i<n;i++) {
            ans[kmp[i]]++;
        }
        for(int i=n-1;i>0;i--) {
            ans[kmp[i-1]]+=ans[i];
        }
        for(int i=0;i<=n;i++) {
            ans[i]++;
        }
        for(int i=0;i<n;i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
    public static int[] create_KMP_Array(int n,String str) {
        int kmp[]=new int[n];
        kmp[0]=0;
        for(int i=1;i<n;i++) {
            int j=kmp[i-1];
            while(j>0 && str.charAt(i)!=str.charAt(j)) {
                j=kmp[j-1];
            }
            if(str.charAt(i)==str.charAt(j)) {
                j++;
            }
            kmp[i]=j;
        }
        return kmp;
    }
}
