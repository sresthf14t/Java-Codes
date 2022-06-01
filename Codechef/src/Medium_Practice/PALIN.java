/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medium_Practice;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.math.BigInteger;  
public class PALIN {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        //System.out.println(compare("2","12"));
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            StringBuilder str=new StringBuilder(input.next());
            StringBuilder str_original=new StringBuilder(str);
            for(int l=0,r=str.length()-1;l<r;l++,r--) {
                str.setCharAt(r, str.charAt(l));
            }
            if(compare(""+str,""+str_original)==-1) {
                
            }
        }
    }
    public static int compare(String str,String str_original) {
        BigInteger edited=new BigInteger(str);
        BigInteger original=new BigInteger(str_original);
        return edited.compareTo(original);
    }
        
}
