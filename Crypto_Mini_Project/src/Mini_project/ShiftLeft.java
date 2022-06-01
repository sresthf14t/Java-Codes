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
public class ShiftLeft {
    public String LeftShift(String in) {
        String out="";      //Output after left Shift
        for(int i=1;i<in.length();i++) {
            out+=""+in.charAt(i);
        }
        out+=""+in.charAt(0);       //To add the leftmost bit of "in" to rightmost position of "out" for circulat shift
        return out;
    }
    public static void main(String atgs[]) {
        ShiftLeft LSR=new ShiftLeft();
        System.out.println(LSR.LeftShift("1011"));
    }
}
