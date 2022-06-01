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
import java.util.*; 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class BC20R102 {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            int cars[]=new int[n];
            Set<Integer> cars_id = new HashSet<Integer>();
            int times_moved[]=new int[n];
            char ad;
            int c_id,ptr=0;
            boolean is_full=false;
            for(int i=0;i<k;i++) {
                ad=input.next().charAt(0);
                c_id=input.nextInt();
                if(ad=='A') {
                    if(!is_full) {
                        System.out.println("AVAILABLE");
                        cars[ptr]=c_id;
                        cars_id.add(c_id);
                        while(ptr<n && cars[ptr]!=0) {
                            ptr++;
                        }
                        if(ptr==n) {
                            is_full=true;
                        }
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
                        is_full=false;
                        int indx=-1;
                        for(int j=0;j<n;j++) {
                            if(cars[j]==c_id) {
                                indx=j;
                                break;
                            }
                            times_moved[j]++;
                        }
                        System.out.println(times_moved[indx]);
                        times_moved[indx]=0;
                        cars[indx]=0;
                        if(ptr>indx) {
                            ptr=indx;
                        }
                        cars_id.remove(c_id);
                    }
                }
            }
        }
    }
}
