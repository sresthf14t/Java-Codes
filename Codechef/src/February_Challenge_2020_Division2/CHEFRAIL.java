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
public class CHEFRAIL {
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
            int m=input.nextInt();
            boolean zero_0_x=false,zero_0_y=false;
            Set<Integer> hash_set_x = new HashSet<Integer>();
            Set<Integer> hash_set_y = new HashSet<Integer>();
            int arr_x[]=new int[n];
            int arr_y[]=new int[m];
            int max_x=-999999999,max_y=-999999999,min_x=999999999,min_y=999999999;
            for(int i=0;i<n;i++) {
                arr_x[i]=input.nextInt();
                hash_set_x.add(arr_x[i]);
                if(arr_x[i]==0) {
                    zero_0_x=true;
                }
                if(arr_x[i]>max_x) {
                    max_x=arr_x[i];
                }
                if(arr_x[i]>min_x) {
                    min_x=arr_x[i];
                }
            }
            for(int i=0;i<m;i++) {
                arr_y[i]=input.nextInt();
                hash_set_y.add(arr_y[i]);
                if(arr_y[i]==0) {
                    zero_0_y=true;
                }
                if(arr_y[i]>max_y) {
                    max_y=arr_y[i];
                }
                if(arr_y[i]>min_y) {
                    min_y=arr_y[i];
                }
            }
            Arrays.sort(arr_x);
            Arrays.sort(arr_y);
            
            int mid_x=-1,mid_y=-1;
            for(int i=0;i<n-1;i++) {
                if(arr_x[i]<0 && arr_x[i+1]>=0) {
                    mid_x=i+1;
                    break;
                }
            }
            for(int i=0;i<m-1;i++) {
                if(arr_y[i]<0 && arr_y[i+1]>=0) {
                    mid_y=i+1;
                    break;
                }
            }
            
            int count=0;
            for(int i=0;i<mid_x;i++) {
                boolean breaking=false;
                for(int j=mid_x;j<n;j++) {
                    double tmp=arr_x[i]*arr_x[j];
                    tmp=Math.abs(tmp);
                    tmp=Math.sqrt(tmp);
                    int tmp1=(int)tmp;
                    if( (Math.floor(tmp)==Math.ceil(tmp)) && hash_set_y.contains(tmp1)  && arr_x[i]!=0 && arr_x[j]!=0) {
                        count++;
                    }
                    if( (Math.floor(tmp)==Math.ceil(tmp)) && hash_set_y.contains(-1*tmp1) && arr_x[i]!=0 && arr_x[j]!=0) {
                        count++;
                    }
                    if(tmp>(double)max_y && tmp>Math.abs((double)min_y)) {
                        if(j==mid_x) {
                            breaking=true;
                        }
                        break;
                    }
                }
                if(breaking) {
                    break;
                }
            }
            for(int i=0;i<mid_y;i++) {
                boolean breaking=false;
                for(int j=mid_y;j<m;j++) {
                    double tmp=arr_y[i]*arr_y[j];
                    tmp=Math.abs(tmp);
                    tmp=Math.sqrt(tmp);
                    int tmp1=(int)tmp;
                    if( (Math.floor(tmp)==Math.ceil(tmp)) && hash_set_x.contains(tmp1) && arr_y[i]!=0 && arr_y[j]!=0) {
                        count++;
                    }
                    if( (Math.floor(tmp)==Math.ceil(tmp)) && hash_set_x.contains(-1*tmp1) && arr_y[i]!=0 && arr_y[j]!=0) {
                        count++;
                    }
                    if(tmp>(double)max_x && tmp>Math.abs((double)min_x)) {
                        if(j==mid_y) {
                            breaking=true;
                        }
                        break;
                    }
                }
                if(breaking) {
                    break;
                }
            }
            if(zero_0_x) {
                count+=(n-1)*m;
            }
            else if(zero_0_y) {
                count+=(n)*(m-1);
            }
            else if(zero_0_x && zero_0_y) {
                count+=(n-1)*(m-1);
            }
            System.out.println(count);
        }
    }
}
