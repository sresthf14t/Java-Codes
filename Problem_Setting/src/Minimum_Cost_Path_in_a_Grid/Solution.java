/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minimum_Cost_Path_in_a_Grid;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Solution {
    
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
    
    //Sloution Starts here
    
    static int primes[];
    public static void main(String args[]) throws IOException{
        Scan input=new Scan();
        sieve(500);
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        int cost_array[]=new int[100001];   //To store cost of every integer in the constraint range
        for(int i=1;i<cost_array.length;i++) {
            cost_array[i]=get_cost(i);
        }
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.scanInt();
                }
            }
            int cost[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    cost[i][j]=cost_array[arr[i][j]];
                }
            }
            ans.append(min_path_sum(cost, n, m)+"\n");
        }
        System.out.println(ans);
    }
    public static int get_cost(int val) {
        int lim=(int)(Math.sqrt(val))+1;
        int cnt=0;
        for(int i=0;i<primes.length && primes[i]<=lim;i++) {
            while(val%primes[i]==0) {
                val/=primes[i];
                cnt++;
            }
        }
        if(val!=1) {
            cnt++;
        }
        return cnt;
    }
    
    //false for prime number and true for composite number
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        primes=new int[95];
        int indx=0;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2;i*j<n;j++) {
                    sieve[i*j]=true;
                }
            }
        }
    }
    public static int min_path_sum(int arr[][],int n,int m) {
        int path_sum[][]=new int[n][m];
        path_sum[0][0]=arr[0][0];
        for(int i=1;i<n;i++) {
            path_sum[i][0]=path_sum[i-1][0]+arr[i][0];
        }
        
        for(int i=1;i<m;i++) {
            path_sum[0][i]=path_sum[0][i-1]+arr[0][i];
        }
        
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                path_sum[i][j]=arr[i][j]+Math.min(path_sum[i-1][j],path_sum[i][j-1]);
            }
        }
        return path_sum[n-1][m-1];
    }
}
