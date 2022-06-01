/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Easy_Practice;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class TOTR {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        String eng="abcdefghijklmnopqrstuvwxyz";
        String ENG="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str=input.next();
        String STR=str.toUpperCase();
        for(int test=0;test<t;test++) {
            String in=input.next();
            String trans="";
            for(int j=0;j<in.length();j++) {
                if(in.charAt(j)>='a'&&in.charAt(j)<='z') {
                    trans+=str.charAt(pos_of_char(eng,in.charAt(j)));
                }
                else if(in.charAt(j)>='A'&&in.charAt(j)<='Z') {
                    trans+=STR.charAt(pos_of_char(ENG,in.charAt(j)));
                }
                else if(in.charAt(j)=='_') {
                    trans+=' ';
                }
                else { 
                    trans+=in.charAt(j);
                }
            }
            System.out.println(trans);
        }
    }
    public static int pos_of_char(String s, char key) {
        int indx=0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)==key) {
                indx=i;
                break;
            }
        }
        return indx;
    }
}
