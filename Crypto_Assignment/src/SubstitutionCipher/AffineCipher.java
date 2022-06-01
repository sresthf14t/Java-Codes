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
public class AffineCipher {
    public static String Additive_cipher(String s,int key) {
		int temp;
		String cipher="";
		for(int i=0;i<s.length();i++) {
			temp=(int)s.charAt(i);
			temp-=65;
			temp+=key;
			temp%=26;
			temp+=65;
			cipher+=(char)temp;
		}
		return cipher;
	}
	public static String Multiplicative_cipher(String s,int key) {
		int temp;
		String cipher="";
		for(int i=0;i<s.length();i++) {
			temp=(int)s.charAt(i);
			temp-=65;
			temp*=key;
			temp%=26;
			temp+=65;
			cipher+=(char)temp;
		}
		return cipher;
	}
	public static String Affine(String s,int key1,int key2) {
		String cipher=Additive_cipher(Multiplicative_cipher(s,key1),key2);
		return cipher;
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Please Enter the String[In capital letters only]");
		String s=input.next();
		System.out.println("Enter the two keys");
		System.out.println("\nCpihrtText: "+Affine(s,input.nextInt(),input.nextInt()));
	}
}
