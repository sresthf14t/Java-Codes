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
public class FIXT {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int k=input.nextInt();
        long seq=1;
        long diff=5;
        for(int i=1;i<k;i++) {
            seq+=diff;
            diff+=4;
        }
        System.out.println(seq);
    }
}
