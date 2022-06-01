/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_110_Rated_for_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
    
    static StringBuilder str;
    static int arr[],par[];
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int k=input.scanInt();
        str=new StringBuilder(input.scanString());
        arr=new int[str.length()];
        adj_lst=new ArrayList[str.length()];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        
        int strt=0,diff=(int)Math.pow(2, k),cnt=0;
        for(int i=0;i<str.length()-1;i+=2) {
            if(i==strt) {
                diff/=2;
                strt=i+diff;
                cnt=0;
            }
            
            
            adj_lst[i].add(strt+cnt);
            adj_lst[strt+cnt].add(i);
            
            adj_lst[i+1].add(strt+cnt);
            adj_lst[strt+cnt].add(i+1);
            
            
            cnt++;
        }
        
        par=new int[str.length()];
        
        DFS(str.length()-1,-1);
        
//        for(int i=0;i<str.length();i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        
        int q=input.scanInt();
        
        char prev='.';
        
        for(int qq=0;qq<q;qq++) {
            
            
            
            int root=input.scanInt()-1;
            prev=str.charAt(root);
            str.setCharAt(root, input.scanString().charAt(0));
            int tr=root;
            
            if(adj_lst[root].size()==1) {
                if(str.charAt(root)=='0' || str.charAt(root)=='1') {
                    arr[root]=1;
                }
                else {
                    arr[root]=2;
                }
                root=par[root];
            }
            
            while(root!=-1) {
                
//                System.out.println(root);
                
                int val[]=new int[2];
                int indx[]=new int[2];
                cnt=0;
                for(int i=0;i<adj_lst[root].size();i++) {
                    if(adj_lst[root].get(i)==par[root]) {
                        continue;
                    }
                    
//                    System.out.println(root+" "+adj_lst[root].get(i)+" "+par[root]);
                    indx[cnt]=adj_lst[root].get(i);
                    val[cnt]=arr[adj_lst[root].get(i)];
                    cnt++;
                }
                
                int ret=0;
        
                if(str.charAt(root)=='0') {
                    if(indx[0]<indx[1]) {
                        ret=val[0];
                    }
                    else {
                        ret=val[1];
                    }
                }
                else if(str.charAt(root)=='1'){
                    if(indx[0]>indx[1]) {
                        ret=val[0];
                    }
                    else {
                        ret=val[1];
                    }
                }
                else {
                    ret=val[0]+val[1];
                }
                

                arr[root]=ret;
                
                root=par[root];
            }
//            System.out.println(qq);
            
            if(str.length()==1) {
                if(str.charAt(0)=='0' || str.charAt(0)=='1') {
                    arr[0]=1;
                }
                else {
                    arr[0]=2;
                }
            }
            
            ans.append(arr[str.length()-1]+"\n");
            
//            str.setCharAt(tr, prev);
            
        }
        
        System.out.println(ans);
    }
    
    public static int DFS(int root,int parent) {
        par[root]=parent;
        if(adj_lst[root].size()==1) {
            if(str.charAt(root)=='0' || str.charAt(root)=='1') {
                arr[root]=1;
                return 1;
            }
            arr[root]=2;
            return 2;
        }
        
        int val[]=new int[2];
        int indx[]=new int[2];
        for(int i=0,cnt=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==parent) {
                continue;
            }
            
            indx[cnt]=adj_lst[root].get(i);
            val[cnt]=DFS(adj_lst[root].get(i),root);
            cnt++;
        }
        
        int ret=0;
        
        if(str.charAt(root)=='0') {
            if(indx[0]<indx[1]) {
                ret=val[0];
            }
            else {
                ret=val[1];
            }
        }
        else if(str.charAt(root)=='1'){
            if(indx[0]>indx[1]) {
                ret=val[0];
            }
            else {
                ret=val[1];
            }
        }
        else {
            ret=val[0]+val[1];
        }
        
        
        
        arr[root]=ret;
        return ret;
        
    }
}
