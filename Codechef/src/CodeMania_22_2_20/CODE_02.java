/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeMania_22_2_20;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CODE_02 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String arr[]=new String[6];
        for(int i=0;i<6;i++) {
            arr[i]=input.next();
        }
//        System.out.println();
        for(int i=5;i>=0;i--) {
            for(int j=2;j>=0;j--) {
                if(j==2 && arr[i].charAt(j)=='0') {
                    continue;
                }
                System.out.print(arr[i].charAt(j));
            }
            System.out.println();
        }
    }
}
