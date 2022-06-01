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
public class Swapper {
    public String swap(String in) {
        String out="";
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
        out=right+left;
        return out;
    }
    public static void main(String atgs[]) {
        Swapper Swaping=new Swapper();
        System.out.println(Swaping.swap("110100"));
    }
}
