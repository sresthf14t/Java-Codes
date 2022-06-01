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
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class NOCHANGE {
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
            int p=input.nextInt();
            int arr[]=new int[n];
            int arr_cpy[]=new int[n];
            int count[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
                arr_cpy[i]=arr[i];
            }
            Arrays.sort(arr_cpy);
            boolean all_div=true,all_mul=true;
            int div_indx=-1,mul_indx=-1;
            for(int i=0;i<n;i++) {
                if(p%arr_cpy[i]!=0) {
                    all_div=false;
                    div_indx=i;
                    break;
                }
            }
            if(all_div) {
                for(int i=0;i<n-1;i++) {
                    if(arr_cpy[i+1]%arr_cpy[i]!=0) {
                        all_mul=false;
                        mul_indx=i;
                        break;
                    }
                }
            }
            //System.out.println(all_div+" "+all_mul);
            if(all_div && all_mul) {
                System.out.println("NO");
            }
            else {
                if(!all_div) {
                    int tmp=p/arr_cpy[div_indx];
                    for(int i=0;i<n;i++) {
                        if(arr[i]==arr_cpy[div_indx]) {
                            count[i]=tmp+1;
                            break;
                        }
                    }
                    System.out.print("YES");
                    for(int i=0;i<n;i++) {
                        System.out.print(" "+count[i]);
                    }
                    System.out.println();
                }
                else {
                    int tmp=p/arr_cpy[mul_indx+1],sum=tmp*arr_cpy[mul_indx+1],count1=tmp,count2=0;
                    if(p%arr_cpy[mul_indx+1]==0) {
                        sum=(tmp-1)*arr_cpy[mul_indx+1];
                        count1=tmp-1;
                    }
                    while(sum<p) {
                        sum+=arr_cpy[mul_indx];
                        count2++;
                    }
                    boolean tmp1=false,tmp2=false;
                    for(int i=0;i<n;i++) {
                        if(arr[i]==arr_cpy[mul_indx+1]) {
                            count[i]=count1;
                            tmp1=true;
                        }
                        if(arr[i]==arr_cpy[mul_indx]) {
                            count[i]=count2;
                            tmp2=true;
                        }
                        if(tmp1 && tmp2) {
                            break;
                        }
                    }
                    System.out.print("YES");
                    for(int i=0;i<n;i++) {
                        System.out.print(" "+count[i]);
                    }
                    System.out.println();
                }
            }
        }
    }
}
