/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.util.Scanner;
public class Bundling {
    
    static class TrieNode {
        TrieNode children[];
        int EndOfWord;
        TrieNode() {
            children=new TrieNode[26];
            EndOfWord=0;
            for(int i=0;i<26;i++) {
                children[i]=null;
            }
        }
    }
    public static void insert(TrieNode root, String str) {
        TrieNode tmp=root;
        for(int i=0;i<str.length();i++) {
            int indx=str.charAt(i)-97;
            if(tmp.children[indx]==null) {
                tmp.children[indx]=new TrieNode();
            }
            tmp=tmp.children[indx];
            if(i==str.length()-1) {
                tmp.EndOfWord++;
            }
        }
    }
    public static boolean search(TrieNode root,String str) {
        TrieNode tmp=root;
        for(int i=0;i<str.length();i++) {
            int indx=str.charAt(i)-97;
            if(tmp.children[indx]==null) {
                return false;
            }
            tmp=tmp.children[indx];
        }
        if(tmp.EndOfWord==0) {
            return false;
        }
        return true;
    }
    //Only logically deleting
    public static void delete(TrieNode root,String str) {
        TrieNode tmp=root;
        for(int i=0;i<str.length();i++) {
            int indx=str.charAt(i)-97;
            if(tmp.children[indx]==null) {
                tmp.children[indx]=new TrieNode();
            }
            tmp=tmp.children[indx];
            if(i==str.length()-1) {
                tmp.EndOfWord--;
            }
        }
    }
    static long ans;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            ans=0;
            int n=input.nextInt();
            int k=input.nextInt();
            String str[]=new String[n];
            TrieNode root=new TrieNode();
            for(int i=0;i<n;i++) {
                str[i]=input.next();
                str[i]=str[i].toLowerCase();
                insert(root,str[i]);
            }
            count(root,k,0);
            System.out.println("Case #"+t+": "+ans);
        }
    }
    public static int count(TrieNode root,int k,int dep) {
        int cnt=0;
        cnt+=root.EndOfWord;
        for(int i=0;i<26;i++) {
            if(root.children[i]!=null) {
                cnt+=count(root.children[i],k,dep+1);
            }
        }
        ans+=(dep*(cnt/k));
        return cnt%k;
    }
}
