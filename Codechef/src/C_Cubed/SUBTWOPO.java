/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C_Cubed;

/**
 *
 * @author User
 */
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class SUBTWOPO {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        Set<Integer> hash_set = new HashSet<Integer>();
        for(int i=0;i<=30;i++) {
            hash_set.add((int)(Math.pow(2, i)));
        }
        int n=input.nextInt();
        boolean is_pos=false;
        for(int i=0;i<n;i++) {
            if(hash_set.contains(input.nextInt())) {
                is_pos=true;
            }
        }
        System.out.println(is_pos);
    }
}
