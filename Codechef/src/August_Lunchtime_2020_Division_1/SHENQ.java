/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Lunchtime_2020_Division_1;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class SHENQ {
    static class Scan {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Scan() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0) {
                throw new InputMismatchException();
            }
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0) {
                    return -1;
                }
            }
            return buf[index++];
        }

        public int scanInt() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else {
                    throw new InputMismatchException();
                }
            }
            return neg * integer;
        }

        public double scanDouble() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n) && n != '.') {
                if (n >= '0' && n <= '9') {
                    doub *= 10;
                    doub += n - '0';
                    n = scan();
                } else {
                    throw new InputMismatchException();
                }
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else {
                        throw new InputMismatchException();
                    }
                }
            }
            return doub * neg;
        }

        public String scanString() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1) {
                return true;
            }
            return false;
        }
    }

    public static void main(String args[]) throws IOException {
        Scan input = new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            solve(n,arr);
        }
        System.out.println(ans);
    }
    public static void solve(int n,long arr[]) {
        if(n==1) {
            System.out.println(1+"\n"+arr[0]);
            return;
        }
        Stack<Long> stck=new Stack<>();
        for(int i=0;i<n;i++) {
            stck.push(arr[i]);
        }
        while(stck.size()>1) {
            long a=stck.pop();
            long b=stck.pop();
            if(a%2!=b%2) {
                if(stck.isEmpty()) {
                    System.out.println(2+"\n"+b+" "+a);
                    return;
                }
                long c=stck.pop();
                long d=add(b,c);
                d=add(a,d);
                stck.push(d);
            }
            else {
                stck.push(add(a,b));
            }
        }
        System.out.println(1+"\n"+stck.pop());
    }
    public static long add(long a,long b) {
        return a+b+(a%2==0?1:0);
    }
}
