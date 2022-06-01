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
public class C_Devu_and_Partitioning_of_the_Array {
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
        int k=input.scanInt();
        int p=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        System.out.println(solve(n,k,p,arr));
    }
    public static StringBuilder solve(int n,int k,int p,int arr[]) {
        ArrayList<Integer> even=new ArrayList<>();
        ArrayList<Integer> odd=new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(arr[i]%2==0) {
                even.add(arr[i]);
            }
            else {
                odd.add(arr[i]);
            }
        }
        StringBuilder ans=new StringBuilder("YES");
        ArrayList<Integer> tmp=new ArrayList<>();
        int e_rem=p;
        for(int i=0;i<even.size() && e_rem!=0;i++) {
            tmp=new ArrayList<>();
            if(e_rem==1) {
                for(int j=i;j<even.size();j++) {
                    tmp.add(even.get(j));
                }
                if(k-p==0) {
                    if(odd.size()%2==1) {
                        return new StringBuilder("NO");
                    }
                    for(int j=0;j<odd.size();j++) {
                        tmp.add(odd.get(j));
                    }
                }
                copy(ans,tmp);
                e_rem--;
                break;
            }
            else {
                tmp.add(even.get(i));
            }
            e_rem--;
            copy(ans,tmp);
        }
        int i=0;
        for(;i<odd.size() && e_rem!=0;i++) {
            tmp=new ArrayList<>();
            tmp.add(odd.get(i));
            i++;
            if(i==odd.size()) {
                break;
            }
            tmp.add(odd.get(i));
            if((k-p)==0 && e_rem==1) {
                for(int j=i+1;j<odd.size();j++) {
                    tmp.add(odd.get(j));
                }
                if(tmp.size()%2==1) {
                    return new StringBuilder("NO");
                }
                i=odd.size();
                copy(ans,tmp);
                e_rem--;
                break;
            }
            copy(ans,tmp);
            e_rem--;
        }
        
        int o_rem=k-p;
        for(;i<odd.size() && o_rem!=0;i++) {
            tmp=new ArrayList<>();
            if(o_rem==1) {
                if(p==0) {
                    for(int j=0;j<even.size();j++) {
                        tmp.add(even.get(j));
                    }
                }
                int count=0;
                for(int j=i;j<odd.size();j++) {
                    count++;
                    tmp.add(odd.get(j));
                }
                if(count%2==0) {
                    return new StringBuilder("NO");
                }
                copy(ans,tmp);
                o_rem--;
                break;
            }
            else {
                tmp.add(odd.get(i));
            }
            o_rem--;
            copy(ans,tmp);
        }
        if(e_rem!=0 || o_rem!=0) {
            return new StringBuilder("NO");
        }
        return ans;
    }
    public static void copy(StringBuilder ans,ArrayList<Integer> arrli) {
        ans.append("\n");
        ans.append(arrli.size()+" ");
        for(int i=0;i<arrli.size();i++) {
            ans.append(arrli.get(i)+" ");
        }
    }
}
