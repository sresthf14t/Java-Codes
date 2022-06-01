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
public class KOL15A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            String str=input.next();
            int sum=0;
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)>='0'&&str.charAt(i)<='9') {
                    sum+=str.charAt(i)-48;
                }
            }
            System.out.println(sum);
        }
        
    }
}
