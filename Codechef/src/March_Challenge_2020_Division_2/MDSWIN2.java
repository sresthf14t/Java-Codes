/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Challenge_2020_Division_2;

/**
 *
 * @author User
 */
import java.math.BigInteger;
import java.util.*; 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class MDSWIN2 {
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

    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            int q=input.nextInt();
            for(int i=0;i<q;i++) {
                int l=input.nextInt();
                int r=input.nextInt();
                l--;
                r--;
                HashMap<Integer, Integer> map = new HashMap<>(); 
                ArrayList<Integer> lst_ele=new  ArrayList<>();
                ArrayList<Integer> lst_count=new  ArrayList<>();
                for(int j=l,indx=0;j<=r;j++) {
                    if(!map.containsKey(arr[j])) {
                        map.put(arr[j],indx);
                        lst_ele.add(arr[j]);
                        lst_count.add(1);
                        indx++;
                    }
                    else {
                        int tmp_indx=map.get(arr[j]);
                        lst_count.set(tmp_indx, lst_count.get(tmp_indx)+1);
                    }
                }
                int dist_count[]=new int[lst_count.size()];
                for(int j=0;j<dist_count.length;j++) {
                    dist_count[j]=lst_count.get(j);
                }
                
                calc(dist_count);
            }
        }
    }
    public static BigInteger fact(int n) {
        BigInteger fact=new BigInteger("1");
        for(int i=2;i<=n;i++) {
            fact=fact.multiply(new BigInteger(""+i));
        }
        return fact;
    }
    public static void calc(int arr[]) {
        int tot_count=0;
        for(int i=0;i<arr.length;i++) {
            int count=0;
            int tmp=arr[i];
            while(arr[i]!=-1) {
                arr[i]--;
                if(xoring(arr)==0) {
                    count+=ncr(tmp,tmp-arr[i]);
                }
            }
            arr[i]=tmp;
            tot_count+=count;
            tot_count%=998244353;
        }
        System.out.println(tot_count);
    }
    
    public static int xoring(int arr[]) {
        int ans=0;
        for(int i=0;i<arr.length;i++) {
            ans=ans^arr[i];
        }
        return ans;
    }
    public static int ncr(int n,int r) {
        BigInteger tmp=(fact(n).divide(fact(r).multiply(fact(n-r)))).mod(new BigInteger(""+998244353));
        return tmp.intValue();
    }
}
