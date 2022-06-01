/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B_Two_Sets {
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
    
    static int n,a,b,arr[];
    static Set<Integer> hashset;
    static TreeSet<Integer> one,two;
    static HashMap<Integer,Integer> map;
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        a=input.scanInt();
        b=input.scanInt();
        arr=new int[n];
        hashset=new HashSet<>();
        one=new TreeSet<>();
        two=new TreeSet<>();
        map=new HashMap<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            hashset.add(arr[i]);
        }
        for(int i=0;i<n;i++) {
            if(hashset.contains(a-arr[i]) && hashset.contains(b-arr[i])) {
                two.add(arr[i]);
            }
            else if(hashset.contains(a-arr[i])) {
                one.add(arr[i]);
            }
            else if(hashset.contains(b-arr[i])) {
                one.add(arr[i]);
            }
            else {
                System.out.println("NO");
                return;
            }
        }
        
        
        two();
        
        StringBuilder ans=new StringBuilder("YES\n");
        for(int i=0;i<n;i++) {
            ans.append(map.get(arr[i])+" ");
        }
        System.out.println(ans);
    }
    
    public static void one() {
        while(!one.isEmpty()) {
            int val=one.first();
            if(one.contains(a-val)) {
                map.put(val, 0);
                map.put(a-val, 0);
                one.remove(val);
                one.remove(a-val);
                
                if(one.contains(b-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(b-(a-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                
                if(two.contains(b-val)) {
                    two.remove(b-val);
                    one.add(b-val);
                }
                if(two.contains(b-(a-val))) {
                    two.remove(b-(a-val));
                    one.add(b-(a-val));
                }
            }
            
            else if(one.contains(b-val)) {
                map.put(val, 1);
                map.put(b-val, 1);
                one.remove(val);
                one.remove(b-val);
                
                if(one.contains(a-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(a-(b-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                
                if(two.contains(a-val)) {
                    two.remove(a-val);
                    one.add(a-val);
                }
                if(two.contains(a-(b-val))) {
                    two.remove(a-(b-val));
                    one.add(a-(b-val));
                }
            }
            
            else if(two.contains(a-val)) {
                map.put(val, 0);
                map.put(a-val, 0);
                one.remove(val);
                two.remove(a-val);
                
                if(one.contains(b-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(b-(a-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(two.contains(b-val)) {
                    two.remove(b-val);
                    one.add(b-val);
                }
                if(two.contains(b-(a-val))) {
                    two.remove(b-(a-val));
                    one.add(b-(a-val));
                }
            }
            
            
            else if(two.contains(b-val)) {
                map.put(val, 1);
                map.put(b-val, 1);
                one.remove(val);
                two.remove(b-val);
                
                if(one.contains(a-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(a-(b-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(two.contains(a-val)) {
                    two.remove(a-val);
                    one.add(a-val);
                }
                if(two.contains(a-(b-val))) {
                    two.remove(a-(b-val));
                    one.add(a-(b-val));
                }
            }
        }
    }
    public static void two() {
        one();
        while(!two.isEmpty()) {
            one();
            int val=two.first();
            
            if(two.contains(a-val)) {
                map.put(val, 0);
                map.put(a-val, 0);
                two.remove(val);
                two.remove(a-val);
                
                if(one.contains(b-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(b-(a-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(two.contains(b-val)) {
                    two.remove(b-val);
                    one.add(b-val);
                }
                if(two.contains(b-(a-val))) {
                    two.remove(b-(a-val));
                    one.add(b-(a-val));
                }
            }
            
            
            else if(two.contains(b-val)) {
                map.put(val, 1);
                map.put(b-val, 1);
                two.remove(val);
                two.remove(b-val);
                
                if(one.contains(a-val)) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(one.contains(a-(b-val))) {
                    System.out.println("NO");
                    System.exit(0);
                }
                if(two.contains(a-val)) {
                    two.remove(a-val);
                    one.add(a-val);
                }
                if(two.contains(a-(b-val))) {
                    two.remove(a-(b-val));
                    one.add(a-(b-val));
                }
            }
        }
    }
        
}
