/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codevita;

/**
 *
 * @author Srest
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
        
        int n=input.nextInt();
        String str[]=new String[n];
        for(int i=0;i<n;i++) {
            if(i==0) {
                String tmp=input.nextLine();
            }
            str[i]=input.nextLine();
//            System.out.println(i+" "+str[i]);
        }
        int t=input.nextInt();
        int in[]=new int[t];
        int out[]=new int[t];
        for(int i=0;i<t;i++) {
            in[i]=input.nextInt()-1;
            out[i]=input.nextInt()-1;
        }
        
        String name[]=new String[t];
        int pin[]=new int[t];
        int pout[]=new int[t];
        HashMap<String,Integer> map=new HashMap<>();
        
        
        for(int i=0,indx=0;i<n;i++) {
            boolean ii=true;
            String nn="";
            for(int j=0;j<str[i].length();j++) {
                if(str[i].charAt(j)!=' ') {
                    nn+=str[i].charAt(j);
                }
                if(nn.equals("-")) {
                    nn="";
                    continue;
                }
                if(str[i].charAt(j)==' ') {
                    if(nn.equals("-")) {
                        nn="";
                        continue;
                    }
                    if(nn.equals(" ")) {
                        nn="";
                        continue;
                    }
                    if(nn.isEmpty()) {
                        continue;
                    }
                    if(ii) {
//                        System.out.println(nn);
                        map.put(nn, indx);
                        name[indx]=""+nn;
                        nn="";
                        pin[indx]=i;
                        indx++;
                    }
                    else {
//                        System.out.println("/"+nn+"/");
                        pout[map.get(nn)]=i;
                        nn="";
                    }
                }
                else if(str[i].charAt(j)=='|') {
                    nn="";
                    ii=false;
                }
            }
            if(!nn.isEmpty()) {
                if(nn.equals(" ")) {
                    continue;
                }
                if(nn.equals("-")) {
                    continue;
                }
//                System.out.println("/"+nn+"/");
                pout[map.get(nn)]=i;
            }
        }
        
        for(int i=0;i<t;i++) {
            System.out.println(name[i]+" "+pin[i]+" "+pout[i]);
        }
        
        boolean ut[]=new boolean[t];
        boolean ot[]=new boolean[t];
        boolean visp[]=new boolean[t];
        boolean vist[]=new boolean[t];
        
        int corr_indx[]=new int[t];
        
        //normal
        for(int i=0;i<t;i++) {
            if(vist[i]) {
                continue;
            }
            for(int j=0;j<t;j++) {
                if(visp[j]) {
                    continue;
                }
                if(in[i]==pin[j] && out[i]==pout[j]) {
                    visp[j]=true;
                    vist[i]=true;
                    corr_indx[j]=i;
                    break;
                }
            }
        }
        
        //start
        for(int i=0;i<t;i++) {
            if(vist[i]) {
                continue;
            }
            for(int j=0;j<t;j++) {
                if(visp[j]) {
                    continue;
                }
                if(in[i]==pin[j]) {
                    visp[j]=true;
                    vist[i]=true;
                    
                    if(pout[j]<out[j]) {
                        ut[j]=true;
                    }
                    else {
                        ot[j]=true;
                    }
                    corr_indx[j]=i;
                    break;
                }
            }
        }
        
        //stop
        for(int i=0;i<t;i++) {
            if(vist[i]) {
                continue;
            }
            for(int j=0;j<t;j++) {
                if(visp[j]) {
                    continue;
                }
                if(out[i]==pout[j] && in[i]>=pin[j]) {
                    visp[j]=true;
                    vist[i]=true;
                    
                    if(pin[j]<in[j]) {
                        ot[j]=true;
                    }
                    corr_indx[j]=i;
                    break;
                }
            }
        }
        
        //rest
        for(int i=0;i<t;i++) {
            if(vist[i]) {
                continue;
            }
            for(int j=0;j<t;j++) {
                if(visp[j]) {
                    continue;
                }
                if(in[i]>pin[j] || out[i]<pout[j]) {
                    ot[j]=true;
                }
                else if(out[i]>pout[j]){
                    ut[j]=true;
                }
                visp[j]=true;
                vist[i]=true;
                corr_indx[j]=i;
                break;
            }
        }
        
        boolean taken[]=new boolean[t];
        
        for(int i=0;i<t;i++) {
            int indx=-1;
            for(int j=0;j<t;j++) {
                if(taken[j]) {
                    continue;
                }
                if(indx==-1) {
                    indx=j;
                    continue;
                }
                if(name[indx].compareTo(name[j])>0) {
                    indx=j;
                }
            }
            if(ut[indx]) {
                ans.append(name[indx]+" "+(in[corr_indx[indx]]+1)+" "+(out[corr_indx[indx]]+1)+" "+"UT\n");
            }
            if(ot[indx]) {
                ans.append(name[indx]+" "+(in[corr_indx[indx]]+1)+" "+(out[corr_indx[indx]]+1)+" "+"OT\n");
            }
            taken[indx]=true;
        }
        
        System.out.println(ans);
    }
    
    
}
