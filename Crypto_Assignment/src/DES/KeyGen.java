/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES;

/**
 *
 * @author User
 */
public class KeyGen {
    //To Store all to generated keys
    static String[] allkeys=new String[16];
    //Parity bit drop table 
       static int[] parity_table=
       {57,49,41,33,25,17,9, 
		1,58,50,42,34,26,18, 
		10,2,59,51,43,35,27, 
		19,11,3,60,52,44,36,		 
		63,55,47,39,31,23,15, 
		7,62,54,46,38,30,22, 
		14,6,61,53,45,37,29, 
		21,13,5,28,20,12,4 
	};
       //Key- Compression Table 
	static int key_compression_box[]= 
	{ 14,17,11,24,1,5, 
		3,28,15,6,21,10, 
		23,19,12,4,26,8, 
		16,7,27,20,13,2, 
		41,52,31,37,47,55, 
		30,40,51,45,33,48, 
		44,49,39,56,34,53, 
		46,42,50,36,29,32 
	};
        //Number of bit shifts 
	static int shift_table[]= 
	{ 1, 1, 2, 2, 
		2, 2, 2, 2, 
		1, 2, 2, 2, 
		2, 2, 2, 1 
	}; 
    public static void keys(String key_64) {

        PBox Permute=new PBox();
        ShiftLeft LSR=new ShiftLeft();
        //Performing Perity drop on the input 64 bit key
        String Pdrop_key_56=Permute.Pbox(key_64,parity_table);
        String key_56=Pdrop_key_56;
        for(int i=0;i<16;i++) {
            //Splliting the key into left and right half
            String key_28_left="",key_28_right="";
            int len=Pdrop_key_56.length();
            for(int j=0;j<len;j++) {
                if(j<len/2) {
                    key_28_left+=""+key_56.charAt(j);
                }
                else {
                    key_28_right+=""+key_56.charAt(j);
                }
            }
            //Left Shifting the two parts
            for(int j=1;j<=shift_table[i];j++) {
                key_28_left=LSR.LeftShift(key_28_left);
                key_28_right=LSR.LeftShift(key_28_right);
            }
            key_56=key_28_left+key_28_right;
            //Applying Compression P-Box
            String key_48=Permute.Pbox(key_56,key_compression_box);
            allkeys[i]=key_48;
        }
    }
    public static void main(String atgs[]) {
        System.out.println("Input Key=0001001100110100010101110111100110011011101111001101111111110001");
        System.out.println("Round Keys");
        keys("0001001100110100010101110111100110011011101111001101111111110001");
        System.out.println();
        for(int i=0;i<allkeys.length;i++) {
            System.out.println(i+".) "+allkeys[i]);
        }
    }
}
