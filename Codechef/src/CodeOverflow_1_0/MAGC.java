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
public class MAGC {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int a=input.nextInt();
        int b=input.nextInt();
        boolean[] arr=new boolean[3];
        arr[a-1]=arr[b-1]=true;
        for(int i=0;i<arr.length;i++) {
            if(!arr[i]) {
                System.out.println(i+1);
            }
        }
    }
}
