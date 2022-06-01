/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class SQRDSUB_2 {
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
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                arr[i]=Math.abs(arr[i]);
            }
            long count=0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i=0;i<n;i++) {
                int j=i+1;
                while(j<n && arr[j]%2!=0) {
                    j++;
                }
                for(int k=i;k<j;k++) {
                    if(j<n && arr[j]%2==0) {
                        map.put(k, j);
                    }
                }
                i=j-1;
            }
//            System.out.println();
//            for(int i=0;i<n;i++) {
//                if(map.containsKey(i)) {
//                    System.out.print(map.get(i)+" ");
//                }
//                else {
//                    System.out.print(0+" ");
//                }
//            }
//            System.out.println();
            //Counting odd
            long counter=0;
            for(int i=0;i<n;i++) {
                if(arr[i]%2==1) {
                    counter++;
                }
                else {
                    count+=((counter*(counter+1))/2);
                    counter=0;
                }
            }
            count+=((counter*(counter+1))/2);
            //Counting multiple of 4
            for(int i=0;i<n;i++) {
                if(arr[i]%2!=0) {
                    if(map.containsKey(i)) {
                        int j=map.get(i);
                        if(arr[j]%4==0) {
                            count+=(n-j);
                        }
                        else if(map.containsKey(j)){
                            int k=map.get(j);
                            count+=(n-k);
                        }
                    }
                }
                else if(arr[i]%4==0) {
                    count+=(n-i);
                }
                else if(arr[i]%2==0) {
                    if(map.containsKey(i)) {
                        int j=map.get(i);
                        count+=(n-j);
                    }
                }
            }
            ans.append(count+"\n");
        }
        System.out.println(ans);
    }
}
