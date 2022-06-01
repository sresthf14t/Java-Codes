/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENIGMA_PLINTH_20_LNMIIT;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class BANSTR {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int p=input.nextInt();
            String str=input.next();
            char arr[]=new char[n];
            for(int i=0;i<arr.length;i++) {
                arr[i]=str.charAt(i);
            }
            for(int l=0,r=n-1;l<r && p>0;l++) {
                if(arr[l]=='b') {
                    while(l<r) {
                        if(arr[r]=='a') {
                            arr[l]='a';
                            arr[r]='b';
                            r--;
                            p--;
                            break;
                        }
                        r--;
                    }
                }
            }
            for(int i=0;i<n && p>1;i++) {
                if(arr[i]=='b') {
                    arr[i]='a';
                    p-=2;
                }
            }
            
            for(int i=0;i<n;i++) {
                System.out.print(arr[i]);
            }
        }
    }
}
