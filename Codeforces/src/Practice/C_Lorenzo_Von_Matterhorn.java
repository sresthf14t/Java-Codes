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
public class C_Lorenzo_Von_Matterhorn {
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
    
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        StringBuilder ans=new StringBuilder("");
        int query=input.nextInt();
        HashMap<Long,Long> map=new HashMap<>();
        for(int qq=1;qq<=query;qq++) {
            int type=input.nextInt();
            if(type==1) {
                long u=input.nextLong();
                long v=input.nextLong();
                long w=input.nextLong();
                update(u,v,w,map);
            }
            else {
                long u=input.nextLong();
                long v=input.nextLong();
                ans.append(get(u,v,map)+"\n");
            }
        }
        System.out.println(ans);
    }
    public static void update(long u,long v,long w,HashMap<Long,Long> map) {
        ArrayList<Long> arr1=new ArrayList<>();
        ArrayList<Long> arr2=new ArrayList<>();
        Set<Long> hashset=new HashSet<>();
        long tmp=u;
        while(tmp!=0) {
            arr1.add(tmp);
            tmp/=2;
        }
        tmp=v;
        while(tmp!=0) {
            arr2.add(tmp);
            hashset.add(tmp);
            tmp/=2;
        }
        long end=-1;
        for(int i=0;i<arr1.size();i++) {
            if(hashset.contains(arr1.get(i))) {
                end=arr1.get(i);
                break;
            }
        }
//        System.out.println(end);
        for(int i=0;i<arr1.size();i++) {
            if(arr1.get(i)==end) {
                break;
            }
//            System.out.println(arr1.get(i));
            if(map.containsKey(arr1.get(i))) {
                map.replace(arr1.get(i), map.get(arr1.get(i))+w);
            }
            else {
                map.put(arr1.get(i), w);
            }
        }
        for(int i=0;i<arr2.size();i++) {
            if(arr2.get(i)==end) {
                break;
            }
//            System.out.println(arr2.get(i));
            if(map.containsKey(arr2.get(i))) {
                map.replace(arr2.get(i), map.get(arr2.get(i))+w);
            }
            else {
                map.put(arr2.get(i), (long)w);
            }
        }
    }
    public static long get(long u,long v,HashMap<Long,Long> map) {
        ArrayList<Long> arr1=new ArrayList<>();
        ArrayList<Long> arr2=new ArrayList<>();
        Set<Long> hashset=new HashSet<>();
        long tmp=u;
        while(tmp!=0) {
            arr1.add(tmp);
            tmp/=2;
        }
        tmp=v;
        while(tmp!=0) {
            arr2.add(tmp);
            hashset.add(tmp);
            tmp/=2;
        }
        long end=-1;
        for(int i=0;i<arr1.size();i++) {
            if(hashset.contains(arr1.get(i))) {
                end=arr1.get(i);
                break;
            }
        }
        long ans=0;
        for(int i=0;i<arr1.size();i++) {
            if(arr1.get(i)==end) {
                break;
            }
            if(map.containsKey(arr1.get(i))) {
                ans+=map.get(arr1.get(i));
            }
        }
        for(int i=0;i<arr2.size();i++) {
            if(arr2.get(i)==end) {
                break;
            }
            if(map.containsKey(arr2.get(i))) {
                ans+=map.get(arr2.get(i));
            }
        }
        return ans;
    }
}
