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
public class Dec_int_To_Bin_String {
    public String convert(int in) {     //To convert decimal to 4 bit binary String
        String out="",temp="";
        while(in!=0) {
            temp+=""+(in%2);
            in/=2;
        }
        //To reverse the String
        for(int i=0;i<temp.length();i++) {
            out=temp.charAt(i)+out;
        }
        //To make out 4 bit binary
        for(int i=out.length();i<4;i++) {
            out="0"+out;
        }
        return out;
    }
    public static void main(String atgs[]) {
        Dec_int_To_Bin_String D2B=new Dec_int_To_Bin_String();
        System.out.println(D2B.convert(7));
    }
}
