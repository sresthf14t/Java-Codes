/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_Vasiliy_s_Multiset {
    static class Scan {
        private byte[] buf=new byte[1024];
        private int index;
        private InputStream in;
        private int total;
        public Scan()
        {
            in=System.in;
        }
        public int scan()throws IOException
        {
            if(total<0)
            throw new InputMismatchException();
            if(index>=total)
            {
                index=0;
                total=in.read(buf);
                if(total<=0)
                return -1;
            }
            return buf[index++];
        }
        public int scanInt()throws IOException
        {
            int integer=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    integer*=10;
                    integer+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            return neg*integer;
        }
        public double scanDouble()throws IOException
        {
            double doub=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n)&&n!='.')
            {
                if(n>='0'&&n<='9')
                {
                    doub*=10;
                    doub+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            if(n=='.')
            {
                n=scan();
                double temp=1;
                while(!isWhiteSpace(n))
                {
                    if(n>='0'&&n<='9')
                    {
                        temp/=10;
                        doub+=(n-'0')*temp;
                        n=scan();
                    }
                    else throw new InputMismatchException();
                }
            }
            return doub*neg;
        }
        public String scanString()throws IOException
        {
            StringBuilder sb=new StringBuilder();
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            while(!isWhiteSpace(n))
            {
                sb.append((char)n);
                n=scan();
            }
            return sb.toString();
        }
        private boolean isWhiteSpace(int n)
        {
            if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
            return true;
            return false;
        }
    }
    static int pow[];
    static class TrieNode {
        TrieNode children[];
        int EndOfWord;
        TrieNode() {
            children=new TrieNode[2];
            EndOfWord=0;
            for(int i=0;i<2;i++) {
                children[i]=null;
            }
        }
    }
    public static void insert(TrieNode root, StringBuilder str) {
        TrieNode tmp=root;
        for(int i=0;i<str.length();i++) {
            int indx=str.charAt(i)-48;
            if(tmp.children[indx]==null) {
                tmp.children[indx]=new TrieNode();
            }
            tmp=tmp.children[indx];
            if(i==str.length()-1) {
                tmp.EndOfWord++;
            }
        }
    }
    public static boolean search(TrieNode root,int indx,StringBuilder str,StringBuilder ans) {
        TrieNode tmp=root;
        if(root.EndOfWord>0) {
            return true;
        }
        if(indx==str.length()) {
            return false;
        }
        if(str.charAt(indx)=='0') {
            if(root.children[1]!=null) {
                ans.append(1);
                if(search(root.children[1],indx+1,str,ans)) {
                    return true;
                }
                ans.deleteCharAt(ans.length()-1);
            }
            if(root.children[0]!=null) {
                ans.append(0);
                if(search(root.children[0],indx+1,str,ans)) {
                    return true;
                }
                ans.deleteCharAt(ans.length()-1);
            }
        }
        if(str.charAt(indx)=='1') {
            if(root.children[0]!=null) {
                ans.append(1);
                if(search(root.children[0],indx+1,str,ans)) {
                    return true;
                }
                ans.deleteCharAt(ans.length()-1);
            }
            if(root.children[1]!=null) {
                ans.append(0);
                if(search(root.children[1],indx+1,str,ans)) {
                    return true;
                }
                ans.deleteCharAt(ans.length()-1);
            }
        }
        return false;
    }
    //Only logically deleting
    public static void delete(TrieNode root,StringBuilder str) {
        TrieNode tmp=root;
        for(int i=0;i<str.length();i++) {
            int indx=str.charAt(i)-48;
            if(tmp.children[indx]==null) {
                tmp.children[indx]=new TrieNode();
            }
            tmp=tmp.children[indx];
            if(i==str.length()-1) {
                tmp.EndOfWord--;
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        pow=new int[32];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        TrieNode root=new TrieNode();
        int query=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        insert(root,dec_to_bin(0));
        for(int q=1;q<=query;q++) {
            char type=input.scanString().charAt(0);
            int x=input.scanInt();
            if(type=='+') {
                insert(root,dec_to_bin(x));
            }
            else if(type=='-') {
                delete(root,dec_to_bin(x));
            }
            else {
                StringBuilder tmp=new StringBuilder("");
                search(root,0,dec_to_bin(x),tmp);
                ans.append(bin_to_dec(tmp)+"\n");
            }
        }
        System.out.println(ans);
    }
    public static StringBuilder dec_to_bin(int n) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        while(ans.length()!=31) {
            ans.append(0);
        }
        ans=ans.reverse();
        return ans;
    }
    public static int bin_to_dec(StringBuilder str) {
        int ans=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='1') {
                ans+=pow[str.length()-i-1];
            }
        }
        return ans;
    }
}
