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
public class Xor {
    public String Xoring(String in1,String in2) {      //Xor of two binary numbers
        String out="";
        for(int i=0;i<in1.length();i++) {
            if(in1.charAt(i)==in2.charAt(i)) {
                out+="0";
            }
            else {
                out+="1";
            }
        }
        return out;
    }
    public static void main(String atgs[]) {
        Xor X=new Xor();
        System.out.println(X.Xoring("1011","1111"));
    }
}
