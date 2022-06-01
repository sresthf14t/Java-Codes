/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_794_Div_2;

/**
 *
 * @author SRESTH
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
    static HashMap<Integer,Integer> dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            
            int a=input.scanInt();
            int b=input.scanInt();
            int ab=input.scanInt();
            int ba=input.scanInt();
            str=new StringBuilder(input.scanString());
            
            if(ab>ba) {
                int tmp=ab;
                ab=ba;
                ba=tmp;
                
                tmp=a;
                a=b;
                a=tmp;
            }
            
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)=='A') {
                    str.setCharAt(i, 'B');
                }
                else {
                    str.setCharAt(i, 'A');
                }
            }
            
            int char_a=0,char_b=0;
            dp=new HashMap[str.length()];
            for(int i=0;i<str.length();i++) {
                dp[i]=new HashMap<>();
                if(str.charAt(i)=='A') {
                    char_a++;
                }
                else {
                    char_b++;
                }
            }
            if(char_a!=(a+ab+ba) || char_b!=(b+ab+ba)) {
                ans.append("NO\n");
                continue;
            }
            int fin=solve(0,ab);
            if(fin>=ba) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.print(ans);
    }
    
    public static int solve(int indx,int rem) {
        if(indx==str.length()) {
            if(rem==0) {
                return 0;
            }
            return Integer.MIN_VALUE/10;
        }
        if(indx==str.length()-1) {
            return solve(indx+1,rem);
        }
        if(2*(str.length()-1-indx+1)<rem) {
            return Integer.MIN_VALUE/10;
        }
        
        if(dp[indx].containsKey(rem)) {
            return dp[indx].get(rem);
        }
        
        int ans=Integer.MIN_VALUE/10;
        ans=Math.max(ans, solve(indx+1,rem));
        
        if(str.charAt(indx)=='A' && str.charAt(indx+1)=='B') {
            ans=Math.max(ans, solve(indx+2,rem-1));
        }
        if(str.charAt(indx)=='B' && str.charAt(indx+1)=='A') {
            ans=Math.max(ans, 1+solve(indx+2,rem));
        }
        dp[indx].put(rem, ans);
        return ans;
    }
}
