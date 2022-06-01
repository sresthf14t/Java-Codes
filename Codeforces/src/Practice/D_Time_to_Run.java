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
public class D_Time_to_Run {
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
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        if(n==1) {
            if(k>=m-1) {
                ans.append((m-1)+" "+"R\n");
                k-=m-1;
                cnt++;
            }
            else if(k>0) {
                ans.append(k+" "+"R\n");
                k-=k;
                cnt++;
            }
            
            if(k>=m-1) {
                ans.append((m-1)+" "+"L\n");
                k-=m-1;
                cnt++;
            }
            else if(k>0) {
                ans.append(k+" "+"L\n");
                k-=k;
                cnt++;
            }
            if(k!=0) {
                System.out.println("NO");
                return;
            }
            System.out.println("YES\n"+cnt+"\n"+ans);
            return;
        }
        if(m==1) {
            if(k>=n-1) {
                ans.append((n-1)+" "+"D\n");
                k-=n-1;
                cnt++;
            }
            else if(k>0) {
                ans.append(k+" "+"D\n");
                k-=k;
                cnt++;
            }
            
            if(k>=n-1) {
                ans.append((n-1)+" "+"U\n");
                k-=n-1;
                cnt++;
            }
            else if(k>0) {
                ans.append(k+" "+"U\n");
                k-=k;
                cnt++;
            }
            if(k!=0) {
                System.out.println("NO");
                return;
            }
            System.out.println("YES\n"+cnt+"\n"+ans);
            return;
        }
        
        for(int i=1;i<=m/2;i++) {
            if(i!=1) {
                if(k>=1) {
                    if(k>=1) {
                        ans.append(1+" "+"R\n");
                        k--;
                        cnt++;
                    }
                }
                else {
                    break;
                }
            }
            if(k>=n-1) {
                ans.append(1+" "+"D\n");
                k--;
                cnt++;
                int tmp_cnt=0;
                int j=1;
                boolean br=false;
                for(j=1;j<=n-2;j++) {
                    if(k<3) {
                        br=true;
                        break;
                    }
                    tmp_cnt++;
                    k-=3;
                }
                if(tmp_cnt>0) {
                    ans.append(tmp_cnt+" "+"RLD\n");
                    cnt++;
                }
                if(br && k>0) {
                    if(k==1) {
                        ans.append(1+" "+"R\n");
                        k--;
                        cnt++;
                    }
                    else if(k==2) {
                        ans.append(1+" "+"RL\n");
                        k-=2;
                        cnt++;
                    }
                }
            }
            else if(k!=0) {
                ans.append(k+" "+"D\n");
                k-=k;
                cnt++;
            }
            
            if(i!=1 && k>0) {
                ans.append(1+" "+"L\n");
                k--;
                cnt++;
            } 
            if(i!=1 && k>0) {
                ans.append(1+" "+"R\n");
                k--;
                cnt++;
            } 
            
            
            if(k==0) {
                break;
            }
            
            if(k>=1) {
                ans.append(1+" "+"R\n");
                k--;
                cnt++;
            }
            
            if(k==0) {
                break;
            }
            if(m%2==0 && i==m/2) {
                if(k>=n-1) {
                    cnt++;
                    k-=n-1;
                    ans.append((n-1)+" "+"U\n");
                }
                else if(k!=0){
                    ans.append(k+" "+"U\n");
                    k-=k;
                    cnt++;
                }
            }
            else {
                if(k>=n-1) {
                    ans.append(1+" "+"U\n");
                    k--;
                    cnt++;
                    int tmp_cnt=0;
                    int j=1;
                    boolean br=false;
                    for(j=1;j<=n-2;j++) {
                        if(k<3) {
                            br=true;
                            break;
                        }
                        tmp_cnt++;
                        k-=3;
                    }
                    if(tmp_cnt>0) {
                        ans.append(tmp_cnt+" "+"RLU\n");
                        cnt++;
                    }
                    if(br && k>0) {
                        if(k==1) {
                            ans.append(1+" "+"R\n");
                            k--;
                            cnt++;
                        }
                        else if(k==2) {
                            ans.append(1+" "+"RL\n");
                            k-=2;
                            cnt++;
                        }
                    }
                }
                else if(k!=0){
                    ans.append(k+" "+"U\n");
                    k-=k;
                    cnt++;
                }
            }
            
            if(k==0) {
                break;
            }
            
            if(k>=n-1) {
                ans.append((n-1)+" "+"D\n");
                k-=n-1;
                cnt++;
            }
            else if(k!=0){
                ans.append(k+" "+"D\n");
                k-=k;
                cnt++;
            }
            
            if(k==0) {
                break;
            }
            
            if(k>=1) {
                ans.append(1+" "+"L\n");
                k--;
                cnt++;
            }
            
            if(k==0) {
                break;
            }
            
            if(k>=n-1) {
                ans.append((n-1)+" "+"U\n");
                k-=n-1;
                cnt++;
            }
            else if(k!=0){
                ans.append(k+" "+"U\n");
                k-=k;
                cnt++;
            }
            
            if(k==0) {
                break;
            }
            
            if(k>=1) {
                ans.append(1+" "+"R\n");
                k--;
                cnt++;
            }
        }
        if(m%2==1) {
            if(k>=1) {
                ans.append(1+" "+"R\n");
                k--;
                cnt++;
            }
            if(k>=n-1) {
                ans.append((n-1)+" "+"D\n");
                k-=n-1;
                cnt++;
            }
            else if(k>0){
                ans.append(k+" "+"D\n");
                k-=k;
                cnt++;
            }
            
            if(k>=1) {
                ans.append(1+" "+"L\n");
                k--;
                cnt++;
            }
            
            if(k>=1) {
                ans.append(1+" "+"R\n");
                k--;
                cnt++;
            }
            
            if(k>=n-1) {
                ans.append((n-1)+" "+"U\n");
                k-=n-1;
                cnt++;
            }
            else if(k!=0) {
                ans.append(k+" "+"U\n");
                k-=k;
                cnt++;
            }
        }
        if(k>=m-1) {
            ans.append((m-1)+" "+"L\n");
            k-=m-1;
            cnt++;
        }
        else if(k!=0) {
            ans.append(k+" "+"L\n");
            k-=k;
            cnt++;
        }
        if(k!=0) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES\n"+cnt+"\n"+ans);
    }
}
