/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class B_A_Lot_of_Games {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    
    
    
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
    public static TrieNode init(int n,String str[]) {
        Scanner input=new Scanner(System.in);
        //Master root node
        TrieNode root=new TrieNode();
        for(int i=0;i<n;i++) {
            insert(root,str[i]);
        }
        return root;
    }

    
    
    
    
    static int n,k;
    static String str[];
    static HashMap<TrieNode,Integer> dp_win[],dp_loose[];
    static boolean first_win,first_loose;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        k=input.scanInt();
        str=new String[n];
        for(int i=0;i<n;i++) {
            str[i]=input.scanString();
        }
        TrieNode root=init(n,str);
        dp_win=new HashMap[5];
        dp_loose=new HashMap[5];
        for(int i=0;i<5;i++) {
            dp_win[i]=new HashMap<>();
            dp_loose[i]=new HashMap<>();
        }
        
        first_win=solve_win(root,true)==1;
        first_loose=solve_loose(root,true)==2;
        
//        System.out.println(first_win+" "+first_loose);

//        boolean first=solve(k,true)==1;
//        
//        if(first) {
//            ans.append("First\n");
//        }
//        else {
//            ans.append("Second\n");
//        }
        
        if(first_win && first_loose) {
            ans.append("First\n");
        }
        else if(first_win && !first_loose) {
            if(k%2==0) {
                ans.append("Second\n");
            }
            else {
                ans.append("First\n");
            }
        }
        else if(!first_win && first_loose) {
            ans.append("Second\n");
        }
        else {
            ans.append("Second\n");
        }
        
        System.out.print(ans);
    }
    
    public static int solve(int game,boolean chance) { //true -> first
        int ans=chance?2:1;
        if(game==0) {
            return ans;
        }
        if(first_win) {
            if(chance) {
                ans=Math.min(ans, solve(game-1,!chance));
            }
            else {
                ans=Math.max(ans, solve(game-1,!chance));
            }
        }
        if(first_loose) {
            if(chance) {
                ans=Math.min(ans, solve(game-1,chance));
            }
            else {
                ans=Math.max(ans, solve(game-1,chance));
            }
        }
        return ans;
    }
    
    public static int solve_win(TrieNode node,boolean chance) { //true -> first
        
        if(dp_win[chance?1:2].containsKey(node)) {
            return dp_win[chance?1:2].get(node);
        }
        
        int ans=chance?2:1;
        for(int i=0;i<node.children.length;i++) {
            if(node.children[i]==null) {
                continue;
            }
            int tmp=solve_win(node.children[i],!chance);
            if(chance) {
                ans=Math.min(ans, tmp);
            }
            if(!chance) {
                ans=Math.max(ans,tmp);
            }
        }
        dp_win[chance?1:2].put(node, ans);
        return ans;
    }
    
    
    public static int solve_loose(TrieNode node,boolean chance) { //true -> first
        boolean has_nxt=false;
        for(int i=0;i<node.children.length;i++) {
            has_nxt|=(node.children[i]!=null);
        }
        if(!has_nxt) {
            return chance?2:1;
        }
        
        if(dp_loose[chance?1:2].containsKey(node)) {
            return dp_loose[chance?1:2].get(node);
        }
        
        int ans=chance?1:2;
        for(int i=0;i<node.children.length;i++) {
            if(node.children[i]==null) {
                continue;
            }
            int tmp=solve_loose(node.children[i],!chance);
            if(chance) {
                ans=Math.max(ans, tmp);
            }
            if(!chance) {
                ans=Math.min(ans, tmp);
            }
        }
        dp_loose[chance?1:2].put(node, ans);
        return ans;
    }
}
