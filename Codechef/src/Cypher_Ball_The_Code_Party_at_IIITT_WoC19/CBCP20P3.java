/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cypher_Ball_The_Code_Party_at_IIITT_WoC19;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class CBCP20P3 {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int n=input.nextInt();
        char[] strt=new char[n];
        char[] end=new char[n];
        String tmp;
        boolean cha[]=new boolean[26];
        for(int i=0;i<n;i++) {
            tmp=input.next();
            tmp=tmp.toLowerCase();
            strt[i]=tmp.charAt(0);
            end[i]=tmp.charAt(tmp.length()-1);
            if(tmp.charAt(0)==tmp.charAt(tmp.length()-1)) {
                cha[tmp.charAt(0)-97]=true;
            }
        }
        Arrays.sort(strt);
        Arrays.sort(end);
        boolean cmp=true;
        for(int i=0,j=0;i<26 && j<n;i++) {
            int count=0;
            if(cha[i]) {
                char ch=(char)(i+97);
                while(j<n) {
                    if(strt[j]==ch) {
                        count++;
                    }
                    else if(strt[j]>ch) {
                        break;
                    }
                    j++;
                }
                if(count==1) {
                    cmp=false;
                    break;
                }
            }
        }
        if(n==1) {
            cmp=true;
        }
        for(int i=0;i<n;i++) {
            if(strt[i]!=end[i]) {
                cmp=false;
                break;
            }
        }
        if(cmp) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
