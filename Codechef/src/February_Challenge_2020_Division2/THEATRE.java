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
import java.util.Scanner;
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class THEATRE {
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
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        char ch;
        int tmp,tmp1,tmp2=0,total_profit=0;
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int arr[][]=new int[4][4];
            int comb[]={1234,1243,1324,1342,1423,1432,2134,2143,2314,2341,2413,2431,3124,3142,3214,3241,3412,3421,4123,4132,4213,4231,4312,4321};
            for(int i=0;i<n;i++) {
                ch=input.next().charAt(0);
                tmp=input.nextInt();
                tmp1=ch-65;
                if(tmp==12) {
                    tmp2=0;
                }
                else if(tmp==3) {
                    tmp2=1;
                }
                else if(tmp==6) {
                    tmp2=2;
                }
                else if(tmp==9) {
                    tmp2=3;
                }
                arr[tmp1][tmp2]++;
            }
//            for(int i=0;i<4;i++) {
//                for(int j=0;j<4;j++) {
//                    if(j==0) {
//                        arr[i][j]*=25;
//                    }
//                    else if(j==1) {
//                        arr[i][j]*=50;
//                    }
//                    else if(j==2) {
//                        arr[i][j]*=75;
//                    }
//                    else if(j==3) {
//                        arr[i][j]*=100;
//                    }
//                }
//            }
            
//            for(int i=0;i<4;i++) {
//                for(int j=0;j<4;j++) {
//                    System.out.print(arr[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.exit(0);
            int total=-500;
            for(int i=0;i<comb.length;i++) {
                tmp=0;
                int tmp_arr[]=new int[4];
                for(int j=0;j<4;j++) {
                    tmp_arr[j]=arr[(comb[i]%10)-1][j];
                    comb[i]/=10;
                }
                tmp=Tot(tmp_arr);
//                System.out.println(tmp);
                if(tmp>total) {
                    total=tmp;
                }
            }
            System.out.println(total);
            total_profit+=total;
        }
        System.out.println(total_profit);
    }
    public static int Tot(int arr[]) {
        int price[]={25,50,75,100};
        Arrays.sort(arr);
        int total=0;
        for(int i=3,j=3;i>=0;i--) {
            if(arr[i]==0) {
                total-=100;
            }
            else {
                total+=arr[i]*price[j];
                j--;
            }
        }
        return total;
    }
        
}
