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
public class Splliting_String {
    public static void Split(String in) {
        String left="",right="";
        int len=in.length();
        for(int i=0;i<len;i++) {
            if(i<len/2) {
                left+=""+in.charAt(i);
            }
            else {
                right+=""+in.charAt(i);
            }
        }
        System.out.println(left+" "+right);
    }
    public static void main(String atgs[]) {
        Split("100011");
    }
}
