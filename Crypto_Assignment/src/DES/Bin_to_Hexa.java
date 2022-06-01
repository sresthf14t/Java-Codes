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
public class Bin_to_Hexa {
    public String Convert_Bin_to_Hex(String in) {       //Convert Binary to Hexadecimal
        String out="";
        for(int i=0;i<in.length();i=i+4) {
            String temp=""+in.charAt(i)+in.charAt(i+1)+in.charAt(i+2)+in.charAt(i+3);
            if(temp.equals("0000")) {
                out+="0";
            }
            else if(temp.equals("0001")) {
                out+="1";
            }
            else if(temp.equals("0010")) {
                out+="2";
            }
            else if(temp.equals("0011")) {
                out+="3";
            }
            else if(temp.equals("0100")) {
                out+="4";
            }
            else if(temp.equals("0101")) {
                out+="5";
            }
            else if(temp.equals("0110")) {
                out+="6";
            }
            else if(temp.equals("0111")) {
                out+="7";
            }
            else if(temp.equals("1000")) {
                out+="8";
            }
            else if(temp.equals("1001")) {
                out+="9";
            }
            else if(temp.equals("1010")) {
                out+="A";
            }
            else if(temp.equals("1011")) {
                out+="B";
            }
            else if(temp.equals("1100")) {
                out+="C";
            }
            else if(temp.equals("1101")) {
                out+="D";
            }
            else if(temp.equals("1110")) {
                out+="E";
            }
            else if(temp.equals("1111")) {
                out+="F";
            }
        }
        return out;
    }
    public static void main(String atgs[]) {
        Bin_to_Hexa B2H=new Bin_to_Hexa();
        System.out.println(B2H.Convert_Bin_to_Hex("100011111100"));
    }
}
