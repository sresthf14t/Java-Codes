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

//KMP Array[Pi Array] is used to find a suffix which is also a prefix

import java.util.*;
import java.io.*;
public class KMP_Array_Creation {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        int kmp[]=create_KMP_Array(str.length(),str);
        System.out.println("KMP Array[Pi Array] ");
        for(int i=0;i<kmp.length;i++) {
            System.out.print(kmp[i]+" ");
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
