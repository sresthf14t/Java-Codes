/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICPC_Challenge_2020_Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A1 {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\sort\\sort.in"));
        int n=input.nextInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        Arrays.sort(arr);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        write(ans);
    }
    public static void write(StringBuilder ans) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\sort\\sort_ans.txt",false));
        }
        catch(FileNotFoundException e) {
            
        }
        outputstream.println(ans);
        outputstream.close();
    }
}
