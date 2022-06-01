package Codeforces_Round_612Div2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import java.util.Scanner;
public class A_Angry_Students {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=1;test<=t;test++) {
            int l=input.nextInt();
            String str=input.next();
            System.out.println(count_time(str));
        }
    }
    public static int count_time(String str) {
        int count=0;
        int len=0;
        boolean p_strt_bool=false,a_bool=false;
        for(int i=1;i<str.length();i++) {
            if(str.charAt(i)=='P' && str.charAt(i-1)=='A') {
                p_strt_bool=true;
                count++;
            }
            else if(p_strt_bool && str.charAt(i)=='P') {
                count++;
            }
            else if(str.charAt(i)=='A') {
                p_strt_bool=false;
                count=0;
            }
            if(count>len) {
                len=count;
            } 
        }
        return len;
    }
}
