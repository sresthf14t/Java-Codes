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
public class E_New_Year_Parties {
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
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        Arrays.sort(arr);
        System.out.println(minimize(n,arr)+" "+maximize(n,arr));
    }
    public static int minimize(int n,int arr[]) {
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i<n;i++) {
            int j=i;
            while(j<n && arr[j]==arr[i]) {
                j++;
            }
            arrli.add(arr[i]);
            i=j-1;
        }
        int ans=0;
        int cnt=1;
        ArrayList<Boolean> moved =new ArrayList<>();
        for(int i=0;i<arrli.size();i++) {
            moved.add(Boolean.FALSE);
        }
        for(int i=0;i<arrli.size();i++) {
            if(i!=0 && !moved.get(i) && arrli.get(i)-1==arrli.get(i-1)) {
                arrli.set(i, arrli.get(i-1));
                moved.set(i, Boolean.TRUE);
                moved.set(i-1, Boolean.TRUE);
            }
            else if(i!=arrli.size()-1 && !moved.get(i) && arrli.get(i)+1==arrli.get(i+1)) {
                arrli.set(i, arrli.get(i+1));
                moved.set(i, Boolean.TRUE);
                moved.set(i+1, Boolean.TRUE);
            }
            else if(i!=0 && !moved.get(i-1) && !moved.get(i) && arrli.get(i)-2==arrli.get(i-1)) {
                arrli.set(i-1, arrli.get(i-1)+1);
                arrli.set(i, arrli.get(i-1)+1);
                moved.set(i-1, Boolean.TRUE);
                moved.set(i, Boolean.TRUE);
            }
            else if(i!=arrli.size()-1 && !moved.get(i+1) && !moved.get(i) && arrli.get(i)+2==arrli.get(i+1)) {
                arrli.set(i+1, arrli.get(i)+1);
                arrli.set(i, arrli.get(i)+1);
                moved.set(i+1, Boolean.TRUE);
                moved.set(i, Boolean.TRUE);
                i++;
            }
        }
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<arrli.size();i++) {
//            System.out.print(arrli.get(i)+" ");
            hashset.add(arrli.get(i));
        }
//        System.out.println();
        return hashset.size();
    }
    public static int maximize(int n,int arr[]) {
        ArrayList<Integer> arrli=new ArrayList<>();
        ArrayList<Integer> freq=new ArrayList<>();
        for(int i=0;i<n;i++) {
            int j=i,cnt=0;
            while(j<n && arr[j]==arr[i]) {
                j++;
                cnt++;
            }
            arrli.add(arr[i]);
            freq.add(cnt);
            i=j-1;
        }
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<arrli.size();i++) {
            if(freq.get(i)==1) {
                if(!hashset.contains(arrli.get(i)-1)) {
                    hashset.add(arrli.get(i)-1);
                }
                else if(!hashset.contains(arrli.get(i))){
                    hashset.add(arrli.get(i));
                }
                else if(!hashset.contains(arrli.get(i)+1)){
                    hashset.add(arrli.get(i)+1);
                }
            }
            else if(freq.get(i)==2) {
                if(!hashset.contains(arrli.get(i)-1)) {
                    hashset.add(arrli.get(i)-1);
                }
                else if(!hashset.contains(arrli.get(i))) {
                    hashset.add(arrli.get(i));
                }
                else if(!hashset.contains(arrli.get(i)+1)) {
                    hashset.add(arrli.get(i)+1);
                }
                
                if(!hashset.contains(arrli.get(i)-1)) {
                    hashset.add(arrli.get(i)-1);
                }
                else if(!hashset.contains(arrli.get(i))) {
                    hashset.add(arrli.get(i));
                }
                else if(!hashset.contains(arrli.get(i)+1)) {
                    hashset.add(arrli.get(i)+1);
                }
            }
            else {
                hashset.add(arrli.get(i));
                if(!hashset.contains(arrli.get(i)-1)) {
                    hashset.add(arrli.get(i)-1);
                }
                if(!hashset.contains(arrli.get(i)+1)) {
                    hashset.add(arrli.get(i)+1);
                }
            }
        }
        return hashset.size();
    }
}
