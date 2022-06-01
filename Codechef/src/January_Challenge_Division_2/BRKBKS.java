/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package January_Challenge_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class BRKBKS {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            int s=input.nextInt();
            int w[]=new int[3];
            for(int i=0;i<w.length;i++) {
                w[i]=input.nextInt();
            }
            
            int n_hits1=1,s_temp=s;
            for(int i=0;i<w.length;i++) {
                if(s_temp>=w[i]) {
                    s_temp-=w[i];
                }
                else {
                    s_temp=s;
                    s_temp-=w[i];
                    n_hits1++;
                }
            }
            
            int n_hits2=1;
            s_temp=s;
            for(int i=w.length-1;i>=0;i--) {
                if(s_temp>=w[i]) {
                    s_temp-=w[i];
                }
                else {
                    s_temp=s;
                    s_temp-=w[i];
                    n_hits2++;
                }
            }
            
            int n_hits;
            if(n_hits1<n_hits2) {
                n_hits=n_hits1;
            }
            else {
                n_hits=n_hits2;
            }
            System.out.println(n_hits);
            
        }
    }
}
