/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_739_Div_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class F2 {
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
    
    static TreeSet<Integer> ts1,ts2;
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        ts1=new TreeSet<>();
        ts2=new TreeSet<>();
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                per(i,j,0);
            }
        }
        
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int k=input.scanInt();
            
//            int n=(int)(Math.random()*100000);
//            int k=3;
            
            if(k==1) {
                if(ts1.contains(n)) {
                    ans.append(n+"\n");
                    continue;
                }
                ans.append(ts1.higher(n)+"\n");
                continue;
            }
            if(k==2) {
                if(ts2.contains(n)) {
                    ans.append(n+"\n");
                    continue;
                }
                ans.append(ts2.higher(n)+"\n");
                continue;
            }
            
//            if(!(""+solve(n,k)).equals(""+check(n,k))) {
//                System.out.println(n+" "+k+" "+solve(n,k)+" "+check(n,k));
//            }
            
            ans.append(solve(n,k)+"\n");
        }
        System.out.println(ans);
    }
    
    public static StringBuilder solve(int n,int k) {
        StringBuilder val=new StringBuilder("");
        String num=""+n;
        boolean dig[]=new boolean[10];
        int indx=0,dig_cnt=0;
        int gr[]=new int[num.length()];
        boolean fst[]=new boolean[num.length()];
        Arrays.fill(gr, -1);
        while(dig_cnt<=k && indx<num.length()) {
            
            
            if(dig[num.charAt(indx)-'0']) {
                gr[indx]=0;
                val.append(num.charAt(indx));
                indx++;
                continue;
            }
            if(dig_cnt==k) {
                break;
            }
            fst[indx]=true;
            gr[indx]=0;
            val.append(num.charAt(indx));
            dig[num.charAt(indx)-'0']=true;
            dig_cnt++;
            indx++;
        }
        if(indx==num.length()) {
            return val;
        }
        
        for(int i=indx;i<num.length();i++) {
            for(int j=0;j<dig.length;j++) {
                if(dig[j] && j==num.charAt(i)-'0') {
                    gr[i]=0;
                }
                if(dig[j] && j>num.charAt(i)-'0') {
                    gr[i]=1;
                } 
            }
        }
        
        int lst_indx=indx-1;
        
        for(int i=indx;i<num.length();i++) {
            if(gr[i]==-1) {
                break;
            }
            if(gr[i]==1) {
                lst_indx=i;
            }
        }
        
        if(lst_indx>indx-1) {
            boolean gre=false;
            for(int i=indx;i<lst_indx;i++) {
                for(int j=0;j<dig.length;j++) {
                    if(dig[j] && j==num.charAt(i)-'0') {
                        val.append(j);
                        break;
                    }
                    if(dig[j] && j>num.charAt(i)-'0') {
                        val.append(j);
                        gre=true;
                        break;
                    }
                }
                if(gre) {
                    break;
                }
            }
            
            if(gre) {
                while(val.length()<num.length()) {
                    for(int j=0;j<dig.length;j++) {
                        if(dig[j]) {
                            val.append(j);
                            break;
                        }
                    }
                }
            }
            else {
                for(int j=0;j<dig.length;j++) {
                    if(dig[j] && j>num.charAt(lst_indx)-'0') {
                        val.append(j);
                        break;
                    }
                }
                while(val.length()<num.length()) {
                    for(int j=0;j<dig.length;j++) {
                        if(dig[j]) {
                            val.append(j);
                            break;
                        }
                    }
                }
            }
            
            
            return val;
        }
        
        
        for(int i=val.length()-1;i>=0;i--) {
            if(!fst[i]) {
                boolean br=false;
                for(int j=0;j<dig.length;j++) {
                    if(dig[j] && j>(val.charAt(val.length()-1))-'0') {
                        br=true;
                        break;
                    }
                }
                if(br) {
                    break;
                }
            }

            if(!fst[i]) {
                val.deleteCharAt(val.length()-1);
            }
            else {
                break;
            }
        }
        
        if(!fst[val.length()-1]) {
            for(int j=0;j<dig.length;j++) {
                if(dig[j] && j>(val.charAt(val.length()-1))-'0') {
                    val.replace(val.length()-1, val.length(), ""+j);
                    break;
                }
            }
        }
        else {
            val.replace(val.length()-1, val.length(), ""+(char)(val.charAt(val.length()-1)+1));
        }
        
        
        dig=new boolean[10];
        dig_cnt=0;
        
        indx=0;
        while(indx<val.length()) {
            if(dig[val.charAt(indx)-'0']) {
                indx++;
                continue;
            }
            dig[val.charAt(indx)-'0']=true;
            dig_cnt++;
            indx++;
        }
        
        if(dig_cnt<k) {
            dig[0]=true;
        }
        
        while(val.length()<num.length()) {
            for(int j=0;j<dig.length;j++) {
                if(dig[j]) {
                    val.append(j);
                    break;
                }
            }
        }
        
        return val;
    }
    
    
    public static void per(int a,int b,long val) {
        if(val>Integer.MAX_VALUE) {
            return;
        }
        if(val!=0) {
            if(a==b) {
                ts1.add((int)val);
            }
            ts2.add((int)val);
        }
        if(a!=0 || val!=0) {
            per(a,b,(val*10)+a);
        }
        if(b!=0 || val!=0) {
            per(a,b,(val*10)+b);
        }
    }
    
    public static int check(int n,int k) {
        for(int i=n;i<=100000000;i++) {
            if(distinct(i)<=k) {
                return i;
            }
        }
        return -1;
    }
    
    public static int distinct(int n) {
        boolean dig[]=new boolean[10];
        while(n!=0) {
            dig[n%10]=true;
            n/=10;
        }
        int cnt=0;
        for(int i=0;i<dig.length;i++) {
            if(dig[i]) {
                cnt++;
            }
        }
        return cnt;
    }
    
}
