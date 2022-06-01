/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_C_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Perfect_Subarray {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            ans.append("Case #"+t+": ");
            ans.append(solve(n,arr));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int arr[]) {
        long cnt=0;
        int prefix[]=new int[n];
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
        }
        int pos_sum[]=new int[n];
        pos_sum[0]=Math.max(arr[0],0);
        for(int i=1;i<n;i++) {
            pos_sum[i]=Math.max(pos_sum[i-1],pos_sum[i-1]+arr[i]);
        }
        ArrayList<Integer> arrli=sq();
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<arrli.size();j++) {
                if(arrli.get(j)>pos_sum[i]) {
                    break;
                }
                if(prefix[i]==arrli.get(j)) {
                    cnt++;
                }
                int rem=prefix[i]-arrli.get(j);
                if(map.containsKey(rem)) {
                    cnt+=map.get(rem);
                }
            }
            if(!map.containsKey(prefix[i])) {
                map.put(prefix[i], 0);
            }
            map.replace(prefix[i], map.get(prefix[i])+1);
        }
        return cnt;
    }
    public static ArrayList<Integer> sq() {
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i*i<=10000000;i++) {
            arrli.add(i*i);
        }
        return arrli;
    }
}
