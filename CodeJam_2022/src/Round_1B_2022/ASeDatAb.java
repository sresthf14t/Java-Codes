/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_1B_2022;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class ASeDatAb {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    static String curr;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        int pow[]=new int[10];
        pow[0]=1;
        String p[]=new String[]{"00000001", "00000010" ,"00000100", "00001000", "00010000", "00100000", "01000000", "10000000", "11111111"};
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        int max=0;
        for(int tt=1;tt<=test;tt++) {
            curr=gen();
            int cnt=0;
            int in=0;
            while(true) {
                int indx=(int)(Math.random()*8);
//                System.out.println(p[0]);
                System.out.flush();
                cnt++;
//                int in=input.scanInt();
                in=grade(gen(8-in));
//                int in=grade(gen(1));
                if(in==0) {
                    break;
                }
                if(in==-1) {
                    System.exit(0);
                }
                if(false && in==2) {
                    in=grade(gen(2));
                    cnt++;
                    if(in==0) {
                        break;
                    }
                }
                if(in==8) {
//                    System.out.println(p[8]);
                    System.out.flush();
//                    in=input.scanInt();
                    in=grade(p[8]);
                    if(in==-1) {
                        System.exit(0);
                    }
                    cnt++;
                    break;
                }
                if(false && in<4) {
//                    System.out.println(p[8]);
                    System.out.flush();
//                    in=input.scanInt();
                    in=grade("11111111");
                    if(in==-1) {
                        System.exit(0);
                    }
                    cnt++;
                }
            }
//            System.out.println(cnt);
            max=Math.max(max, cnt);
        }
        System.out.println(max);
    }
    
    public static String gen(int r) {
        while(true) {
            StringBuilder tmp=new StringBuilder("00000000");
            for(int i=0;i<4;i++) {
                int indx=(int)(Math.random()*8);
                if(indx==8) {
                    continue;
                }
                if(tmp.charAt(indx)=='1') {
                    continue;
                }
                tmp.setCharAt(indx, '1');
            }
            return ""+tmp;
        }
    }
    
    public static String gen() {
        String tmp="";
        for(int i=0;i<8;i++) {
            int val=(int)(Math.random()*100);
            tmp+=val%2;
        }
        return tmp;
    }
    
    public static int grade(String val) {
        val=rot(val,(int)(Math.random()*8));
        xor(val);
        int cnt=0;
        for(int i=0;i<curr.length();i++) {
            if(curr.charAt(i)=='1') {
                cnt++;
            }
        }
        return cnt;
    }
    
    public static void xor(String val) {
        String tmp="";
        for(int i=0;i<val.length();i++) {
            if(curr.charAt(i)=='0' && val.charAt(i)=='0') {
                tmp+='0';
            }
            if(curr.charAt(i)=='0' && val.charAt(i)=='1') {
                tmp+='1';
            }
            if(curr.charAt(i)=='1' && val.charAt(i)=='0') {
                tmp+='1';
            }
            if(curr.charAt(i)=='1' && val.charAt(i)=='1') {
                tmp+='0';
            }
        }
        curr=""+tmp;
    }
    public static String rot(String val, int r) {
        if(r==0) {
            return val;
        }
        String tmp=val.substring(val.length()-r, val.length())+val.substring(0, val.length()-r);
        return tmp;
    }
}
