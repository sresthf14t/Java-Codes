/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class REBIT {
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
        StringBuilder str=new StringBuilder(input.scanString());
        StringBuilder post=in_to_pos(str);
        //a=0
        Stack<Integer> num_1=new Stack<>();
        Stack<Integer> den_1=new Stack<>();
        Stack<Integer> num_0=new Stack<>();
        Stack<Integer> den_0=new Stack<>();
        for(int i=0;i<post.length();i++) {
            if(post.charAt(i)=='#') {
                num_1.push(-1);
                den_1.push(-1);
                num_0.push(-1);
                den_0.push(-1);
            }
            else {
                int a_num_1=num_1.pop(),a_den_1=den_1.pop();
                int b_num_1=num_1.pop(),b_den_1=den_1.pop();
                int a_num_0=num_0.pop(),a_den_0=den_0.pop();
                int b_num_0=num_0.pop(),b_den_0=den_0.pop();
                if(a_num_1==-1 && b_num_1==-1) {
                    int arr[]=prob(0,post.charAt(i));
                    num_1.push(arr[1]);
                    den_1.push(arr[0]+arr[1]);
                    num_0.push(arr[0]);
                    den_0.push(arr[0]+arr[1]);
                }
                else if(b_num_1==-1) {
                    num_1.push(a_num_1);
                    den_1.push(a_den_1*2);
                    num_0.push(a_num_0);
                    den_0.push(a_den_0*2);
                }
                else {
                    num_1.push(a_num_1*b_num_1);
                    den_1.push(a_den_1*b_den_1);
                    num_0.push(a_num_0*b_num_0);
                    den_0.push(a_den_0*b_den_0);
                }
            }
        }
        
        int a_0_prob_0_num=num_0.pop();
        int a_0_prob_0_den=den_0.pop();
        int a_0_prob_1_num=num_1.pop();
        int a_0_prob_1_den=den_1.pop();
        
        
        //a=1
        num_1=new Stack<>();
        den_1=new Stack<>();
        num_0=new Stack<>();
        den_0=new Stack<>();
        for(int i=0;i<post.length();i++) {
            if(post.charAt(i)=='#') {
                num_1.push(-1);
                den_1.push(-1);
                num_0.push(-1);
                den_0.push(-1);
            }
            else {
                int a_num_1=num_1.pop(),a_den_1=den_1.pop();
                int b_num_1=num_1.pop(),b_den_1=den_1.pop();
                int a_num_0=num_0.pop(),a_den_0=den_0.pop();
                int b_num_0=num_0.pop(),b_den_0=den_0.pop();
                if(a_num_1==-1 && b_num_1==-1) {
                    int arr[]=prob(1,post.charAt(i));
                    num_1.push(arr[1]);
                    den_1.push(arr[0]+arr[1]);
                    num_0.push(arr[0]);
                    den_0.push(arr[0]+arr[1]);
                }
                else if(b_num_1==-1) {
                    num_1.push(a_num_1);
                    den_1.push(a_den_1*2);
                    num_0.push(a_num_0);
                    den_0.push(a_den_0*2);
                }
                else {
                    num_1.push(a_num_1*b_num_1);
                    den_1.push(a_den_1*b_den_1);
                    num_0.push(a_num_0*b_num_0);
                    den_0.push(a_den_0*b_den_0);
                }
            }
        }
        
        int a_1_prob_0_num=num_0.pop();
        int a_1_prob_0_den=den_0.pop();
        int a_1_prob_1_num=num_1.pop();
        int a_1_prob_1_den=den_1.pop();
        
        System.out.println("a_0_prob_0_num="+a_0_prob_0_num);
        System.out.println("a_0_prob_0_den="+a_0_prob_0_den);
        System.out.println("a_0_prob_1_num="+a_0_prob_1_num);
        System.out.println("a_0_prob_1_den="+a_0_prob_1_den);
        System.out.println("a_1_prob_0_num="+a_1_prob_0_num);
        System.out.println("a_1_prob_0_den="+a_1_prob_0_den);
        System.out.println("a_1_prob_1_num="+a_1_prob_1_num);
        System.out.println("a_1_prob_1_den="+a_1_prob_1_den);
        
        long first_num=a_0_prob_0_num*a_1_prob_0_num;
        long first_den=a_0_prob_0_den*a_1_prob_0_den;
        long sec_num=a_0_prob_1_num*a_1_prob_1_num;
        long sec_den=a_0_prob_1_den*a_1_prob_1_den;
        long third_num=a_0_prob_0_num*a_1_prob_1_num;
        long third_den=a_0_prob_0_den*a_1_prob_1_den;
        long four_num=a_0_prob_1_num*a_1_prob_0_num;
        long four_den=a_0_prob_1_den*a_1_prob_0_den;
        
        long mod=998244353L;
        System.out.println((first_num*Inverse(first_den,mod))%mod);
        System.out.println((sec_num*Inverse(sec_den,mod))%mod);
        System.out.println((third_num*Inverse(third_den,mod))%mod);
        System.out.println((four_num*Inverse(four_den,mod))%mod);
    }
    
    public static int[] prob(int a_val,char opr) {
        int a=a_val;
        int A=1-a_val;
        int arr[]={0,1,a,A};
        int zero=0,one=0;
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                int tmp=-1;
                if(opr=='&') {
                    tmp=arr[i]&arr[j];
                }
                if(opr=='^') {
                    tmp=arr[i]^arr[j];
                }
                if(opr=='|') {
                    tmp=arr[i]|arr[j];
                }
                if(tmp==0) {
                    zero++;
                }
                else {
                    one++;
                }
            }
        }
        return new int[]{zero,one};
    }
    
    public static StringBuilder in_to_pos(StringBuilder str) {
        StringBuilder ans=new StringBuilder(""); 
        Stack<Character> stck=new Stack<>();
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='#') {
                ans.append(str.charAt(i));
            }
            else if(str.charAt(i)=='&' || str.charAt(i)=='|' || str.charAt(i)=='^') {
                if(stck.isEmpty() || compare_greater_than(str.charAt(i), stck.peek())) {
                    stck.push(str.charAt(i));
                }
                else {
                    while(!compare_greater_than(str.charAt(i), stck.peek())) {
                        ans.append(stck.pop());
                    }
                    stck.push(str.charAt(i));
                }
            }
            else if(str.charAt(i)=='(') {
                stck.push(str.charAt(i));
            }
            else if(str.charAt(i)==')') {
                while(stck.peek()!='(') {
                    ans.append(stck.pop());
                }
                stck.pop();
            }
        }
        while(!stck.isEmpty()) {
            ans.append(stck.pop());
        }
        return ans;
    }
    public static boolean compare_greater_than(char c1, char c2) {
        int a1=-1,a2=-1;
        if(c1=='&') {
            a1=3;
        }
        if(c1=='^') {
            a1=2;
        }
        if(c1=='|') {
            a1=1;
        }
        if(c2=='&') {
            a1=3;
        }
        if(c2=='^') {
            a1=2;
        }
        if(c2=='|') {
            a1=1;
        }
        if(a1>=a2) {
            return true;
        }
        return false;
    }
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
}
