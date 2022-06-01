/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Wars_10;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class COWA19F {
    static Scanner input=new Scanner(System.in);
    static char char_arr[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            String s=input.next();
            StringBuilder str=new StringBuilder();
            s=s.toLowerCase();
            int arr[]=new int[26];
            for(int i=0;i<s.length();i++) {
                arr[s.charAt(i)-97]++;
            }
            StringBuilder out=new StringBuilder("");
            for(int i=arr.length-1;i>=0;i--) {
                for(int j=1;j<=arr[i];j++) {
                    out.append(char_arr[i]);
                }
            }
            System.out.println(out);
        }
    }
}
