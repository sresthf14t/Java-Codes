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
public class LAZER_2 {
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
            int q=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            
            int chng[]=new int[n];
            
            
            for(int i=0;i<n-1;i++) {
                int j=i;
                
                boolean inc=false;
                while(j<n-1 && arr[j+1]>arr[j]) {
                    j++;
                    inc=true;
                }
                for(int k=i;k<j && inc;k++) {
                    chng[k]=j;
                }
                if(inc) {
                    i=j-1;
                    continue;
                }
                
                boolean dec=false;
                while(j<n-1 && arr[j+1]<arr[j]) {
                    j++;
                    dec=true;
                }
                for(int k=i;k<j && dec;k++) {
                    chng[k]=j;
                }
                
                i=j-1;
            }
            
//            for(int i=0;i<n;i++) {
//                System.out.print(chng[i]+" ");
//            }
//            System.out.println();
            
            
            int pre_comp[][]=new int[q][n];
            HashMap<Integer, Integer> map = new HashMap<>(); 
            StringBuilder ans=new StringBuilder("");
            int indx_count=-1;
            for(int i=0;i<q;i++) {
                int x1=input.nextInt();
                int x2=input.nextInt();
                x1--;
                x2--;
                int y=input.nextInt();
                if(map.containsKey(y)) {
                    int indx=map.get(y);
                    int tmp_ans=pre_comp[indx][x2]-pre_comp[indx][x1];
                    ans.append(tmp_ans+"\n");
                    continue;
                }
                int count=0;
                indx_count++;
                map.put(y, indx_count);
                for(int j=0;j<n-1;j++) {
                    if(arr[j]<=y && arr[j+1]>=y) {
                        count++;
                        j=chng[j]-1;
                    }
                    else if(arr[j+1]<=y && arr[j]>=y) {
                        count++;
                        j=chng[j]-1;
                    }
                    pre_comp[indx_count][j]=count;
                }
//                map.put(x1+" "+y+" "+x2, count);
                int tmp_ans=pre_comp[indx_count][x2]-pre_comp[indx_count][x1];
                ans.append(tmp_ans+"\n");
            }
            System.out.println(ans);
        }
    }
}
