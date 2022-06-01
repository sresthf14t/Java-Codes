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
public class C_Adding_Powers {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            ArrayList<Long> pow_lis=new ArrayList<>();
            long pow=1;
            for(int i=0;pow>0;i++) {
                pow_lis.add(pow);
                pow*=k;
            }
            boolean is_pos=true;
            for(int i=0;i<n;i++) {
                if(solve(input.nextLong(),pow_lis)!=0) {
                    is_pos=false;
                } 
            }
            if(is_pos) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    public static long solve(long n,ArrayList<Long> pow_lis) {
        for(int i=pow_lis.size()-1;i>=0;i--) {
            if(pow_lis.get(i)<=n) {
                n-=pow_lis.get(i);
                pow_lis.remove(i);
            }
        }
        return n;
    }
}
