/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Easy_Practice;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class DISCHAR_1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            String str=input.next();
            System.out.println(distinct_chars(str));
        }
    }
    public static int distinct_chars(String str) {
        boolean[] str_bool=new boolean[str.length()];
        for(int i=0;i<str_bool.length;i++) {
            if(!str_bool[i]) {
                for(int j=i+1;j<str.length();j++) {
                    if(str.charAt(i)==str.charAt(j)) {
                        str_bool[j]=true;
                    }
                }
            }
        }
        int count=0; 
        for(int i=0;i<str_bool.length;i++) {
            //System.out.print(str_bool[i]+"  ");
            if(!str_bool[i]) {
                count++;
            }
        }
        return count;
    }
}
