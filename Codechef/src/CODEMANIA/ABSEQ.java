/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODEMANIA;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class ABSEQ {
    static Scanner input=new Scanner(System.in);
    public static void main(String ard[]) {
        int l=input.nextInt();
        String str=input.next();
        StringBuilder Str=new StringBuilder(str);
        boolean chng=true;
        int len=l;
        for(int i=0;i<len-1;i++) {
            if(Str.charAt(i)=='a' && Str.charAt(i+1)=='b') {
                Str.delete(i, i+2);
                i-=2;
                if(i<0) {
                    i=-1;
                }
            }
            //System.out.println(Str);
            len=Str.length();
        }
        System.out.println(len);
    }
}
