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
public class TATU {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int x=input.nextInt();
        int y=input.nextInt();
        int count=0;
        int lim=Math.min(x, y);
        for(int i=1;i<=lim/2;i++) {
            if(x%i==0 && y%i==0) {
                count++;
            }
        }
        if(x%y==0 || y%x==0) {
            count++;
        }
        System.out.println(count);
    }
}
