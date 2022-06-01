/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_82_Rated_for_Div2;

/**
 *
 * @author User
 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class D {

    static class Reader {

        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }

    public static void main(String args[]) throws IOException {
        Reader input = new Reader();
        int test = input.nextInt();
        for (int t = 0; t < test; t++) {
            long n = input.nextLong();
            int m = input.nextInt();
            long arr[] = new long[m];
            for (int i = 0; i < m; i++) {
                arr[i] = input.nextLong();
            }
            Arrays.sort(arr);
            StringBuilder str = new StringBuilder(Dec_to_Bin(n));
            StringBuilder is_found = new StringBuilder("");
            for (int i = 0, j = 0; i < str.length(); i++) {
                if (str.charAt(i) == '1') {
                    long tmp = (long) Math.pow(2, i);
                    boolean found = false;
                    for (; j < m; j++) {
                        if (arr[j] == tmp) {
                            arr[j] = 0;
                            is_found.append("1");
                            found = true;
                            break;
                        } else if (arr[j] > tmp) {
                            j--;
                            break;
                        }
                    }
                    if (!found) {
                        is_found.append("0");
                    }
                } else {
                    is_found.append("0");
                }
            }
            Arrays.sort(arr);
            int strt_indx = -1;
            for (int i = 0; i < m; i++) {
                if (arr[i] != 0) {
                    strt_indx = i;
                    break;
                }
            }
            System.out.println(str+" "+is_found);
            long count = 0L;
            boolean found = false;
            for (int i = 0, j = strt_indx; i < str.length(); i++) {
                if (str.charAt(i) == '1' && is_found.charAt(i) == '0') {
                    long tmp = (long) Math.pow(2, i);
                    found = false;
                    int tmp1 = j;
                    for (; j < m; j++) {
                        while (arr[i] > tmp) {
                            arr[i] /= 2;
                            count += 1L;
                            System.out.println(arr[i]+" "+tmp);
                            if (arr[i] == tmp) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        break;
                    } else {
                        j = tmp1;
                    }
                }
            }
            if (!found) {
                System.out.println(-1);
            } else {
                System.out.println(count);
            }
        }
    }

    public static String Dec_to_Bin(long m) {
        StringBuilder str = new StringBuilder("");
        while (m != 0) {
            str.append("" + m % 2);
            m /= 2;
        }
        String tmp = "" + str;
        if (tmp.equals("")) {
            str.append("0");
        }
        //str.reverse();
        return "" + str;
    }
}
