/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_Little_Elephant_and_Array {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int arr[]=new int[n];
        int cpy[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            cpy[i]=arr[i];
        }
        ArrayList<Integer> arrli=new ArrayList<>();
        Arrays.sort(cpy);
        for(int i=0;i<n;i++) {
            int j=i,cnt=0;
            while(j<n && cpy[j]==cpy[i]) {
                j++;
                cnt++;
            }
            if(cnt>=cpy[i]) {
                arrli.add(cpy[i]);
            }
            i=j-1;
        }
        int freq[][]=new int[arrli.size()][n];
        for(int i=0;i<arrli.size();i++) {
            int cnt=0;
            for(int j=0;j<n;j++) {
                if(arr[j]==arrli.get(i)) {
                    cnt++;
                }
                freq[i][j]=cnt;
            }
        }
//        for(int i=0;i<freq.length;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(freq[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println(arrli.size());
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<m;i++) {
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
            int cnt=0;
            for(int j=0;j<freq.length;j++) {
                if((freq[j][r]-(l>0?freq[j][l-1]:0))==arrli.get(j)) {
                    cnt++;
                }
            }
            ans.append(cnt+"\n");
        }
        System.out.println(ans);
    }
}
