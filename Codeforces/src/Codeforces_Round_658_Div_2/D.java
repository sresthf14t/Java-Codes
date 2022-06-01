/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_658_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int n,arr[],mod;
    static HashMap<Integer,Boolean> dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            arr=new int[2*n];
            for(int i=0;i<2*n;i++) {
                arr[i]=input.scanInt();
            }
            if(solve()) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    public static boolean solve() {
        int arr1[]=new int[n];
        int arr2[]=new int[n];
        int indx1=0,indx2=0,indx=0;
        boolean fst=true;
        ArrayList<Integer> arrli[]=new ArrayList[2*n];
        for(int i=0;i<arr.length;i++) {
            int j=i;
            arrli[indx]=new ArrayList<>();
            while(j<arr.length-1 && arr[j+1]>arr[j]) {
                arrli[indx].add(arr[j]);
                j++;
            }
            arrli[indx].add(arr[j]);
            indx++;
            i=j;
        }
//        for(int i=0;i<arrli.length;i++) {
//            if(arrli[i]==null) {
//                break;
//            }
//            for(int j=0;j<arrli[i].size();j++) {
//                System.out.print(arrli[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<arrli.length;i++) {
            if(arrli[i]==null || arrli[i].size()==0) {
                break;
            }
            if(indx1==n) {
                for(int j=0;j<arrli[i].size();j++) {
                    arr2[indx2]=arrli[i].get(j);
                    indx2++;
                }
                continue;
            }
            if(indx2==n) {
                for(int j=0;j<arrli[i].size();j++) {
                    arr1[indx1]=arrli[i].get(j);
                    indx1++;
                }
                continue;
            }
            if(fst) {
                if(arrli[i].size()==1) {
                    arr1[indx1]=arrli[i].get(0);
                    indx1++;
                    continue;
                } 
                for(int j=0;j<arrli[i].size()-1;j++) {
                    if(indx1==n) {
                        arr2[indx2]=arrli[i].get(j);
                        indx2++;
                        continue;
                    }
                    arr1[indx1]=arrli[i].get(j);
                    indx1++;
                }
                arr2[indx2]=arrli[i].get(arrli[i].size()-1);
                indx2++;
                fst=!fst;
            }
            else {
                if(arrli[i].size()==1) {
                    arr2[indx2]=arrli[i].get(0);
                    indx2++;
                    continue;
                } 
                for(int j=0;j<arrli[i].size()-1;j++) {
                    if(indx2==n) {
                        arr1[indx1]=arrli[i].get(j);
                        indx1++;
                        continue;
                    }
                    arr2[indx2]=arrli[i].get(j);
                    indx2++;
                }
                arr1[indx1]=arrli[i].get(arrli[i].size()-1);
                indx1++;
                fst=!fst;
            }
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(arr1[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(arr2[i]+" ");
//        }
//        System.out.println();
        if(check(arr1,arr2)) {
            return true;
        }
        return false;
    }
    
    public static boolean check(int arr1[],int arr2[]) {
        int indx=0,indx1=0,indx2=0;
        for(int i=0;i<arr.length;i++) {
            if(indx1==n) {
                if(arr2[indx2]==arr[indx]) {
                    indx2++;
                    indx++;
                    continue;
                }
                return false;
            }
            if(indx2==n) {
                if(arr1[indx1]==arr[indx]) {
                    indx1++;
                    indx++;
                    continue;
                }
                return false;
            }
            if(arr1[indx1]<=arr2[indx2]) {
                if(arr1[indx1]==arr[indx]) {
                    indx1++;
                    indx++;
                    continue;
                }
                return false;
            }
            if(arr1[indx1]>=arr2[indx2]) {
                if(arr2[indx2]==arr[indx]) {
                    indx2++;
                    indx++;
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
