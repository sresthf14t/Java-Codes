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
public class Round_Function_f_K {
    //Expansion D-box Table 
	static int expansion_p_box[]= 
	{ 32,1,2,3,4,5,4,5, 
		6,7,8,9,8,9,10,11, 
		12,13,12,13,14,15,16,17, 
		16,17,18,19,20,21,20,21, 
		22,23,24,25,24,25,26,27, 
		28,29,28,29,30,31,32,1 
	};
        //Straight Permutation Table 
	static int Straight_P_Box[]= 
	{ 16,7,20,21, 
		29,12,28,17, 
		1,15,23,26, 
		5,18,31,10, 
		2,8,24,14, 
		32,27,3,9, 
		19,13,30,6, 
		22,11,4,25 
	}; 
    public String func(String in, String round_key) {         //"in" is the 32 bit String binary value input
        String out="";                     //32 bit String binary value to output after the function completes
        PBox Permute=new PBox();
        Xor ExclusiveOr=new Xor();
        SBoxes Substitution=new SBoxes();
        //Applying the Expansion P-Box
        out=Permute.Pbox(in, expansion_p_box);
        //Applying XOR to the output of the above operation with the round key
        out=ExclusiveOr.Xoring(out, round_key);
        //Applying S-Boxes to the output of the above operation
        out=Substitution.Sbox(out);
        //Applying Straight P-Box to the output of the above operation
        out=Permute.Pbox(out,Straight_P_Box);
        return out;
    }
    public static void main(String atgs[]) {
        Round_Function_f_K fk=new Round_Function_f_K();
        System.out.println(fk.func("11110000101010101111000010101010","000110110000001011101111111111000111000001110010"));
    }
}
