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
public class DES_Hexa_to_Str {
    public static String Hexa_to_Str(String in) {
        Hexa_to_Bin H2B=new Hexa_to_Bin();
        Bin_String_To_Dex_int B2D=new Bin_String_To_Dex_int();
        String out="";
        for(int i=0;i<in.length();i=i+2) {
            String temp=""+in.charAt(i)+in.charAt(i+1);
            String bin_temp=H2B.Convert_Hex_to_Bin(temp);
            int dec_temp=B2D.convert(bin_temp);
            out+=(char)dec_temp;
        }
        return out;
    }
    public static void main(String args[]) {
        System.out.println(Hexa_to_Str("4E414E444F"));
    }
}
