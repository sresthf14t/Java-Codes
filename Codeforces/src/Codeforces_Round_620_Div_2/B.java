/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_620_Div_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class B {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        String str[]=new String[n];
        for(int i=0;i<n;i++) {
            str[i]=input.next();
        }
        String out="",out1="";
        for(int i=0;i<n;i++) {
            if(str[i]=="") {
                continue;
            }
            for(int j=i+1;j<n;j++) {
                if(str[j]=="") {
                    continue;
                }
                if(is_equal(str[i],str[j],m)) {
                    //System.out.println(str[i]+" "+str[j]);
                    out+=str[i];
                    out=rev(str[i])+out;
                    str[i]=str[j]="";
                    break;
                }
            }
        }
        boolean tmp=false;
        if(out.length()%2==0) {
            int indx=-1,len=-1;
            for(int i=0;i<n;i++) {
                if(str[i].equals(rev(str[i])) && str[i].length()>len) {
                    indx=i;
                    len=str[i].length();
                }
            }
            if(out.length()==0 && indx!=-1) {
                out1=str[indx];
                tmp=true;
            }
            if(indx!=-1 && len!=-1) {
                tmp=true;
                for(int i=0;i<out.length();i++) {
                    if(i+1<=out.length()/2) {
                        out1+=out.charAt(i);
                    }
                    if(i+1==out.length()/2) {
                        out1+=str[indx];
                    }
                    if(i+1>out.length()/2) {
                        out1+=out.charAt(i);
                    }
                }
            }
        }
        if(!tmp) {
            out1=out;
        }
        System.out.println(out1.length()+"\n"+out1);
    }
    public static boolean is_equal(String a,String b,int m) {
        int is_alpha_a[]=new int[26];
        int is_alpha_b[]=new int[26];
        for(int i=0;i<m;i++) {
            is_alpha_a[a.charAt(i)-97]++;
            is_alpha_b[b.charAt(i)-97]++;
        }
        for(int i=0;i<26;i++) {
            if(is_alpha_a[i]!=is_alpha_b[i]) {
                return false;
            }
        }
        return true;
    }
    public static String rev(String s) {
        String str="";
        for(int i=0;i<s.length();i++) {
            str=s.charAt(i)+str;
        }
        return str;
    }
}
