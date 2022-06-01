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
public class PBox {
    public String Pbox(String in,int[] box) {
        String out="";                  // Output after Permutation
        for(int i=0;i<box.length;i++) {     //For premutation
            out+=""+in.charAt(box[i]-1);
        }
        return out;
    }
    public static void main(String atgs[]) {
        PBox P=new PBox();
        int arr[]={3,5,2,7,4,10,1,9,8,6};
        int[] keyp={57,49,41,33,25,17,9, 
		1,58,50,42,34,26,18, 
		10,2,59,51,43,35,27, 
		19,11,3,60,52,44,36,		 
		63,55,47,39,31,23,15, 
		7,62,54,46,38,30,22, 
		14,6,61,53,45,37,29, 
		21,13,5,28,20,12,4 
	};
        int key_comp[]= 
	{ 14,17,11,24,1,5, 
		3,28,15,6,21,10, 
		23,19,12,4,26,8, 
		16,7,27,20,13,2, 
		41,52,31,37,47,55, 
		30,40,51,45,33,48, 
		44,49,39,56,34,53, 
		46,42,50,36,29,32 
	};
        System.out.println("0000‬‭000100000001000000010000000100000001000000010000000100000001".length());
        System.out.println(P.Pbox("0000‬‭000100000001000000010000000100000001000000010000000100000001",keyp));
    }
}
