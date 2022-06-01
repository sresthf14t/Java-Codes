/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shaastra_Programming_Contest_Open_Round;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.util.Arrays;
public class SOPC006 {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[][]=new int[2][n];
            arr=in_arr(arr);
            arr=sort(arr);
            find_pairs(arr);
        }
    }
    
    public static int[][] in_arr(int arr[][]) {
        for(int i=0;i<arr[0].length;i++) {
            arr[0][i]=input.nextInt();
            arr[1][i]=i+1;
        }
        return arr;
    }

    
    public static int[][] sort(int arr[][]) {
        for(int i=0;i<arr[0].length;i++) {
            for(int j=0;j<arr[0].length-1;j++) {
                if(arr[0][j]>arr[0][j+1]) {
                    int temp_0=arr[0][j];
                    int temp_1=arr[1][j];
                    arr[0][j]=arr[0][j+1];
                    arr[1][j]=arr[1][j+1];
                    arr[0][j+1]=temp_0;
                    arr[1][j+1]=temp_1;
                }
            }
        }
        return arr;
    }
    public static void find_pairs(int arr[][]) {
        int count=0;
        String str="";
        for(int i=0;i<arr[0].length-1;i++) {
            if(arr[0][i]*2>=arr[0][i+1]) {
                count+=2;
                str+="\n"+arr[1][i]+" "+arr[1][i+1];
                i++;
            }
        }
        System.out.println(count+str);
    }
}
