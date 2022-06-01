/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_New_Skateboard {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        long count=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='4' || str.charAt(i)=='8' || str.charAt(i)=='0') {
                count++;
            }
        }
        for(int i=1;i<str.length();i++) {
            int tmp=(str.charAt(i)-48)+10*(str.charAt(i-1)-48);
            if(tmp%4==0) {
                count+=i;
            }
        }
        System.out.println(count);
    }
}
