/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adesh;

//CODE TO ENCIPHER AND DECIPHER VIGENERE CIPHER
//USE CAPITAL LETTERS ONLY
/**
 *
 * @author User
 */
import java.util.Scanner;
public class VigenereCipher {
    public static String Encrypt(String s,String key) {			//FOR ENCRYPTION
		String encrypt="";
		char temp;
		for(int i=0,j=0;i<s.length();i++,j=(j+1)%key.length()) {
			temp=(char)(s.charAt(i)+key.charAt(j));
			temp=(char)((temp%26)+65);
			encrypt+=temp;
		}
		return encrypt;
	}
	public static String Decrypt(String s,String key) {		
		String decrypt="";
		int temp;
		for(int i=0,j=0;i<s.length();i++,j=(j+1)%key.length()) {	//FOR DECRYPTION
			temp=(s.charAt(i)-key.charAt(j));
			temp=(temp%26);
			if((int)temp<0)
				temp+=26;
			temp+=65;
			decrypt+=(char)temp;
		}
		return decrypt;
	}
	public static void main(String args[]) {		
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the Plaintext String[USE CAPITAL LETTERS ONLY]");
		String s=input.next();
		System.out.println("Enter the Key String");
		String key=input.next();
		String en=Encrypt(s,key);
		System.out.println("Encrypted String: "+en);
		String de=Decrypt(en,key);
		System.out.println("\nDencrypted String: "+de);
	}
}
