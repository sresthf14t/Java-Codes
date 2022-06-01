/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_B_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Robot_Path_Decoding {
    static long x,y,mod;
    static String str;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            str=input.next();
            x=1;
            y=1;
            mod=1000000000L;
            solve(1,0,str.length()-1);
            System.out.println("Case #"+t+": "+x+" "+y);
        }
    }
    public static void solve(long rep,int strt,int end) {
        rep%=mod;
//        System.out.println(rep+" "+strt+" "+end);
//        System.out.println(str.charAt(strt)+" "+str.charAt(end));
        for(int i=strt;i<=end;i++) {
//            System.out.println(str.charAt(i));
            if(str.charAt(i)=='S') {
                y=(y+rep);
                check();
            }
            else if(str.charAt(i)=='N') {
                y=(y-rep);
                check();
            }
            else  if(str.charAt(i)=='E') {
                x=(x+rep);
                check();
            }
            else if(str.charAt(i)=='W') {
                x=(x-rep);
                check();
            }
            else if(str.charAt(i)==')') {
                
            }
            else {
                int n=str.charAt(i)-48;
                i+=2;
                int new_strt=i;
                int open=1,close=0;
                while(open!=close) {
                    if(str.charAt(i)=='(') {
                        open++;
                    }
                    if(str.charAt(i)==')') {
                        close++;
                    }
                    i++;
                }
                int new_end=i-2;
                i--;
                solve(n*rep,new_strt,new_end);
            }
            
        }
    }
    public static void check() {
        if(x>mod) {
            x-=mod;
        }
        if(x<=0) {
            x+=mod;
        }
        if(y>mod) {
            y-=mod;
        }
        if(y<=0) {
            y+=mod;
        }
    }
}
