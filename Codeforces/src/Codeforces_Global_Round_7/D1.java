/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_7;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class D1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            StringBuilder str=new StringBuilder(input.next());
            int lenfb=0,lenfb_l=-1,lenfb_r=-1;
//            for(int i=0;i<str.length();i++) {
//                if(check_pal(str,0,i)) {
//                    lenf=i+1;
//                    lenf_r=i;
//                }
//            }
//            for(int i=str.length()-1;i>=0;i--) {
//                if(check_pal(str,i,str.length()-1)) {
//                    lenb=str.length()-i+1;
//                    lenb_l=i;
//                }
//            }
            for(int i=0,j=str.length()-1;;i++,j--) {
                if(i>=j) {
                    break;
                }
                if(str.charAt(i)==str.charAt(j) && i!=j) {
                    lenfb+=2;
                    lenfb_l=i;
                    lenfb_r=j;
                }
                else {
                    int tmp_lenfb1=lenfb,tmp_lenfb2=lenfb,tmp_lenfb1_l=i,tmp_lenfb2_r=j;
                    for(int k=i;k<=j;k++) {
                        if(check_pal(str,i,k)) {
                            tmp_lenfb1=lenfb+(k-i+1);
                            tmp_lenfb1_l=k;
                        }
                    }
                    for(int k=j;k>=i;k--) {
                        if(check_pal(str,k,j)) {
                            tmp_lenfb2=lenfb+(j-k+1);
                            tmp_lenfb2_r=k;
                        }
                    }
                    if(tmp_lenfb1>=tmp_lenfb2) {
                        lenfb=tmp_lenfb1;
                        lenfb_l=tmp_lenfb1_l;
                    }
                    else {
                        lenfb=tmp_lenfb2;
                        lenfb_r=tmp_lenfb2_r;
                    }
                    break;
                }
                if(i==j) {
                    lenfb+=1;
                }
                
            }
            if(lenfb_l==-1) {
                System.out.println(str.substring(lenfb_r, str.length()));  
            }
            else if(lenfb_r==-1) {
                System.out.println(str.substring(0,lenfb_l+1));  
            }
            else {
              System.out.println(str.substring(0,lenfb_l+1)+str.substring(lenfb_r, str.length()));  
            }
//            if(lenf>=lenb && lenf>=lenfb) {
//                System.out.println(str.substring(0, lenf_r+1));
//            }
//            else if(lenb>=lenf && lenb>=lenfb) {
//                System.out.println(str.substring(lenb_l, str.length()));
//            }
//            else {
//                System.out.println(str.substring(0,lenfb_l+1)+str.substring(lenfb_r, str.length()));
//            }
        }
    }
    public static boolean check_pal(StringBuilder str,int l,int r) {
        for(int i=l,j=r;;i++,j--) {
            if(i>=j) {
                break;
            }
            if(str.charAt(i)!=str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
