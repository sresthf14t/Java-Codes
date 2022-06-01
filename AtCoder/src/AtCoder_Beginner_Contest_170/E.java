/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_170;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E   {
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
        int q=input.scanInt();
        int power[]=new int[200001];
        int kinter[]=new int[200001];
        PriorityQueue<Integer> pque[] = new PriorityQueue[200001];
        for(int i=0;i<pque.length;i++) {
            pque[i] = new PriorityQueue<Integer>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer a, Integer b) {
                        return b - a; 
                    }
                }); 
        }
        for(int i=0;i<n;i++) {
            int pow=input.scanInt();
            int kct=input.scanInt()-1;
            power[i]=pow;
            kinter[i]=kct;
            pque[kct].add(pow);
        }
        PriorityQueue<Integer>  kct= new PriorityQueue<>();
        for(int i=0;i<pque.length;i++) {
            if(pque[i].isEmpty()) {
                continue;
            }
            kct.add(pque[i].peek());
        }
        HashMap<Integer,Integer> pque_rem[]=new HashMap[200001];
        for(int i=0;i<pque_rem.length;i++) {
            pque_rem[i]=new HashMap<>();
        }
        HashMap<Integer,Integer> kct_rem=new HashMap<>();
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<q;i++) {
            int child=input.scanInt()-1;
            int target=input.scanInt()-1;
            
            while(pque_rem[kinter[child]].containsKey(pque[kinter[child]].peek())) {
                pque_rem[kinter[child]].replace(pque[kinter[child]].peek(),pque_rem[kinter[child]].get(pque[kinter[child]].peek())-1);
                if(pque_rem[kinter[child]].get(pque[kinter[child]].peek())==0) {
                    pque_rem[kinter[child]].remove(pque_rem[kinter[child]]);
                }
                pque[kinter[child]].poll();
            }
            
            int src_ini=-1,src_fin=-1,tar_ini=-1,tar_fin=-1;
            if(!pque[kinter[child]].isEmpty()) {
                src_ini=pque[kinter[child]].peek();
            }
            if(!pque[target].isEmpty()) {
                tar_ini=pque[target].peek();
            }
            if( pque[kinter[child]].peek()==power[child]) {
                 pque[kinter[child]].poll();
            }
            else {
                if( pque_rem[kinter[child]].containsKey(power[child])) {
                    pque_rem[kinter[child]].replace(power[child],pque_rem[kinter[child]].get(power[child])-1);
                }
                pque_rem[kinter[child]].put(power[child],1);
            }
            
            pque[target].add(power[child]);
            
            
            if(!pque[kinter[child]].isEmpty()) {
                src_fin=pque[kinter[child]].peek();
            }
            if(!pque[target].isEmpty()) {
                tar_fin=pque[target].peek();
            }
//            System.out.println(src_ini+" "+src_fin+" "+tar_ini+" "+tar_fin);
            if(src_ini!=src_fin) {
                if(true || kct.contains(src_ini)) {
                    if(kct_rem.containsKey(src_ini)) {
                        kct_rem.replace(src_ini,kct_rem.get(src_ini)-1);
                    }
                    kct_rem.put(src_ini,1);
                }
                if(src_fin!=-1) {
                    kct.add(src_fin);
                }
                
            }
            if(tar_ini!=tar_fin) {
                if(true || kct.contains(tar_ini)) {
                    if(kct_rem.containsKey(tar_ini)) {
                        kct_rem.replace(tar_ini,kct_rem.get(tar_ini)-1);
                    }
                    kct_rem.put(tar_ini,1);
                }
                if(tar_fin!=-1) {
                    kct.add(tar_fin);
                }
                
            }
            kinter[child]=target;
            while(kct_rem.containsKey(kct.peek())) {
                kct_rem.replace(kct.peek(),kct_rem.get(kct.peek())-1);
                if(kct_rem.get(kct.peek())==0) {
                    kct_rem.remove(kct.peek());
                }
                kct.poll();
            }
            ans.append(kct.peek());
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
