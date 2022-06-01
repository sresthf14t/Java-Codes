/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_631_Div_2;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class B {
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
        StringBuilder fin_ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            int arr1[]=new int[n];
            int arr2[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                arr1[i]=arr[i];
                arr2[i]=arr[i];
            }
            Set<Integer> hash_set=new HashSet<>();
            int indx=-1;
            int count=0;
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<n;i++){
                if(hash_set.contains(arr[i])) {
                    indx=i;
                    break;
                }
                hash_set.add(arr[i]);
            }
            if(indx!=-1 && check(arr1,indx)) {
                count++;
                ans.append(indx+" "+(arr.length-indx)+"\n");
            }
            int indx2=-1;
            hash_set=new HashSet<>();
            for(int i=arr.length-1;i>=0;i--){
                if(hash_set.contains(arr[i])) {
                    indx2=i;
                    break;
                }
                hash_set.add(arr[i]);
            }
            if(indx2!=-1 && (indx2+1)!=indx && check(arr2,indx2+1)) {
                count++;
                ans.append((indx2+1)+" "+(arr.length-indx2-1));
            }
            fin_ans.append("\n"+count+"\n"+ans);
        }
        System.out.println(fin_ans);
    }
    public static boolean check(int arr[],int indx) {
        Arrays.sort(arr,0,indx);
        Arrays.sort(arr,indx,arr.length);
        for(int i=0,j=1;i<indx;i++,j++) {
            if(arr[i]!=j) {
                return false;
            }
        }
        for(int i=indx,j=1;i<arr.length;i++,j++) {
            if(arr[i]!=j) {
                return false;
            }
        }
        return true;
    }
}