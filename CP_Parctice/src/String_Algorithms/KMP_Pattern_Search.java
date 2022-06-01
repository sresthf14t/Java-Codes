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
public class KMP_Pattern_Search {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String pattern=input.next();
        String txt=input.next();
        search(pattern,txt);
    }
    public static void search(String pattern,String txt) {
        int kmp[]=create_KMP_Array(pattern.length() ,pattern);
        int j=0;
        for(int i=0;i<txt.length();i++) {
            if(pattern.charAt(j)==txt.charAt(i)) {
                j++;
            }
            else {
                if(j!=0) {
                    j=kmp[j-1];
                    i--;
                }
            }
            if(j==pattern.length()) {
                System.out.println("Pattern Found at "+(i-j+1));
                j=kmp[j-1];
            }
        }
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
