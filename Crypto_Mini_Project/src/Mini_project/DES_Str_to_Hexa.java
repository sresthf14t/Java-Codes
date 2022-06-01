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

//Using the ASCII value of the charecters in String to convert to Hexadecinal
public class DES_Str_to_Hexa {
    public static String str_to_hexa(String in) {
        Dec_int_To_Bin_String D2B=new Dec_int_To_Bin_String();
        Bin_to_Hexa B2H=new Bin_to_Hexa();
        String out="";
        for(int i=0;i<in.length();i++) {
            String bin_temp=D2B.convert((int)in.charAt(i));
            while(!(bin_temp.length()%8==0)) {
                bin_temp="0"+bin_temp;
            }
            String hex_temp=B2H.Convert_Bin_to_Hex(bin_temp);
            out+=hex_temp;
        }
        return out;
    }
    public static void main(String args[]) {
        System.out.println(str_to_hexa("¹ó}á­}"));
        System.out.println("¹ó}á­}".length());
    }
}
