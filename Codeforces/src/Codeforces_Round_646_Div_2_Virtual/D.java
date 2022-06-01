/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_646_Div_2_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
    static Scanner input=new Scanner(System.in);
    static int fin_max=-1;
    public static void main(String args[]) throws IOException {
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            ArrayList<Integer> arr[]=new ArrayList[k];
            for(int i=0;i<k;i++) {
                arr[i]=new ArrayList<>();
                int size=input.nextInt();
                for(int j=0;j<size;j++) {
                    arr[i].add(input.nextInt());
                }
            }
            solve(n,k,arr);
            String res=input.next();
            if(res.equals("Correct")) {
                continue;
            }
            else {
                break;
            }
        }
    }
    public static void solve(int n,int k, ArrayList<Integer> arr[]) {
        int indx=find_max_indx(n);
        int max=fin_max;
        StringBuilder ans=new StringBuilder("! ");
        for(int i=0;i<k;i++) {
            if(arr[i].contains(new Integer(indx))) {
                System.out.print("? "+((n-arr[i].size())+" "));
                for(int j=1;j<=n;j++) {
                    if(arr[i].contains(j)) {
                        continue;
                    }
                    System.out.print(j+" ");
                }
                System.out.println();
                System.out.flush();
                ans.append(input.nextInt()+" ");
            }
            else {
                ans.append(max+" ");
            }
        }
        System.out.println(ans);
        System.out.println();
        System.out.flush();
    }
    public static int find_max_indx(int n) {
        int l=0,r=n-1,prev_max=-1;
        while(true) {
//            System.out.println(l+" "+r);
            if(l==r) {
                break;
            }
            int max1,max2;
            System.out.print("? "+((l+r)/2-l+1)+" ");
            for(int i=l;i<=(l+r)/2;i++) {
                System.out.print((i+1)+" ");
            }
            System.out.println();
            System.out.flush();
            max1=input.nextInt();
            if(prev_max!=-1) {
                if(max1==prev_max) {
                    r=(l+r)/2;
                    fin_max=prev_max;
                    continue;
                }
                else {
                    fin_max=prev_max;
                    l=((l+r)/2)+1;
                    continue;
                }
            }
            System.out.print("? "+(r-((l+r)/2)-1+1)+" ");
            for(int i=(l+r)/2+1;i<=r;i++) {
                System.out.print((i+1)+" ");
            }
            System.out.println();
            System.out.flush();
            max2=input.nextInt();
            if(max1>max2) {
                fin_max=max1;
                prev_max=max1;
                r=(l+r)/2;
            }
            else {
                fin_max=max2;
                prev_max=max2;
                l=((l+r)/2)+1;
            }
        }
        return l+1;
    }
}
