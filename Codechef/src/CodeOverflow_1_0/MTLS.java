/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeOverflow_1_0;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class MTLS {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        String key=input.next();
        int count=0;
        for(int i=0;i<str.length()-key.length()+1;i++) {
            String temp="";
            for(int j=i;j<i+key.length();j++) {
                temp+=str.charAt(j);
            }
            if(temp.equals(key)) {
                count++;
            }
        }
        System.out.println(count);
    }
    
}
