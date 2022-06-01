/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;
public class C_Searching_for_Graph { 
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int p=input.nextInt();
            int count=2*n+p;
            Set<Integer> hash_set[]=new HashSet[n+1];
            for(int i=1;i<=n;i++) {
                hash_set[i]=new HashSet<>();
                hash_set[i].add(i);
            }
            StringBuilder ans=new StringBuilder("");
            for(int i=1;i<n;i++) {
                count--;
                hash_set[i].add(i+1);
                hash_set[i+1].add(i);
                ans.append(i+" "+(i+1)+"\n");
            }
            if(!hash_set[1].contains(n)) {
                count--;
                hash_set[1].add(n);
                hash_set[n].add(1);
                ans.append(1+" "+n+"\n");
            }
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    if(hash_set[i].contains(j)) {
                        continue;
                    }
                    ans.append(i+" "+j+"\n");
                    hash_set[i].add(j);
                    hash_set[j].add(i);
                    count--;
                    if(count==0) {
                        break;
                    }
                }
                if(count==0) {
                    break;
                }
            }
            System.out.println(ans);
        }
    }
}
