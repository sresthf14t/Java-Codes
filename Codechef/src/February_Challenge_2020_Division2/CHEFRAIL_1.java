/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Challenge_2020_Division2;

/**
 *
 * @author User
 */
import java.util.*; 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class CHEFRAIL_1 {
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
        boolean sieve[]=sieve(100001);
        int primes[]=new int[9592];
        for(int i=2,j=0;i<sieve.length;i++) {
            if(!sieve[i]) {
                primes[j]=i;
                j++;
            }
        }
        int mul_for_sq[]=new int[100001];
        for(int i=2;i<=100000;i++) {
            int mul=1,tmp=i,count=0;
            for(int j=0;j<9592 && primes[j]<=tmp;j++) {
                count=0;
                while(tmp%primes[j]==0) {
                    count++;
                    tmp/=primes[j];
                }
                if(count%2!=0) {
                    mul*=primes[j];
                }
            }
            mul_for_sq[i]=mul;
        }
        long mul[][]=new long[1000][100001];
        mul[0][1]++;
        mul[1][1]=1;
        for(int i=0;i<100001;i++) {
            int tmp=mul_for_sq[i];
            mul[0][tmp]++;
            mul[(int)mul[0][tmp]][tmp]=i;
        }
//        for(int i=0;i<100;i++) {
//            for(int j=0;j<100;j++) {
//                System.out.print(mul[i][j]+"\t");
//            }
//            System.out.println();
//        }
//        long tot=0;
//        for(int i=0;i<100001;i++) {
//            tot+=mul[0][i]*mul[0][i];
//        }
//        System.out.println(tot);
        
        
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            boolean zero_0_x=false,zero_0_y=false;
            int n=input.nextInt();
            int m=input.nextInt();
            Set<Long> hash_set_x_pos = new HashSet<Long>();
            Set<Long> hash_set_x_neg = new HashSet<Long>();
            Set<Long> hash_set_y_pos = new HashSet<Long>();
            Set<Long> hash_set_y_neg = new HashSet<Long>();
            for(int i=0;i<n;i++) {
                long tmp=input.nextLong();
                if(tmp>0) {
                    hash_set_x_pos.add(tmp);
                }
                else if(tmp<0) {
                    hash_set_x_neg.add(-1*tmp);
                }
                if(tmp==0) {
                    zero_0_x=true;
                }
            }
            
            for(int i=0;i<m;i++) {
                long tmp=input.nextLong();
                if(tmp>0) {
                    hash_set_y_pos.add(tmp);
                }
                else if(tmp<0) {
                    hash_set_y_neg.add(-1*tmp);
                }
                if(tmp==0) {
                    zero_0_y=true;
                }
            }
             
            long count=0L;
            
            //X AXIS AS BASE
            
            
            for(int i=1;i<100001;i++) {
                int len=(int)mul[0][i];
                for(int j=1;j<=len;j++) {
                    for(int k=j;k<=len;k++) {
                        if(hash_set_x_pos.contains((long)mul[j][i]) && hash_set_x_neg.contains((long)mul[k][i])) {
                            long tmp=mul[j][i]*mul[k][i];
                            tmp=(long)Math.sqrt(tmp);
                            if(hash_set_y_pos.contains(tmp)) {
                                count=count+1L;
                            }
                            if(hash_set_y_neg.contains(tmp)) {
                                count=count+1L;
                            }
                        }
                        if(j==k) {
                            continue;
                        }
                        if(hash_set_x_neg.contains((long)mul[j][i]) && hash_set_x_pos.contains((long)mul[k][i])) {
                            long tmp=mul[j][i]*mul[k][i];
                            tmp=(long)Math.sqrt(tmp);
                            if(hash_set_y_pos.contains(tmp)) {
                                count=count+1L;
                            }
                            if(hash_set_y_neg.contains(tmp)) {
                                count=count+1L;
                            }
                        }
                    }
                }
            }
            
            
            //Y AXIS AS BASE
            
            
            for(int i=1;i<100001;i++) {
                int len=(int)mul[0][i];
                for(int j=1;j<=len;j++) {
                    for(int k=j;k<=len;k++) {
                        if(hash_set_y_pos.contains((long)mul[j][i]) && hash_set_y_neg.contains((long)mul[k][i])) {
                            long tmp=mul[j][i]*mul[k][i];
                            tmp=(long)Math.sqrt(tmp);
                            if(hash_set_x_pos.contains(tmp)) {
                                count++;
                            }
                            if(hash_set_x_neg.contains(tmp)) {
                                count++;
                            }
                        }
                        if(j==k) {
                            continue;
                        }
                        if(hash_set_y_neg.contains((long)mul[j][i]) && hash_set_y_pos.contains((long)mul[k][i])) {
                            long tmp=mul[j][i]*mul[k][i];
                            tmp=(long)Math.sqrt(tmp);
                            if(hash_set_x_pos.contains(tmp)) {
                                count++;
                            }
                            if(hash_set_x_neg.contains(tmp)) {
                                count++;
                            }
                        }
                    }
                }
            }
            long N=n,M=m;
            if(zero_0_x) {
                count+=(N-1L)*M;
            }
            else if(zero_0_y) {
                count+=(N)*(M-1L);
            }
            else if(zero_0_x && zero_0_y) {
                count+=(N-1L)*(M-1L);
            }
            System.out.println(count);
        }
        
    }
    
    //false for prime number and true for composite number
    public static boolean[] sieve(int n) {
        boolean a[]=new boolean[n];
        for(int i=2;i<n;i++) {
            for(int j=2*i;j<n;j=j+i) {
                a[j]=true;
            }
        }
        return a;
    }
}
