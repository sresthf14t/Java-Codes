/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Challenge_2019_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Hard_Sequence {
    static int[] seq=new int[128];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        create_array();
        int t=0;
        if(input.hasNextInt()) {
           t=input.nextInt(); 
        }
        for(int i=0;i<t;i++) {
            int n=0;
            if(input.hasNextInt()) {
                n=input.nextInt();
            }
            System.out.println(count(n));
        }
    }
    public static int count(int n) {
        int count=0;
        for(int i=0;i<n;i++) {
            if(seq[i]==seq[n-1]) {
                count++;
            }
        }
        return count;
    }
    public static void create_array() {
        seq[0]=0;
        for(int i=1;i<seq.length;i++) {
            int indx=-1;
            for(int j=i-2;j>=0;j--) {
                if(seq[i-1]==seq[j]) {
                    indx=j;
                    break;
                }
            }
            if(indx==-1) {
                seq[i]=0;
            }
            else {
                seq[i]=i-indx-1;
            }
        }
    }
}
