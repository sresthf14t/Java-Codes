/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Fools_Day_Contest_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        StringBuilder str=new StringBuilder(input.next());
        long num=0;
        str=str.reverse();
        for(int i=0;i<str.length()-1;i++) {
            num+=(str.charAt(i)-48)*Math.pow(16, i);
        }
        num+=10*Math.pow(16,str.length()-1);
        System.out.println(num%2);
    }
}
