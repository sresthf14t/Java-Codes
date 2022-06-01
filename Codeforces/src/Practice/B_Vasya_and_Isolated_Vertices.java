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
import java.util.Scanner;
public class B_Vasya_and_Isolated_Vertices {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long m=input.nextLong();
        long min=2*m;
        long max=0;
        while((max*(max-1))/2L<m) {
            max++;
        }
        if(min>=n) {
            System.out.print(0+" ");
        }
        else {
            System.out.print((n-min)+" ");
        }
        if(max>=n) {
            System.out.print(0);
        }
        else {
            System.out.print(n-max);
        }
    }
}
