/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAT2Ques;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Main1111 {
    
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int a = input.nextInt(), b = input.nextInt(), c = input.nextInt();

        System.out.println(steps(a, b, c));
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public static int pour(int fromCap, int toCap, int d)  {
        int from = fromCap;
        int to = 0;
        int step = 1; 
        while (from != d && to != d)
        {
            int temp = Math.min(from, toCap - to);
            to += temp;
            from -= temp;
            step++;
            if (from == d || to == d)
                break;
            if (from == 0) {
                from = fromCap;
                step++;
            }
            if (to == toCap) {
                to = 0;
                step++;
            }
        }
        return step;
    }


    public static int steps(int m, int n, int d) {
        if (m > n) {
            int t = m;
            m = n;
            n = t;
        }
        if (d > n)
            return -1;
        if ((d % gcd(n, m)) != 0)
            return -1;
        return Math.min(pour(n, m, d),pour(m, n, d));
    }

}
