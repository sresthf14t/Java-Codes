/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubstitutionCipher;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class PolynomialMultiplication {
    public static String LeftShift(String s) {
        String shifted="";
        for(int i=1;i<s.length();i++) {
            shifted+=s.charAt(i);
        }
        shifted+="0";
        return shifted;
    }
    public static String XOR(String a, String b) {
        String xor="";
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)==b.charAt(i)) {
                xor+="0";
            }
            else {
                xor+="1";
            }
        }
        return xor; 
    }
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter polynomial 1 in binary format: ");
        String p1=input.next();
        System.out.println("Enter polynomial 2 in binary format: ");
        String p2=input.next();
        System.out.println("Enter modulus in binary format: ");
        String mod=input.next();
        //Removing the MSB from mod
        String MOD="";
        for(int i=1;i<mod.length();i++) {
            MOD+=mod.charAt(i);
        }
        mod=MOD;
        //doing X^n x p2
        String temp=p2;
        String res;
        if(p1.charAt(p1.length()-1)=='1') {
            res=p1;
        }
        else {
            res="";
        }
        for(int i=1;i<p1.length()-1;i++) {
            if(temp.charAt(0)=='1') {
                temp=LeftShift(temp);
                temp=XOR(temp,mod);
            }
            else {
                temp=LeftShift(temp);
            }
            if(p1.charAt(7-i)=='1') {
                if(res.length()==0) {
                    res=temp;
                }
                else {
                    res=XOR(res,temp);
                }
            }
        }
        System.out.println("\nResult: "+res);
    }
}
