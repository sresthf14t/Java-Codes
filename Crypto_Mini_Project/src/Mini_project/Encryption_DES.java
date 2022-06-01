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
public class Encryption_DES {
    //Initial Permutation Table 
	static int initial_permutation_table[]= 
	{ 58,50,42,34,26,18,10,2, 
		60,52,44,36,28,20,12,4, 
		62,54,46,38,30,22,14,6, 
		64,56,48,40,32,24,16,8, 
		57,49,41,33,25,17,9,1, 
		59,51,43,35,27,19,11,3, 
		61,53,45,37,29,21,13,5, 
		63,55,47,39,31,23,15,7 
	};
        //Final Permutation Table 
	static int final_permutation_table[]= 
	{ 40,8,48,16,56,24,64,32, 
		39,7,47,15,55,23,63,31, 
		38,6,46,14,54,22,62,30, 
		37,5,45,13,53,21,61,29, 
		36,4,44,12,52,20,60,28, 
		35,3,43,11,51,19,59,27, 
		34,2,42,10,50,18,58,26, 
		33,1,41,9,49,17,57,25 
	};
        static String round_ciphertext[]=new String[16];
    public void encrypt(String plaintext)  {        //Encrypting the plaintext
        String ciphertext="";       //To store final ciphertext
        PBox Permute=new PBox();
        Round_Function_f_K fk=new Round_Function_f_K();
        Xor ExclusiveOr=new Xor();
        //Applying The Initial Permutation
        ciphertext=Permute.Pbox(plaintext, initial_permutation_table);
        String left="",right="";
        //Performing the 16 rounds
        for(int i=0;i<16;i++) {
            //To split the Strig into left and right halves
            left="";
            right="";
            int len=ciphertext.length();
            for(int j=0;j<len;j++) {
                if(j<len/2) {
                    left+=""+ciphertext.charAt(j);
                }
                else {
                    right+=""+ciphertext.charAt(j);
                }
            }
            //To perform round and swapping(except for round 16)
            if(i!=15) {                 
                String temp_right=right;        //To temprarily store the value of right;      
                right=ExclusiveOr.Xoring(left,fk.func(right,KeyGen.allkeys[i]));
                left=temp_right;
            }
            //To perform XOR for the last round
            else {  
                left=ExclusiveOr.Xoring(left,fk.func(right,KeyGen.allkeys[i]));
            }
            ciphertext=left+right;
            //Swapping the two values
            round_ciphertext[i]=ciphertext;
        }
        //Applying The Final Permutation
        ciphertext=Permute.Pbox(ciphertext, final_permutation_table);
        round_ciphertext[15]=ciphertext;
    }
    public static void main(String atgs[]) {
        Scanner input=new Scanner(System.in);
        Encryption_DES encryption=new Encryption_DES();
        System.out.println("Enter the key in hexadecimal:- ");
        String key_hex=input.next();
        System.out.println("Enter the plaintext in hexadecimal:- ");
        String plaintext_hex=input.next();
        Hexa_to_Bin H2B=new Hexa_to_Bin();
        Bin_to_Hexa B2H=new Bin_to_Hexa();
        //Generating the keys using the KeyGen function
        KeyGen.keys(H2B.Convert_Hex_to_Bin(key_hex));
        encryption.encrypt(H2B.Convert_Hex_to_Bin(plaintext_hex));
        System.out.println("Round Keys");
        for(int i=0;i<16;i++) {
            System.out.println(i+".)"+B2H.Convert_Bin_to_Hex(KeyGen.allkeys[i]));
        }
        System.out.println("\nRound Ciphertexts\n");
        for(int i=0;i<16;i++) {
            System.out.println(i+".)"+B2H.Convert_Bin_to_Hex(round_ciphertext[i]));
        }
    }
}
