/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_B_2019;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Building_Palindromes {
    public static void main(String aths[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int q=input.nextInt();
            String str=input.next();
            int count=0;
            int alpha[][]=new int[n][26];
            int count_alpha[]=new int[26];
            for(int i=0;i<n;i++) {
                count_alpha[str.charAt(i)-65]++;
                for(int j=0;j<26;j++) {
                    alpha[i][j]=count_alpha[j];
                }
            }
            for(int i=0;i<q;i++) {
                int l=input.nextInt();
                int r=input.nextInt();
                l--;
                r--;
                int odd=0;
                for(int j=0;j<26;j++) {
                    if(l>0 && (alpha[r][j]-alpha[l-1][j])%2!=0) {
                        odd++;
                    }
                    else if(l==0 && alpha[r][j]%2!=0) {
                        odd++;
                    }
                }
                if(odd==0 || odd==1) {
                    count++;
                }
            }
            System.out.println("Case #"+t+": "+count);
        }
    }
}
