/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTest;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class TEST123 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String filename="C:\\Users\\User\\Desktop\\Stress_Test.txt";
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream(filename,false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        int len=input.nextInt();
        StringBuilder ans=new StringBuilder("c");
        for(int i=0;i<len;i++) {
            ans.append("a");
        }
        outputstream.println(1+"\n"+ans);
        outputstream.close();
        System.out.println("--------DONE------");
    }
}
