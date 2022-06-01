/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mini_project;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Main {
    static Scanner input=new Scanner(System.in);
    public static void main(String rags[]) {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------------ENCRYPTION------------------");
        System.out.println("Enter the Plaintext[in Capital letters]");
        String plaintext=sc.nextLine();
        String Ciphertext=encryption(plaintext);
        System.out.println("\nCiphertext="+Ciphertext);
        System.out.println("\n------------------DECRYPTION------------------");
        String decrypted=decryption(Ciphertext);
        System.out.println("\nDecrypted="+decrypted);
    }
    public static String encryption(String plaintext) {
        //Using Additive Cipher
        String middletext=Additive_cipher_methord_encryption(plaintext);
        //Using DES Cipher
        String splitted[]=SplitString(middletext);
        System.out.println("Enter the key for DES in Hexadecimal");
        String key=input.next();
        middletext="";
        for(int i=0;i<splitted.length;i++) {
            middletext+=DES_encryption(splitted[i],key);
        }
        //Using RSA Cryptosystem
        String ciphertext=RSA_encryption(middletext);
        return ciphertext;
    }
    public static String decryption(String Ciphertext) {
        //Using RSA Cryptosystem
        String middletext=RSA_decryption(Ciphertext);
        //Using DES Cipher
        String splitted[]=SplitString(middletext);
        System.out.println("Enter the key for DES in Hexadecimal");
        String key=input.next();
        middletext="";
        for(int i=0;i<splitted.length;i++) {
            middletext+=DES_decryption(splitted[i],key);
        }
        //Removing appennded '#' form string
        String temp="";
        for(int i=0;i<middletext.length();i++) {
            if(middletext.charAt(i)!='#') {
                temp+=middletext.charAt(i);
            }
        }
        middletext=temp;
        //Using Additive Cipher
        middletext=Additive_cipher_methord_decryption(middletext);
        return middletext;
    }
    public static String Additive_cipher_methord_encryption(String in) {
        System.out.println("Enter the key for Additive Cipher");
        int key=input.nextInt();
        Additive_Cipher add_cip=new Additive_Cipher();
        String out=add_cip.encipher(in,key);
        return out;
    }
    public static String Additive_cipher_methord_decryption(String in) {
        System.out.println("Enter the key for Additive Cipher");
        int key=input.nextInt();
        Additive_Cipher add_cip=new Additive_Cipher();
        String out=add_cip.decipher(in,key);
        return out;
    }
    public static String DES_encryption(String in,String key) {
        DES_Str_to_Hexa str_hexa=new DES_Str_to_Hexa();
        String hexa_in=str_hexa.str_to_hexa(in);
        Hexa_to_Bin H2B =new Hexa_to_Bin();
        Bin_to_Hexa B2H=new Bin_to_Hexa();
        //Generating the keys using the KeyGen function
        KeyGen.keys(H2B.Convert_Hex_to_Bin(key));
        Encryption_DES encryption=new Encryption_DES();
        encryption.encrypt(H2B.Convert_Hex_to_Bin(hexa_in));
        String ciphertext=B2H.Convert_Bin_to_Hex(encryption.round_ciphertext[15]);
        DES_Hexa_to_Str hexa_str=new DES_Hexa_to_Str();
        ciphertext=hexa_str.Hexa_to_Str(ciphertext);
        return ciphertext;
    }
    public static String[] SplitString(String s) {
        String splitted[]=new String[(int)Math.ceil(s.length()/8.0)];
        for(int i=0;i<splitted.length;i++) {
            splitted[i]="";
            for(int j=i*8;j<((i+1)*8)&&j<s.length();j++) {
                splitted[i]+=""+s.charAt(j);
            }
        }
        //Replacing empty spaces with '#'
            while(splitted[splitted.length-1].length()!=8) {
                splitted[splitted.length-1]+="#";
            } 
        return splitted;
    }
    public static String DES_decryption(String in,String key) {
        DES_Str_to_Hexa str_hexa=new DES_Str_to_Hexa();
        String hexa_in=str_hexa.str_to_hexa(in);
        Hexa_to_Bin H2B =new Hexa_to_Bin();
        Bin_to_Hexa B2H=new Bin_to_Hexa();
        //Generating the keys using the KeyGen function
        KeyGen.keys(H2B.Convert_Hex_to_Bin(key));
        Decryption_DES decryption=new Decryption_DES();
        decryption.decrypt(H2B.Convert_Hex_to_Bin(hexa_in));
        String plaintext=B2H.Convert_Bin_to_Hex(decryption.round_plaintext[15]);
        DES_Hexa_to_Str hexa_str=new DES_Hexa_to_Str();
        plaintext=hexa_str.Hexa_to_Str(plaintext);
        return plaintext;
    }
    public static String RSA_encryption(String plaintext) {
        RSA rsa=new RSA();
        String ciphertext="";
        for(int i=0;i<plaintext.length();i++) {
            ciphertext+=(char)rsa.encrypt(plaintext.charAt(i));
        }
        return ciphertext;
    }
    public static String RSA_decryption(String ciphertext) {
        RSA rsa=new RSA();
        String plaintext="";
        for(int i=0;i<ciphertext.length();i++) {
            plaintext+=(char)rsa.decrypt(ciphertext.charAt(i));
        }
        return plaintext;
    }
}
