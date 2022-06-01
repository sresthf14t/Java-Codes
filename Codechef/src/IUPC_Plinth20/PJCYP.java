/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IUPC_Plinth20;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class PJCYP {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        String str=input.nextLine();
        arr=str_to_arr(str,n);
        int count=1;
        for(int i=arr.length-2;i>=0;i--) {
            if(arr[i]==arr[arr.length-1]) {
                count++;
            }
        }
        if(count%2==1) {
            System.out.println("Customer-A");
        }
        else {
            System.out.println("Customer-B");
        }
    }
    
    public static int[] in_arr(int arr[]) {
        Scanner input=new Scanner(System.in);
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextInt();
        }
        return arr;
    }
    
    public static int[] str_to_arr(String str,int n) {
        int arr[]=new int[n];
        int point=0;
        for(int i=0;i<str.length();i++) {
            String str_tmp="";
            while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9') {
                str_tmp+=str.charAt(i);
                i++;
            }
            arr[point]=str_to_int(str_tmp);
            point++;
        }
        return arr;
    }
    
     public static int str_to_int(String str) {
         int num=0;
         for(int i=0;i<str.length();i++) {
             num+=(num*10)+(str.charAt(i)-48);
         }
         return num;
     }
}
