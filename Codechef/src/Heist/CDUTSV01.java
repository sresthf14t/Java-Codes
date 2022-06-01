/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heist;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CDUTSV01 {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int chori_amt[]=new int[8];
        chori_amt=in_arr(chori_amt);
        int n=input.nextInt();
        long count=0L;
        for(int i=1;i<=n;i++) {
            int a=input.nextInt();
            int b=input.nextInt();
            
        }
    }
    public static int[] in_arr(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextInt();
        }
        return arr;
    }
}
