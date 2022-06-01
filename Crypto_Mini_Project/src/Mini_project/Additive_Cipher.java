/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mini_project;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Additive_Cipher {
        public static String encipher(String s,int key) {
		int temp;
		String cipher="";
		for(int i=0;i<s.length();i++) {
                    if(s.charAt(i)==' ') {
                        cipher+=" ";
                        continue;
                    }
			temp=(int)s.charAt(i);
			temp-=65;
			temp+=key;
			temp%=26;
			temp+=65;
			cipher+=(char)temp;
		}
		return cipher;
	}
        public static String decipher(String s,int key) {
            int temp;
		String plaintxt="";
		for(int i=0;i<s.length();i++) {
                    if(s.charAt(i)==' ') {
                        plaintxt+=" ";
                        continue;
                    }
			temp=(int)s.charAt(i);
			temp-=65;
			temp-=key;
			temp%=26;
                        if(temp<0) {    //To manage negetive results
                            temp+=26;
                        }
			temp+=65;
			plaintxt+=(char)temp;
		}
		return plaintxt;
        }
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
                System.out.println("Enter the plaintext and the key [in capital letters]");
                String plaintext=input.nextLine();
                
                input=new Scanner(System.in);
                int key=input.nextInt();
                key%=26;
                String ciphertext=encipher(plaintext,key);
		System.out.println("\nCiphrText: "+ciphertext);
                String decipheredTxt=decipher(ciphertext,key);
                System.out.println("\nPlainText: "+decipheredTxt);
                
	}
    
}
