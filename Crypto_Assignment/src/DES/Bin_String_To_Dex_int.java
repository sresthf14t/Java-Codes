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
public class Bin_String_To_Dex_int {
    public int convert(String in) {     //To convert a String binary data to integer decimal
        int out=0;
        int len=in.length();
        for(int i=0;i<len;i++) {
            if(in.charAt(i)=='1') {
                out+=(int)Math.pow(2,len-i-1);
            }
        }
        return out;
    }
    public static void main(String atgs[]) {
        Bin_String_To_Dex_int B2D=new Bin_String_To_Dex_int();
        System.out.println(B2D.convert("11"));
    }
}
