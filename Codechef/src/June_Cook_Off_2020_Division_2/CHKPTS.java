/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Cook_Off_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CHKPTS {
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
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int c=input.scanInt();
            int arr_x[]=new int[n];
            int arr_y[]=new int[n];
            for(int i=0;i<n;i++) {
                arr_x[i]=input.scanInt();
                arr_y[i]=input.scanInt();
            }
            HashMap<Integer,Integer> map=new HashMap<>();
            ArrayList<Integer> arrli[]=new ArrayList[n];
            int cnt=0;
            for(int i=0;i<n;i++) {
                if(map.containsKey(arr_y[i]-arr_x[i])) {
                    int k=arr_y[i]-arr_x[i];
                    int indx=map.get(k);
                    arrli[indx].add(i);
                }
                else {
                    int k=arr_y[i]-arr_x[i];
                    map.put(k, cnt);
                    arrli[cnt]=new ArrayList<>();
                    arrli[cnt].add(i);
                    cnt++;
                }
            }
            long points=0,dist=0;
            for(int i=0;i<arrli.length;i++) {
                if(arrli[i]==null) {
                    break;
                }
                long tmp[]=solve(arr_x,arrli[i],c);
                points+=tmp[0];
                dist+=tmp[1];
            }
            System.out.println(points+" "+dist);
        }
        
    }
    public static long[] solve(int arr_x[],ArrayList<Integer> arrli,int c) {
        int arr[]=new int[arrli.size()];
        HashMap<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> tmp[]=new ArrayList[arrli.size()];
        for(int i=0;i<arrli.size();i++) {
            arr[i]=arr_x[arrli.get(i)];
        }
        
        int cnt=0;
        for(int i=0;i<arr.length;i++) {
            int k=arr[i]%c;
            if(k<0) {
                k+=c;
            }
            if(map.containsKey(k)) {
                int indx=map.get(k);
                tmp[indx].add(arr[i]);
            }
            else {
                map.put(k, cnt);
                tmp[cnt]=new ArrayList<>();
                tmp[cnt].add(arr[i]);
                cnt++;
            }
        }
        long cnt_points=0,dist=0;
        for(int i=0;i<tmp.length;i++) {
            if(tmp[i]==null) {
                break;
            }
            cnt_points++;
            dist+=count(tmp[i],c);
        }
        return new long[]{cnt_points,dist};
    }
    
    public static long count(ArrayList<Integer> arr,int c) {
        arr.sort(null);
//        for(int i=0;i<arr.size();i++) {
//            System.out.print(arr.get(i)+" ");
//        }
//        System.out.println();
        if(arr.size()%2==1) {
            int mid=(arr.size()/2);
            long sum=0;
            for(int i=0;i<arr.size();i++) {
                sum+=Math.abs(arr.get(i)-arr.get(mid));
            }
            return sum/c;
        }
        int mid1=arr.size()/2,mid2=mid1-1;
        long sum1=0,sum2=0;
        for(int i=0;i<arr.size();i++) {
            sum1+=Math.abs(arr.get(i)-arr.get(mid1));
        }
        for(int i=0;i<arr.size();i++) {
            sum2+=Math.abs(arr.get(i)-arr.get(mid2));
        }
        sum1/=c;
        sum2/=c;
//        System.out.println(sum1+" "+sum2);
        return Math.min(sum1,sum2);
    }
}
