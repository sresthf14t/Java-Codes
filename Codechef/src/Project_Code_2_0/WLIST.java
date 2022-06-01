/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Code_2_0;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import java.math.*;
public class WLIST {
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
    static int indx;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            indx=-1;
            int n=input.scanInt();
            int arr[]=new int[3];
            for(int i=0;i<arr.length;i++) {
                arr[i]=input.scanInt();
            }
            ArrayList<Integer> arrli=create_lst(n, arr);
            ArrayList<Integer> tmp=new ArrayList<>();
            for(int i=indx;i<arrli.size();i++) {
                tmp.add(arrli.get(i));
            }
//            for(int i=0;i<arrli.size();i++) {
//                System.out.print(arrli.get(i)+" ");
//            }
//            System.out.println();
            int query=input.scanInt();
            for(int q=1;q<=query;q++) {
                int tmp_indx=input.scanInt();
                if(tmp_indx<arrli.size()) {
                    ans.append(arrli.get(tmp_indx)+"\n");
//                    System.out.println(arrli.get(indx));
                }
                else {
                    tmp_indx-=indx;
                    int len=arrli.size()-indx;
                    ans.append(tmp.get(tmp_indx%len)+"\n");
                }
            }
        }
        System.out.println(ans);
    }
    public static ArrayList<Integer> create_lst(int n,int arr[]) {
        ArrayList<Integer> arrli=new ArrayList<>();
        arrli.add(n);
        HashMap<Integer,Integer> map=new HashMap<>();
        boolean br=false;
        while(true) {
            for(int i=0;i<arr.length;i++) {
                n=arrli.get(arrli.size()-1);
//                System.out.print(n+" ");
                BigDecimal tmp=new BigDecimal(""+n);
                BigDecimal div=tmp.divide(new BigDecimal(""+arr[i]), 10,RoundingMode.HALF_UP);
                String str=""+div;
                for(int j=0;j<str.length();j++) {
                    if(str.charAt(j)=='.') {
                        int add;
//                        System.out.println("Enter");
                        if(str.charAt(j+1)!='0') {
                            add=(str.charAt(j+1)-48);
                        }
                        else {
                            add=(str.charAt(0)-48);
                        }
                        if(i==0 && map.containsKey(add)) {
                            indx=map.get(add);
                            br=true;
                            break;
                        }
                        arrli.add(add);
                        if(i==0) {
                            map.put(add,arrli.size()-1);
                        }
                        break;
                    }
                }
                if(br) {
                    break;
                }
            }
//            System.out.println();
            if(br) {
                break;
            }
        }
        return arrli;
    }
}
