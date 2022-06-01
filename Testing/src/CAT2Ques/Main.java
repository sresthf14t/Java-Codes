/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAT2Ques;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        String str=input.next();
        boolean is_pal=true;
        for(int i=0,j=str.length()-1;i<=j;i++,j--) {
            if(str.charAt(i)!=str.charAt(j)) {
                is_pal=false;
                break;
            }
        }
        if(is_pal) {
            System.out.println(str+" :palindrome");
        }
        else {
            System.out.println(str+" :not a palindrome");
        }
    }
}