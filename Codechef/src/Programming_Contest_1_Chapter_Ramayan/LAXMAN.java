/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programming_Contest_1_Chapter_Ramayan;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class LAXMAN {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            for(int i=1;i<=n;i++) {
                ans.append(i+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
