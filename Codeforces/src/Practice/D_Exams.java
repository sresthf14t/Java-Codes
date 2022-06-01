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
public class D_Exams {
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
    static int seg_tree[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        seg_tree=new int[4*n];
        int days[]=new int[n];
        int time[]=new int[m+1];
        for(int i=0;i<n;i++) {
            days[i]=input.scanInt();
        }
        for(int i=1;i<m+1;i++) {
            time[i]=input.scanInt();
        }
        solve(n,m,days,time);
    }
    public static void solve(int n,int m,int work[],int time[]) {
        int study=0;
        for(int i=0;i<m;i++) {
            study+=time[i];
        }
        if(study+m>n) {
            System.out.println(-1);
            return;
        }
        ArrayList<Integer> arrli[]=new ArrayList[n+1];
        for(int i=0;i<n;i++) {
            if(arrli[work[i]]==null) {
                arrli[work[i]]=new ArrayList<>();
            }
            arrli[work[i]].add(i);
        }
        boolean xams[]=new boolean[m+1];
        for(int i=study;i<n;i++) {
            if(work[i]==0) {
                continue;
            }
            xams[work[i]]=true;
        }
        int days_need[]=new int[n];
        for(int i=study-1;i>=0;i--) {
            if(xams[work[i]] || work[i]==0) {
                continue;
            }
            if(sum(0,0,n-1,0,i-1)+time[i]<=i-1) {
                update(0,0,n-1,i,time[i]+1);
                days_need[i]=time[i]+1;
                xams[work[i]]=true;
            }
        }
        for(int i=1;i<m+1;i++) {
            if(!xams[i]) {
                System.out.println(-1);
                return;
            }
        }
        xams=new boolean[m+1];
        Arrays.fill(xams, true);
        for(int i=study;i<n;i++) {
            xams[work[i]]=false;
        }
        for(int i=1;i<m+1;i++) {
            if(xams[i]) {
                continue;
            }
            System.out.println(i);
            for(int j=0;j<arrli[i].size();j++) {
                System.out.println("\t"+arrli[i].get(j));
                int indx=arrli[i].get(j);
                System.out.println("\t\t"+sum(0,0,n-1,0,indx-1));
                if(sum(0,0,n-1,0,indx-1)+time[i]<=indx) {
                    days_need[indx]=time[i]+1;
                    update(0,0,n-1,indx,time[i]+1);
                    xams[i]=true;
                    break;
                }
            }
        }
        for(int i=1;i<m+1;i++) {
            if(!xams[i]) {
                System.out.println(-1);
                return;
            }
        }
        int max=-1;
        for(int i=0;i<n;i++) {
            System.out.println(i+" "+days_need[i]);
            if(days_need[i]>0) {
                max=i;
            }
        }
        System.out.println(max+1);
    }
    
    public static int sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
        if(ql>qr) {
            return 0;
        }
        
        if(ql==l && qr==r) {
            return seg_tree[vertex];
        }
        int mid=(l+r)/2;
        
        int total=0;
        
        //Left Child
        total+=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
        
        //Right Child
        total+=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
        
        return total;
    }
    
    public static void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
        if(l==r) {
            seg_tree[vertex]=value;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        if(pos<=mid) {
            update((2*vertex)+1,l,mid,pos,value);
        }
        //Right Child
        else {
            update((2*vertex)+2,mid+1,r,pos,value);
        }
        seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
    }
    
}
