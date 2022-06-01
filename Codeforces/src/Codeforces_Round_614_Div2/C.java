/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_614_Div2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class C {
    static Scanner input=new Scanner(System.in);
    static int blockade[][];
    static int ptr=0;
    public static void main(String args[]) {
        int n=input.nextInt();
        boolean arr[][]=new boolean[2][n];
        blockade=new int[2][n];
        int q=input.nextInt();
        for(int i=0;i<q;i++) {
            int r=input.nextInt();
            int c=input.nextInt();
            r--;
            c--;
            if(arr[r][c]) {
                arr[r][c]=false;
                check_blockade(c);
            }
            else {
                arr[r][c]=true;
            }
            if(ptr!=0) {
                System.out.println("No");
            }
            else {
                if(check_arr(arr)) {
                    System.out.println("Yes");
                }
                else {
                    System.out.println("No");
                }
            }
            //print(arr);
        }
        
    }
    public static boolean check_arr(boolean arr[][]) {
        boolean cond1=true,cond2=true,cond3=true;
        for(int i=0;i<arr[0].length;i++) {
            if(arr[0][i] && arr[1][i]) {
                blockade[0][ptr]=i;
                blockade[1][ptr]=i;
                ptr++;
                return false;
            }
        }
        for(int i=0;i<arr[0].length-1;i++) {
            if(arr[0][i] && arr[1][i+1]) {
                blockade[0][ptr]=i;
                blockade[1][ptr]=i+1;
                ptr++;
                return false;
            }
        }
        for(int i=1;i<arr[0].length;i++) {
            if(arr[0][i] && arr[1][i-1]) {
                blockade[0][ptr]=i;
                blockade[1][ptr]=i-1;
                ptr++;
                return false;
            }
        }
        return true;
    }
    public static void print(boolean arr[][]) {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void check_blockade(int c) {
        for(int i=0;i<blockade.length;i++) {
            if(blockade[0][i]==c || blockade[1][i]==c) {
                blockade[0][i]=blockade[0][ptr];
                blockade[1][i]=blockade[1][ptr];
                ptr--;
            }
        }
    }
}
