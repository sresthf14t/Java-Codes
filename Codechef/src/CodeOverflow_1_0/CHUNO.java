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
import java.util.Arrays; 
public class CHUNO {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextInt();
        }
        Arrays.sort(arr);
        int count=0;
        for(int i=arr.length-1;i>=0;i--) {
            count++;
            if(count>=k) {
                if(i>0 && arr[i]!=arr[i-1]) {
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
