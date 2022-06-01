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

//Lower case English alphabets

import java.util.*;
import java.io.*;
public class Trie {
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
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //Master root node
        TrieNode root=new TrieNode();
        insert(root,"pqrdt");
        insert(root,"pqsdt");
        insert(root,"pqrdt");
        insert(root,"abcde");
        insert(root,"abcde");
        System.out.println(search(root,"abcde"));
        delete(root,"abcde");
        System.out.println(search(root,"abcde"));
        delete(root,"abcde");
        System.out.println(search(root,"abcde"));
    }
}
