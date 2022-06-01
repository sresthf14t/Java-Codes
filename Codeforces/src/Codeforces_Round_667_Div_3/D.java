/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_667_Div_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
    static int arr[];
    static long pow[],dp[][][];
    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        pow=new long[19];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*10;
        }
        for(int t=0;t<test;t++) {
            long n=input.nextLong();
            int sum=input.nextInt();
            arr=new int[(""+n).length()];
            long tmp=n;
            for(int i=arr.length-1;i>=0;i--) {
                arr[i]=(int)(tmp%10);
                tmp/=10;
            }
            int total=0;
            for(int i=0;i<arr.length;i++) {
                total+=arr[i];
            }
            if(total<=sum) {
                ans.append(0+"\n");
                continue;
            }
//            dp=new long[arr.length][2][sum+1];
//            for(int i=0;i<dp.length;i++) {
//                for(int j=0;j<dp[0].length;j++) {
//                    for(int k=0;k<dp[0][0].length;k++) {
//                        dp[i][j][k]=-1;
//                    }
//                }
//            }
            long fin=solve1(n,sum);
            if(fin==Long.MAX_VALUE) {
                fin=pow[arr.length]-n;
            }
            ans.append(fin+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int indx,boolean can_down,int rem) {
        if(rem<0) {
            return Long.MAX_VALUE/2;
        }
        if(indx==arr.length) {
            return 0;
        }
        if(dp[indx][can_down?1:0][rem]!=-1) {
            return dp[indx][can_down?1:0][rem];
        }
        long ans=Long.MAX_VALUE;
        if(!can_down) {
            ans=Math.min(ans,solve(indx+1,false,rem-arr[indx]));
            for(int i=arr[indx]+1;i<10;i++) {
                ans=Math.min(ans,(pow[arr.length-indx-1]*(i-arr[indx]))+solve(indx+1,true,rem-i));
            }
        }
        else {
            for(int i=0;i<10;i++) {
                ans=Math.min(ans,(pow[arr.length-indx-1]*(i-arr[indx]))+solve(indx+1,true,rem-i));
            }
        }
        dp[indx][can_down?1:0][rem]=ans;
        return ans;
    }
    public static long solve1(long n,int sum) {
        long ans=Long.MAX_VALUE;
        int tmp_sum=0;
        for(int i=0;i<arr.length;i++) {
            tmp_sum+=arr[i];
            if(arr[i]==9) {
                continue;
            }
            if(tmp_sum+1>sum) {
                continue;
            }
            long tmp=pow[arr.length-i-1];
            for(int j=i+1;j<arr.length;j++) {
                tmp-=(pow[arr.length-j-1]*arr[j]);
            }
            ans=Math.min(ans,tmp);
        }
        return ans;
    }
}
