/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package January_Challenge_Division_2;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class ENGLISH {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            String[] str=new String[n];
            for(int i=0;i<n;i++) {
                str[i]=input.next();
            }
            Arrays.sort(str);
            int match_arr[]=match_arr(str);
            System.out.println(verse(match_arr));
        }
       
    }
    public static int[] match_arr(String str[]) {
        int arr[]=new int[str.length];
        for(int i=0;i<str.length-1;i++) {
            arr[i]=match(str[i],str[i+1]);
        }
        return arr;
    }
    public static int match(String s1,String s2) {
        int len=Math.min(s1.length(), s2.length());
        int count=0;
        for(int i=0;i<len;i++) {
            if(s1.charAt(i)==s2.charAt(i)) {
                count++;
            }
            else {
                break;
            }
        }
        return count;
    }
    
    public static long verse(int arr[]) {
        boolean bool[]=new boolean[arr.length];
        long count_e=0,count_o=0;
        for(int i=0;i<arr.length-1;i++) {
            if(i%2==0) {
                count_e+=(arr[i]*arr[i]);
            }
            else {
                count_o+=(arr[i]*arr[i]);
            }
        }
        return Math.max(count_e, count_o);
    }
}
