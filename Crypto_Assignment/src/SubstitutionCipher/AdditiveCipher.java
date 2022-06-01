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
public class AdditiveCipher {
    public static String encipher(String s,int key) {
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
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
                System.out.println("Enter the plaintext and the key [in capital letters]");
		System.out.println("\nCiphrText: "+encipher(input.next(),input.nextInt()));
	}
}
