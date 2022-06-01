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
public class Game_on_a_Strip {
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
            int n = input.scanInt();
            int arr[] = new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            int max=0,cnt=0,max_cnt=0,sec_max=0;
            for(int i=0;i<n;i++) {
                if(arr[i]==1) {
                    continue;
                }
                int j=i;
                while(j<n && arr[j]==0) {
                    cnt++;
                    j++;
                }
                j--;
                if(cnt>max) {
//                    System.out.println(i+" "+max+" "+sec_max);
                    sec_max=max;
                    max_cnt=1;
                    max=cnt;
                }
                else if(cnt==max) {
                    sec_max=max;
                    max_cnt++;
                }
                else if(cnt<max && cnt>sec_max) {
                    sec_max=cnt;
                }
//                System.out.println(i+" "+cnt+" "+max);
                i=j;
                cnt=0;
            }
//            System.out.println(max+" "+sec_max);
            if(max_cnt>1) {
                ans.append("No\n");
            }
            else if(max%2==0) {
                ans.append("No\n");
            }
            else if(sec_max==0){
                ans.append("Yes\n");
            }
            else if(sec_max<=max/2) {
                ans.append("Yes\n");
            }
            else {
                ans.append("No\n");
            }
        }
        System.out.println(ans);
    }
}
