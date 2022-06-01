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
public class MDSWIN2_1 {
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

    static long[] fact_arr=new long[100001];
    static HashMap<Long,Long> hash_map_inv=new HashMap<>();
    public static void main(String args[]) throws IOException {
        fact(100000);
        Reader input=new Reader();
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            int q=input.nextInt();
            StringBuilder ans=new StringBuilder("");
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
                int xors=0;
                for(int j=0;j<dist_count.length;j++) {
                    dist_count[j]=lst_count.get(j);
                    xors^=dist_count[j];
                }
                ans.append(calc(dist_count,xors)+"\n");
            }
            System.out.println(ans);
        }
    }
    public static void fact(int n) {
        long fact=1;
        fact_arr[0]=fact_arr[1]=fact;
        for(int i=2;i<=n;i++) {
            fact*=i;
            fact%=998244353L;
            fact_arr[i]=fact;
        }
    }
    public static int calc(int arr[],int arr_xor) {
        int tot_count=0;
//        int arr_xor=xoring(arr);
        if(arr_xor==0) {
            return 0;
        }
        for(int i=0;i<arr.length;i++) {
            int tmp=arr_xor^arr[i];
            int count=0;
            if(tmp<arr[i]) {
                count=ncr(arr[i],arr[i]-tmp);
            }
            tot_count+=count;
            tot_count%=998244353;
        }
        return tot_count;
    }
    
    public static int xoring(int arr[]) {
        int ans=0;
        for(int i=0;i<arr.length;i++) {
            ans=ans^arr[i];
        }
        return ans;
    }
    
    public static int ncr(int n,int r) {
        long num=fact_arr[n];
        if(!hash_map_inv.containsKey(fact_arr[n-r])) {
            hash_map_inv.put(fact_arr[n-r], mod_inv(fact_arr[n-r],998244353L));
        }
        if(!hash_map_inv.containsKey(fact_arr[r])) {
            hash_map_inv.put(fact_arr[r], mod_inv(fact_arr[r],998244353L));
        }
        long den=hash_map_inv.get(fact_arr[n-r])*hash_map_inv.get(fact_arr[r]);
        den%=998244353L;
        return (int)((num*den)%998244353L);
    }
    public static long mod_inv(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
}
