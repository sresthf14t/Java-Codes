/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashCode_2020_Extended_Round;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Try_13 {
    static boolean Lib_is_taken[];
    static boolean book_is_taken[];
    public static void main(String args[]) throws IOException {
        
        //TAKING INPUT
        
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\HashCode\\c_incunabula.txt"));
        int Books=input.nextInt();
        int Lib=input.nextInt();
        int Days=input.nextInt();
        int b_score[]=new int[Books];
        int no_of_books_in_lib[]=new int[Lib];
        ArrayList<Integer>[] books_in_lib = new ArrayList[Lib];
        int no_of_days_sign[]=new int[Lib];
        int no_of_books_to_scan[]=new int[Lib];
        Lib_is_taken=new boolean[Lib];
        for(int i=0;i<Books;i++) {
            b_score[i]=input.nextInt();
        }
        for(int i=0;i<Lib;i++) {
            no_of_books_in_lib[i]=input.nextInt();
            no_of_days_sign[i]=input.nextInt();
            no_of_books_to_scan[i]=input.nextInt();
            books_in_lib[i]=new ArrayList<Integer>();
            for(int j=1;j<=no_of_books_in_lib[i];j++) { 
                books_in_lib[i].add(input.nextInt());
            }
        }
        
        
//        Arrays.sort(no_of_days_sign);
//        for(int i=0;i<Lib;i++) {
//            System.out.println(i+".)"+no_of_days_sign[i]);
//        }
//        System.exit(0);
        
        int arr_lib_to_take[]={10,35,141,320,323,350,435,662,731,924,929,978,987,1062,1070,1081,1167,1172,1333,1392,1436,1472,1595,1635,1760,1796,1875,1896,1904,2125,2205,2238,2306,2314,2388,2396,2402,2449,2509,2516,2648,2725,2743,2764,2820,2952,2969,2977,3062,3088,3114,3119,3238,3262,3268,3297,3368,3369,3516,3715,3754,3860,3872,3951,3956,3981,4107,4168,4190,4204,4228,4384,4408,4466,4479,4531,4578,4600,4700,4802,4918,4953,4978,5039,5124,5259,5271,5290,5388,5494,5509,5517,5519,5539,5546,5553,5563,5567,5602,5629,5659,5682,5791,5801,5817,5840,5847,5891,5921,5935,5940,6072,6138,6143,6147,6163,6197,6229,6305,6354,6369,6386,6388,6397,6411,6435,6527,6578,6580,6640,6752,6847,6855,6891,6988,7000,7011,7012,7059,7061,7076,7087,7144,7168,7181,7270,7334,7363,7397,7411,7487,7498,7616,7617,7621,7639,7665,7698,7706,7755,7773,7805,7875,7884,7934,7951,7987,8153,8193,8194,8246,8272,8311,8314,8315,8426,8466,8543,8657,8721,8876,8908,8959,8965,8977,9052,9148,9163,9241,9424,9482,9556,9597,9598,9681,9746,9752,9787,9815,9832,9846,9853,9868,9885};
        HashSet hash_set_lib_to_take=new HashSet<Integer>();
        for(int i=0;i<arr_lib_to_take.length;i++) {
            hash_set_lib_to_take.add(arr_lib_to_take[i]);
        }
        
        for(int i=0;i<Lib;i++) {
            if(hash_set_lib_to_take.contains(i)) {
                Lib_is_taken[i]=true;
            }
        }
        
        book_is_taken=new boolean[Books];
        
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("c_incunabula.txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        
        
        int count_lib=Lib,left_rng=0,right_rng=10,score=0;
        boolean once=false;
        while(Days>0 && count_lib>0) {
            
            int best_lib=find_best_Lib(Lib,Days,b_score , no_of_days_sign, no_of_books_to_scan, books_in_lib,no_of_books_in_lib);
//            System.out.println(best_lib);
            if(best_lib==-1) {
                break;
            }
            Days-=no_of_days_sign[best_lib];
            
            int days_rem=Days-no_of_days_sign[best_lib];
            int books_can_send=days_rem*no_of_books_to_scan[best_lib];
            int count=0;
            for(int j=0;j<no_of_books_in_lib[best_lib];j++) {
                if(!book_is_taken[books_in_lib[best_lib].get(j)]) {
                    count++;
                }
            }
            int books_rem_to_send[]=new int[count];
            for(int j=0,pos=0;j<no_of_books_in_lib[best_lib];j++) {
                if(!book_is_taken[books_in_lib[best_lib].get(j)]) {
                    books_rem_to_send[pos]=books_in_lib[best_lib].get(j);
                    
                    pos++;
                }
            }
            for(int j=0;j<count;j++) {
                int max_score=-1,max_score_indx=-1;
                for(int k=j;k<count;k++) {
                    if(b_score[books_rem_to_send[k]]>max_score) {
                        max_score=b_score[books_rem_to_send[k]];
                        max_score_indx=k;
                    }
                }
                int tmp=books_rem_to_send[max_score_indx];
                books_rem_to_send[max_score_indx]=books_rem_to_send[j];
                books_rem_to_send[j]=tmp;
            }
            
            count=0;
            StringBuilder books=new StringBuilder("");
            for(int j=0;j<books_rem_to_send.length && j<=books_can_send;j++) {
                count++;
//                System.out.print(books_rem_to_send[j]+" ");
                if(count==1) {
                    books.append(books_rem_to_send[j]);
                }
                else {
                    books.append(" "+books_rem_to_send[j]);
                }
                score+=b_score[books_rem_to_send[j]];
                book_is_taken[j]=true;
                
            }
            if(count>0) {
               System.out.println(best_lib+" "+count);
               System.out.println(books);
               outputstream.println(best_lib+" "+count);
               outputstream.println(books);
            }
            
            count_lib--;
        }
        System.out.println("\n"+(Lib-count_lib));
        System.out.println(Days+" "+score);
        outputstream.close();
    }
    public static int find_best_Lib(int Lib,int Days, int[] b_score ,int[] no_of_days_sign, int[] no_of_books_to_scan, ArrayList<Integer>[] books_in_lib,int[] no_of_books_in_lib) {
        int best_lib=-1,best_score=0;
        int min_days_sign_up=9999;
        
        for(int i=0;i<Lib;i++) {
            if(Lib_is_taken[i]) {
                continue;
            }
            if(no_of_days_sign[i]<min_days_sign_up) {
                min_days_sign_up=no_of_days_sign[i];
            }
        }
        
        for(int i=0;i<Lib;i++) {
            if(Lib_is_taken[i]) {
                continue;
            }
            int days_rem=Days-no_of_days_sign[i];
            int books_can_send=days_rem*no_of_books_to_scan[i];
            int count=0;
            for(int j=0;j<no_of_books_in_lib[i];j++) {
                if(!book_is_taken[books_in_lib[i].get(j)]) {
                    count++;
                }
            }
            int books_rem_to_send[]=new int[count];
            for(int j=0,pos=0;j<no_of_books_in_lib[i];j++) {
                if(!book_is_taken[books_in_lib[i].get(j)]) {
                    books_rem_to_send[pos]=books_in_lib[i].get(j);
                    pos++;
                }
            }
            for(int j=0;j<count;j++) {
                int max_score=-1,max_score_indx=-1;
                for(int k=j;k<count;k++) {
                    if(b_score[books_rem_to_send[k]]>max_score) {
                        max_score=b_score[books_rem_to_send[k]];
                        max_score_indx=k;
                    }
                }
                int tmp=books_rem_to_send[max_score_indx];
                books_rem_to_send[max_score_indx]=books_rem_to_send[j];
                books_rem_to_send[j]=tmp;
            }
            int tmp_score=0;
            for(int j=0;j<books_rem_to_send.length && j<=books_can_send;j++) {
                tmp_score+=b_score[books_rem_to_send[j]];
            }
            
            tmp_score=(tmp_score*min_days_sign_up)/no_of_days_sign[i];
            
            if(tmp_score>best_score) {
                best_score=tmp_score;
                best_lib=i;
            }
        }
        if(best_score==0) {
            return -1;
        }
        if(best_lib>=0) {
            Lib_is_taken[best_lib]=true;
        }
        return best_lib;
    }
}

