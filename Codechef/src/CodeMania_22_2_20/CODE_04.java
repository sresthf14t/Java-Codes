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
import java.util.Scanner;
public class CODE_04 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String s1=input.next();
        String s2=input.next();
        int count1=0,count2=0;
        for(int i=0,j=0;i<s1.length() && j<s2.length();i++) {
            boolean is_present=false;
            int tmp_j=j;
            while(j<s2.length() && s1.charAt(i)!=s2.charAt(j)) {
                if(s1.charAt(i)!=s2.charAt(j)) {
                    is_present=true;
                }
                j++;
            }
            if(!is_present) {
                j=tmp_j;
            }
            if(j<s2.length() && s1.charAt(i)==s2.charAt(j)) {
                count1++;
            }
        }
        
        for(int i=0,j=0;i<s1.length() && j<s2.length();j++) {
            boolean is_present=false;
            int tmp_i=i;
            while(i<s1.length() && s1.charAt(i)!=s2.charAt(j)) {
                if(s1.charAt(i)!=s2.charAt(j)) {
                    is_present=true;
                }
                i++;
            }
            if(!is_present) {
                i=tmp_i;
            }
            if(i<s1.length() && s1.charAt(i)==s2.charAt(j)) {
                count2++;
            }
        }

        
        System.out.println(Math.max(count1, count2));
    }
}
