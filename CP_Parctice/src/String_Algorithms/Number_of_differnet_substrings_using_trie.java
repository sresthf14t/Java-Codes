/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String_Algorithms;

/**
 *
 * @author User
 */

//Time Complexity:O(n^2)

import java.util.*;
import java.io.*;
public class Number_of_differnet_substrings_using_trie {
    
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
    public static void insert(TrieNode root, StringBuilder str) {
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
    
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        TrieNode root=new TrieNode();
        for(int i=0;i<str.length();i++) {
            StringBuilder tmp=new StringBuilder("");
            for(int j=i;j<str.length();j++) {
                tmp.append(str.charAt(j));
                insert(root,tmp);
            }
        }
        System.out.println(count(root)+1);  //+1 if you want to count empty Strign "" also;
    }
    public static int count(TrieNode root) {
        int cnt=0;
        if(root.EndOfWord>0) {
            cnt++;
        }
        for(int i=0;i<26;i++) {
            if(root.children[i]!=null) {
                cnt+=count(root.children[i]);
            }
        }
        return cnt;
    }
}
