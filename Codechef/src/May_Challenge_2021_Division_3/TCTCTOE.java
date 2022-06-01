/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package May_Challenge_2021_Division_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class TCTCTOE {
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
    
    static HashMap<Integer,Integer> map;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        map=new HashMap<>();
        per(new int[3][3],true);
        for(int tt=1;tt<=test;tt++) {
            int hash=0,mul=1;
            for(int i=0;i<3;i++) {
                String str=input.scanString();
                for(int j=0;j<str.length();j++) {
                    if(str.charAt(j)=='_') {
                        hash+=(0*mul);
                    }
                    else if(str.charAt(j)=='X') {
                        hash+=(1*mul);
                    }
                    else {
                        hash+=(2*mul);
                    }
                    mul*=3;
                }
            }
            if(map.containsKey(hash)) {
                ans.append(map.get(hash)+"\n");
            }
            else {
                ans.append(3+"\n");
            }
        }
        System.out.println(ans);
    }
    public static void per(int arr[][],boolean cc) {   //0->blank  1->X  2->O
        int hash=hash(arr);
        if(won(arr)) {
            map.put(hash, 1);
            return;
        }
        else {
            if(finish(arr)) {
                map.put(hash, 1);
                return;
            }
            map.put(hash, 2);
        }
        
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                if(arr[i][j]!=0) {
                    continue;
                }
                if(cc) {
                    arr[i][j]=1;
                }
                else {
                    arr[i][j]=2;
                }
                per(arr,!cc);
                arr[i][j]=0;
            }
        }
        
    }
    
    public static int hash(int arr[][]) {
        int hash=0,mul=1;
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                hash+=(mul*arr[i][j]);
                mul*=3;
            }   
        }
        return hash;
    }
    
    public static boolean finish(int arr[][]) {
        int z=0;
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                if(arr[i][j]==0) {
                    z++;
                }
            }
        }
        if(z==0) {
            return true;
        }
        return false;
    }
    
    public static boolean won(int arr[][]) {
        
        for(int i=0;i<arr.length;i++) {
            int x=0,o=0;
            for(int j=0;j<arr[0].length;j++) {
                if(arr[i][j]==1) {
                    x++;
                }
                if(arr[i][j]==2) {
                    o++;
                }
            }
            if(x==arr[0].length || o==arr[0].length) {
                return true;
            }
        }
        
        for(int i=0;i<arr.length;i++) {
            int x=0,o=0;
            for(int j=0;j<arr[0].length;j++) {
                if(arr[j][i]==1) {
                    x++;
                }
                if(arr[j][i]==2) {
                    o++;
                }
            }
            if(x==arr.length || o==arr.length) {
                return true;
            }
        }
        
        if(arr[0][0]==1 && arr[1][1]==1 && arr[2][2]==1) {
            return true;
        }
        if(arr[0][0]==2 && arr[1][1]==2 && arr[2][2]==2) {
            return true;
        }
        
        if(arr[0][2]==1 && arr[1][1]==1 && arr[2][0]==1) {
            return true;
        }
        if(arr[0][2]==2 && arr[1][1]==2 && arr[2][0]==2) {
            return true;
        }
        
        return false;
    }
}
