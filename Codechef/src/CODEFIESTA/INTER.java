/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODEFIESTA;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class INTER {
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
    static int n;
    static String str;
    static long mod,dp[];
    public static void main(String args[]) throws IOException {
        Scan input = new Scan();
        StringBuilder fin=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            str=input.scanString();
            n=str.length();
            mod=1000000007L;
            dp=new long[n];
            Arrays.fill(dp, -1);
            fin.append(solve(0)+"\n");
        }
        System.out.println(fin);
    }
    public static long solve(int indx) {
        if(indx==n) {
            return 1;
        }
        if(str.charAt(indx)=='0') {
            dp[indx]=0;
            return 0;
        }
        if(dp[indx]!=-1) {
            return dp[indx];
        }
        long ans=0;
        ans+=solve(indx+1);
        ans%=mod;
        if(indx!=n-1 && get(indx)<=26) {
            ans+=solve(indx+2);
        }
        ans%=mod;
        dp[indx]=ans;
        return ans;
    }
    public static int get(int indx) {
        int tmp=str.charAt(indx)-48;
        tmp*=10;
        tmp+=(str.charAt(indx+1)-48);
        return tmp;
    }
}
