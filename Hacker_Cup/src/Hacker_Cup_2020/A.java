/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hacker_Cup_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A {
    static boolean visit[];
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\Srest\\Desktop\\Hacker Cup\\Input\\travel_restrictions_input.txt"));
        StringBuilder out=new StringBuilder("");
        int test=input.nextInt();
        for(int T=1;T<=test;T++) {
            out.append("Case #"+T+": \n");
            int len=input.nextInt();
            adj_lst=new ArrayList[len];
            for(int i=0;i<len;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            String incomming=input.next();
            String outgoinng=input.next();
            for(int i=0;i<len;i++) {
                if(i!=0 && outgoinng.charAt(i)=='Y' && incomming.charAt(i-1)=='Y') {
                    adj_lst[i].add(i-1);
                }
                if(i!=len-1 && outgoinng.charAt(i)=='Y' && incomming.charAt(i+1)=='Y') {
                    adj_lst[i].add(i+1);
                }
            }
            for(int i=0;i<len;i++) {
                visit=new boolean[len];
                solve(i);
                for(int j=0;j<len;j++) {
                    if(visit[j]) {
                        out.append("Y");
                    }
                    else {
                        out.append("N");
                    }
                }
                out.append("\n");
            }
        }
        Write_to_File(out);
    }
    public static void solve(int indx) {
        if(!visit[indx]) {
            visit[indx]=true;
        }
        for(int i=0;i<adj_lst[indx].size();i++) {
            if(visit[adj_lst[indx].get(i)]) {
                continue;
            }
            if(!visit[adj_lst[indx].get(i)]) {
                solve(adj_lst[indx].get(i));
            }
        }
    }
    
    public static void Write_to_File(StringBuilder ans) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\Srest\\Desktop\\Hacker Cup\\Output\\A_final_out.txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        outputstream.println(ans);
        outputstream.close();
    }
}
