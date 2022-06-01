/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_649_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=1;i<=n;i++) {
            arrli.add(i);
        }
        int min=Integer.MAX_VALUE;
        ArrayList<Integer> tmp=new ArrayList<>();
        for(int i=2;i<=n;i++) {
            System.out.println("? "+1+" "+i);
            System.out.flush();
            int in=input.nextInt();
            if(in==-1) {
                System.exit(0);
            }
            if(in>min) {
                continue;
            }
            if(in==min) {
                tmp.add(i);
            }
            else {
                tmp=new ArrayList<>();
                tmp.add(i);
            }
        }
        int indx=-1;
        for(int i=0;i<tmp.size();i++) {
            arrli.remove(tmp.get(i));
            indx=tmp.get(i);
        }
        int zero=-1;
        min=Integer.MAX_VALUE;
        indx=1;
        while(arrli.size()!=1) {
            while(arrli.contains(indx)) {
                indx++;
            }
            min=Integer.MAX_VALUE;
            tmp=new ArrayList<>();
            for(int i=0;i<arrli.size();i++) {
                System.out.println("? "+indx+" "+arrli.get(i));
                System.out.flush();
                int in=input.nextInt();
                if(in==-1) {
                    System.exit(0);
                }
                if(in>min) {
                    continue;
                }
                if(in==min) {
                    tmp.add(i);
                }
                else {
                    tmp=new ArrayList<>();
                    tmp.add(i);
                }
            }
            for(int i=0;i<arrli.size();i++) {
                if(!tmp.contains(arrli.get(i))) {
                    arrli.remove(i);
                    i--;
                }
            }
            indx++;
        }
         zero=arrli.get(0);
        int arr[]=new int[n];
        for(int i=1;i<=n;i++) {
            if(i==zero) {
                continue;
            }
            System.out.println("? "+zero+" "+i);
            System.out.flush();
            arr[i-1]=input.nextInt();
            if(arr[i-1]==-1) {
                System.exit(0);
            }
        }
        StringBuilder ans=new StringBuilder("! ");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        System.out.println(ans);
        System.out.flush();
    }
}
