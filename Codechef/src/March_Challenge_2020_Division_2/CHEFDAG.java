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
import java.util.*; 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class CHEFDAG {
    
    static int[] fin_out;
    static int min_count;
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
            int k=input.nextInt();
            int arr[][]=new int[k][n];
            boolean in[]=new boolean[n+1];
            int out[]=new int[n+1];
            for(int i=0;i<k;i++) {
                for(int j=0;j<n;j++) {
                    arr[i][j]=input.nextInt();
                }
            }
            ArrayList<Integer> pos_con[] = new ArrayList[n+1];
            for(int i=1;i<=n;i++) {
                pos_con[i]=new ArrayList<Integer>();
                for(int j=1;j<=n;j++) {
                    if(j!=i) {
                        pos_con[i].add(j);
                    }
                }
            }
            
            for(int i=0;i<k;i++) {
                for(int j=n-1;j>0;j--) {
                    for(int l=j-1;l>=0;l--) {
                        pos_con[arr[i][j]].remove(new Integer(arr[i][l]));
                    }
                }
            }
            
//            for(int i=1;i<=n;i++) {
//                System.out.print(i+"> ");
//                for(int j=0;j<pos_con[i].size();j++) {
//                    System.out.print(pos_con[i].get(j)+" ");
//                }
//                System.out.println();
//            }
            
//            for(int i=1;i<=n;i++) {
//                for(int j=0;j<pos_con[i].size();j++) {
//                    if(!in[pos_con[i].get(j)]) {
//                        out[i]=pos_con[i].get(j);
//                        in[pos_con[i].get(j)]=true;
//                        break;
//                    }
//                }
//            }

//            int count=0;
//            for(int i=1;i<=n;i++) {
//                if(!in[i]) {
//                    count++;
//                }
//            }
            
            int last_indx=-1;
            for(int i=n;i>=1;i--) {
                if(pos_con[i].size()!=0) {
                    last_indx=i;
                    break;
                }
            }
            in=new boolean[n+1];
            min_count=n;
            fin_out=new int[n+1];
            combs(in,out,pos_con,1,last_indx);
            System.out.println(min_count);
            for(int i=1;i<=n;i++) {
                if(i==1) {
                    System.out.print(fin_out[i]);
                }
                else {
                    System.out.print(" "+fin_out[i]);
                }
            }
            System.out.println();
        }
    }
    
    
    public static void combs(boolean in[],int out[],ArrayList<Integer> arr[],int indx,int last_indx) {
        if(indx<last_indx && arr[indx].size()==0) {
            combs(in,out,arr,indx+1,last_indx);
        }
        for(int i=0;i<arr[indx].size();i++) {
            
            if(!in[arr[indx].get(i)]) {
                out[indx]=arr[indx].get(i);
                in[arr[indx].get(i)]=true;
            }
            if(indx==last_indx) {
//                print(in,out);
                check(in,out);
            }
            else {
                combs(in,out,arr,indx+1,last_indx);
            }
            in[arr[indx].get(i)]=false;
        }
    }
    
    public static void check(boolean in[],int out[]) {
        int count=0;
        for(int i=1;i<out.length;i++) {
            if(!in[i]) {
                count++;
            }
        }
        if(count<min_count) {
            min_count=count;
            clone(out);
        }
    }
    public static void clone(int out[]) {
        for(int i=1;i<out.length;i++) {
            fin_out[i]=out[i];
        }
    }
    public static void print(boolean in[],int out[]) {
        for(int i=1;i<out.length;i++) {
            System.out.print(out[i]+" ");
        }
        System.out.println();
        for(int i=1;i<in.length;i++) {
            System.out.print(in[i]+" ");
        }
        System.out.println("\n");
    }
}
