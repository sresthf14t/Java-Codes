/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _2019_Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Robot_Programming_Strategy {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            String str[]=new String[n];
            int max_len=-1;
            for(int i=0;i<n;i++) {
                str[i]=input.next();
                max_len=Math.max(max_len,str[i].length());
            }
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<=max_len;i++) {
                boolean chr[]=new boolean[3];//R P S
                for(int j=0;j<n;j++) {
                    if(str[j].charAt(i%str[j].length())=='R') {
                        chr[0]=true;
                    }
                    if(str[j].charAt(i%str[j].length())=='P') {
                        chr[1]=true;
                    }
                    if(str[j].charAt(i%str[j].length())=='S') {
                        chr[2]=true;
                    }
                }
                if(chr[0]  && chr[1] && chr[2]) {
                    ans=new StringBuilder("IMPOSSIBLE");
                    break;
                }
                if(chr[0] && chr[1]) {
                    ans.append('P');
                    delete(str,'R',i);
                }
                else if(chr[1] && chr[2]) {
                    ans.append('S');
                    delete(str,'P',i);
                }
                else if(chr[0] && chr[2]) {
                    ans.append('R');
                    delete(str,'S',i);
                }
                else if(chr[0]) {
                    ans.append('P');
                    break;
                }
                else if(chr[1]) {
                    ans.append('S');
                    break;
                }
                else if(chr[2]) {
                    ans.append('R');
                    break;
                }
            }
            System.out.println("Case #"+t+": "+ans);
        }
    }
    public static void delete(String str[],char chr,int indx) {
        for(int i=0;i<str.length;i++) {
            if(str[i].charAt(indx%str[i].length())==chr) {
                str[i]="";
            }
        }
    }
}
