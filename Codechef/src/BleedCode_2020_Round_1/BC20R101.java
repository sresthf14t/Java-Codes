/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BleedCode_2020_Round_1;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Scanner;
public class BC20R101 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            boolean is_inc=true;
            for(int i=0;i<n;i++) {
                arr.add(input.nextInt());    
            }
            for(int i=0;i<n;i++) {
                int indx=i,ele=arr.get(i);
                arr.remove(i);
                is_inc=true;
                for(int j=1;j<arr.size();j++) {
                    if(arr.get(j-1)<arr.get(j));
                    else {
                        is_inc=false;
                        break;
                    }
                }
                if(is_inc) {
                    break;
                }
                arr.add(indx, ele);
            }
            if(is_inc) {
                System.out.println("TRUE");
            }
            else {
                System.out.println("FALSE");
            }
        }
    }
}
