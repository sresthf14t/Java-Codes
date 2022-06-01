/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Challenge_2020_Division2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class LONGCOOK_Test {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int year_strt=input.nextInt();
        int year_end=input.nextInt();
        StringBuilder str=new StringBuilder("");
        String day[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        int ref=3;
        for(int i=1;i<=year_end;i++) {
            str.append(""+ref);
            if(!is_leap(i)) {
                ref=(ref+1)%7;
            }
            else {
                ref=(ref+2)%7;
            }
        }
        //System.out.println(str);
        //find_rep(str);
        check_rep(str);
    }
    
    public static void find_rep(StringBuilder str) {
        for(int len=2;len<=str.length()/2;len++) {
            StringBuilder tmp=new StringBuilder("");
            for(int i=0;i<=len;i++) {
                tmp.append(str.charAt(i));
            }
            boolean is_rep=true;
            for(int i=0,j=0;i<str.length();i++,j=(j+1)%len) {
                if(str.charAt(i)!=tmp.charAt(j)) {
                    is_rep=false;
                    break;
                }
            }
            if(is_rep) {
                System.out.println(tmp+"\n"+tmp.length());
                break;
            }
        }
    }
    public static void check_rep(StringBuilder str) {
        boolean is_rep=true;
        StringBuilder tmp=new StringBuilder("3456123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234560124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501234560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501235601");
        int len=tmp.length();
        for(int i=0,j=0;i<str.length();i++,j=(j+1)%len) {
            if(str.charAt(i)!=tmp.charAt(j)) {
                is_rep=false;
                break;
            }
        }
        System.out.println(is_rep);
        
    }
    
    public static boolean is_leap(int year) {
        if(year%400==0) {
            return true;
        }
        else if(year%4==0 && year%100!=0) {
            return true;
        }
        return false;
    }
}
