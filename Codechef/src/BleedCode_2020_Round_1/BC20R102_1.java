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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class BC20R102_1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            ArrayList<Integer> cars = new ArrayList<Integer>();
            ArrayList<Integer> times_moved = new ArrayList<Integer>();
            Set<Integer> cars_id = new HashSet<Integer>();
            char ad;
            int c_id;
            boolean is_full=false;
            for(int i=0;i<k;i++) {
                ad=input.next().charAt(0);
                c_id=input.nextInt();
                if(ad=='A') {
                    if(cars.size()!=n) {
                        System.out.println("AVAILABLE");
                        cars.add(c_id);
                        times_moved.add(0);
                        cars_id.add(c_id);
                    }
                    else {
                        System.out.println("NO ROOM");
                    }
                }
                else {
                    if(!cars_id.contains(c_id)) {
                        System.out.println("NOT FOUND");
                    }
                    else {
                        int indx=-1;
                        for(int j=0;j<cars.size();j++) {
                            if(cars.get(j)==c_id) {
                                indx=j;
                                break;
                            }
                            times_moved.set(j, times_moved.get(j)+1);
                        }
                        System.out.println(times_moved.get(indx));
                        times_moved.remove(indx);
                        cars.remove(indx);
                        cars_id.remove(c_id);
                    }
                }
            }
        }
    }
}
