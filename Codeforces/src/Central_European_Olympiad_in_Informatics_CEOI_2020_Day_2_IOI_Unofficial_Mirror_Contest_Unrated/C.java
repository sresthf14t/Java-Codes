/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Central_European_Olympiad_in_Informatics_CEOI_2020_Day_2_IOI_Unofficial_Mirror_Contest_Unrated;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
        int r=input.scanInt();
        int c=input.scanInt();
        int q=input.scanInt();
        for(int t=1;t<=q;t++) {
            char type=input.scanString().charAt(0);
            int c1=input.scanInt();
            int c2=input.scanInt();
            if(type=='P') {
                ans.append(solvep(r,c1,c2));
            }
            if(type=='R') {
                ans.append(solver(r,c1,c2));
            }
            if(type=='Q') {
                ans.append(solveq(r,c1,c2,c));
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static StringBuilder solvep(int r,int c1,int c2) {
        if(c1!=c2) {
            return new StringBuilder(0+" "+0);
        }
        return new StringBuilder((r-1)+" "+1);
    }
    public static StringBuilder solver(int r,int c1,int c2) {
        if(c1!=c2) {
            return new StringBuilder(2+" "+2);
        }
        return new StringBuilder(1+" "+1);
    }
    public static StringBuilder solveq(int r,int c1,int c2,int c) {
        if(c1!=c2) {
            if(Math.abs(c1-c2)==r-1) {
                return new StringBuilder(1+" "+1);
            }
            return new StringBuilder(2+" "+(4+checkq(1,r,c1,c2,c)));
        }
        return new StringBuilder(1+" "+1);
    }
    public static int checkq(int r1,int r2,int c1,int c2,int c) {
        int ans=0;
        int tmp1=r1,tmp2=c1;
        for(int i=0;;i++) {
            tmp1++;
            tmp2++;
            if(tmp2>c) {
                break;
            }
            if(tmp1>=r2) {
                break;
            }
            if(Math.abs(tmp1-r2)==Math.abs(tmp2-c2)) {
                ans++;
                break;
            }
        }
        tmp1=r1;
        tmp2=c1;
        for(int i=0;;i++) {
            tmp1++;
            tmp2--;
            if(tmp2<0) {
                break;
            }
            if(tmp1>=r2) {
                break;
            }
            if(Math.abs(tmp1-r2)==Math.abs(tmp2-c2)) {
                ans++;
                break;
            }
        }
        return ans;
    }
}
