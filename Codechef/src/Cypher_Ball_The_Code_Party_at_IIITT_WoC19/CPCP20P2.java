/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cypher_Ball_The_Code_Party_at_IIITT_WoC19;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CPCP20P2 {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        long strt=input.nextLong();
        long n=input.nextLong();
        long count=0;
        String tmp;
        boolean bool=true;
        for(long i=strt;i<=n;i+=1L) {
            tmp=""+i;
            bool=true;
            for(int j=0;j<tmp.length()-1;j++) {
                if(!(tmp.charAt(j)<=tmp.charAt(j+1))) {
                    bool=false;
                    break;
                }
            }
            if(bool) {
                count+=1L;
            }
        }
        System.out.println(count);
    }
}
