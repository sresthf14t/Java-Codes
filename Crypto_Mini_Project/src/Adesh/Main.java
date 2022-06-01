/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adesh;



/**
 *
 * @author User
 */
import java.util.Scanner;

public class Main {
    static int key[]=new int[10];
    static String VigKey;
    static Simplified_DES DES=new Simplified_DES();
    static Scanner input=new Scanner(System.in);
    public static void main(String rags[]) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Plaintext");
        String plaintext=sc.nextLine();
        String Ciphertext=encrypt(plaintext);
        System.out.println("\nCiphertext="+Ciphertext);
        String decrypted=decrypt(Ciphertext);
        System.out.println("\nDecrypted="+decrypted);
    }
    public static String encrypt(String plaintext) {
        //Vignere Cipher
        System.out.println("Enter the key for vignere cipher");
        VigKey=input.next();
        String ciphertext=VigenereCipher.Encrypt(plaintext, VigKey);
        //Simplified DES
        System.out.println("Enter the Simplified DES key in binary");
        for(int i=0;i<key.length;i++){
            key[i]=input.nextInt();
        }
        String temp_str="";
        for(int i=0;i<ciphertext.length();i++) {
            int temp_arr[]=DES.Encryption(dec_to_bin(ciphertext.charAt(i)), key);
            int temp_int=0;
            for(int j=0;j<temp_arr.length;j++) {
                temp_int+=(int)(Math.pow(2, (7-j))*temp_arr[j]);
            }
            temp_str+=(char)temp_int;
        }
        ciphertext=temp_str;
        //RSA
        RSA rsa=new RSA();
        temp_str="";
        for(int i=0;i<ciphertext.length();i++) {
            temp_str+=(char)rsa.encrypt(ciphertext.charAt(i));
        }
        ciphertext=temp_str;
        
        return ciphertext;
    }
    public static String decrypt(String ciphertext) {
        
        //RSA
        RSA rsa=new RSA();
        String temp_str="";
        for(int i=0;i<ciphertext.length();i++) {
            temp_str+=(char)rsa.decrypt(ciphertext.charAt(i));
        }
        String plaintext=temp_str;
        
        
        //Simplified DES
        temp_str="";
        for(int i=0;i<plaintext.length();i++) {
            int temp_arr[]=DES.Decryption(dec_to_bin(plaintext.charAt(i)), key);
            int temp_int=0;
            for(int j=0;j<temp_arr.length;j++) {
                temp_int+=(int)(Math.pow(2, (7-j))*temp_arr[j]);
            }
            temp_str+=(char)temp_int;
        }
        plaintext=temp_str;
        //Vignere Cipher
        
        plaintext=VigenereCipher.Decrypt(plaintext, VigKey);
        
        return plaintext;
        
        
    }
    public static int[] dec_to_bin(int in) {
        String out="",temp="";
        while(in!=0) {
            temp+=""+(in%2);
            in/=2;
        }
        //To reverse the String
        for(int i=0;i<temp.length();i++) {
            out=temp.charAt(i)+out;
        }
        //To make out 4 bit binary
        for(int i=out.length();i<8;i++) {
            out="0"+out;
        }
        int[] arr=new int[8];
        for(int i=0;i<8;i++) {
            if(out.charAt(i)=='0') {
                arr[i]=0;
            }
            else {
                arr[i]=1;
            }
        }
        return arr;
    }
        
    }
    
