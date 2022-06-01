/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeMania_22_2_20;

/**
 *
 * @author User
 */
public class Test_1 {
    public static void main(String args[]) {
        for(int i=0;i<999999999;i++) {
            String str=""+i;
            boolean four=true,is_pos=true;
            for(int j=0;j<str.length();j++) {
                if(str.charAt(j)=='0' || str.charAt(j)=='4');
                else {
                    is_pos=false;
                    break;
                }
                if(four) {
                    if(str.charAt(j)=='0') {
                        four=false;
                    }
                }
                if(!four) {
                    if(str.charAt(j)=='4') {
                        is_pos=false;
                        break;
                    }
                }
            }
            if(is_pos) {
                System.out.print(i+",");
            }
        }
    }
}
