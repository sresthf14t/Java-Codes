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
public class Hexa_to_Bin {
    public String Convert_Hex_to_Bin(String in) {      //Convert Hexadecimal to Binary
        String out="";
        for(int i=0;i<in.length();i++) {
            if(in.charAt(i)=='0') {
                out+="0000";
            }
            else if(in.charAt(i)=='1') {
                out+="0001";
            }
            else if(in.charAt(i)=='2') {
                out+="0010";
            }
            else if(in.charAt(i)=='3') {
                out+="0011";
            }
            else if(in.charAt(i)=='4') {
                out+="0100";
            }
            else if(in.charAt(i)=='5') {
                out+="0101";
            }
            else if(in.charAt(i)=='6') {
                out+="0110";
            }
            else if(in.charAt(i)=='7') {
                out+="0111";
            }
            else if(in.charAt(i)=='8') {
                out+="1000";
            }
            else if(in.charAt(i)=='9') {
                out+="1001";
            }
            else if(in.charAt(i)=='A') {
                out+="1010";
            }
            else if(in.charAt(i)=='B') {
                out+="1011";
            }
            else if(in.charAt(i)=='C') {
                out+="1100";
            }
            else if(in.charAt(i)=='D') {
                out+="1101";
            }
            else if(in.charAt(i)=='E') {
                out+="1110";
            }
            else if(in.charAt(i)=='F') {
                out+="1111";
            }  
        }
        return out;
    }
    public static void main(String atgs[]) {
        Hexa_to_Bin H2B=new Hexa_to_Bin();
        System.out.println(H2B.Convert_Hex_to_Bin("123456ABCD132536"));
        System.out.println(H2B.Convert_Hex_to_Bin("AABB09182736CCDD"));
    }
}
