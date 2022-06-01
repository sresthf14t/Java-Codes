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
public class PPDIV {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        ArrayList<Long> lis=new ArrayList<>();
        perf_pow_lis(lis);
        long lis_arr[]=new long[lis.size()];
        for(int i=0;i<lis.size();i++) {
            lis_arr[i]=lis.get(i);
        }
        Arrays.sort(lis_arr);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long n=input.nextLong();
            System.out.println(count_arr_fill(lis_arr, n));
        }
    }
    public static void perf_pow_lis(ArrayList<Long> lis) {
        Set<Long> hash_set=new HashSet<>();
        lis.add(1L);
        long lim=(long)Math.pow(10, 12);
        for(long i=2;i<=1000000;i++) {
            long pow=i;
            for(long j=1;;j++) {
                pow*=i;
                if(pow>lim) {
                    break;
                }
                if(!hash_set.contains(pow)) {
                    lis.add(pow);
                    hash_set.add(pow);
                }
            }
        }
    }
    public static long count_arr_fill(long[] arr_lis, long lim) {
        long sum=0,mod=1000000007L;
        for(int i=0;i<arr_lis.length;i++) {
            long tmp=lim/arr_lis[i];
            sum+=(tmp*arr_lis[i]);
            sum%=mod;
        }
        return sum;
    }
}

