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
public class NUM239 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            int l=input.nextInt();
            int r=input.nextInt();
            int count=0;
           
            for(;l%10!=0;l++) {
                if(l%10==2||l%10==3||l%10==9) {
                    count++;
                }
            }
            
            for(;r%10!=0;r--) {
                if(r%10==2||r%10==3||r%10==9) {
                    count++;
                }
            }
            
            count+=3*((r-l)/10);
            
            System.out.println(count);
        }
    }
}
