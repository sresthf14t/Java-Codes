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
public class Try_7 {
    static boolean Lib_is_taken[];
    static boolean book_is_taken[];
    public static void main(String args[]) throws IOException {
        
        //TAKING INPUT
        
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\HashCode\\e_so_many_books.txt"));
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
        
        int Lib_not_to_take_arr[]={0,7,29,31,32,56,66,71,78,80,81,83,85,88,91,112,118,121,123,124,128,135,140,151,155,163,164,174,175,184,186,191,200,202,212,217,222,231,233,242,245,247,252,258,281,287,292,316,318,323,328,332,334,375,409,416,428,433,437,443,447,472,473,494,500,509,516,521,525,531,536,543,565,566,569,571,575,589,596,617,622,631,632,634,640,642,646,650,652,653,676,679,685,687,709,710,716,743,760,764,767,772,773,793,795,798,805,820,823,837,844,853,878,904,908,920,925,936,944,949,958,971,977,980,985,998};
        Set<Integer> hash_set_Lib_not_to_take_arr = new HashSet<Integer>();
        for(int i=0;i<Lib_not_to_take_arr.length;i++) {
            hash_set_Lib_not_to_take_arr.add(Lib_not_to_take_arr[i]);
        }
        for(int i=0;i<Lib;i++) {
            if(!hash_set_Lib_not_to_take_arr.contains(i)) {
                Lib_is_taken[i]=true;
            }
        }
        
        boolean book_is_taken[]=new boolean[Books];
        
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("e_so_many_books.txt",true));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        
        
        int count_lib=Lib;
        boolean done=false;
        while(Days>0 && count_lib>0) {
            
            int best_lib=find_best_Lib(Lib,Days,b_score , no_of_days_sign, no_of_books_to_scan, books_in_lib, book_is_taken,no_of_books_in_lib);
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
                    book_is_taken[books_in_lib[best_lib].get(j)]=true;
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
            for(int j=0;j<books_rem_to_send.length && j<books_can_send;j++) {
                count++;
//                System.out.print(books_rem_to_send[j]+" ");
                if(count==1) {
                    books.append(books_rem_to_send[j]);
                }
                else {
                    books.append(" "+books_rem_to_send[j]);
                }
                
            }
            if(count>0) {
//               System.out.println(best_lib+" "+count);
//               System.out.println(books);
               outputstream.println(best_lib+" "+count);
               outputstream.println(books);
            }
            
            count_lib--;
        }
        System.out.println("\n"+(Lib-count_lib));
        outputstream.close();
        System.out.println(Days);
        int counter=0;
        for(int i=0;i<Lib;i++) {
            if(Lib_is_taken[i]) {
                counter++;
            }
        }
        System.out.println(counter);
    }
    public static int find_best_Lib(int Lib,int Days, int[] b_score ,int[] no_of_days_sign, int[] no_of_books_to_scan, ArrayList<Integer>[] books_in_lib, boolean[] book_is_taken,int[] no_of_books_in_lib) {
        int best_lib=-1,best_score=0;
        
        
        for(int i=0;i<Lib;i++) {
            if(Lib_is_taken[i]) {
                continue;
            }
        }
        int min_no_of_days_sign=999;
        for(int i=0;i<Lib;i++) {
            if(no_of_days_sign[i]<min_no_of_days_sign) {
                min_no_of_days_sign=no_of_days_sign[i];
            }
        }
        for(int i=0;i<Lib;i++) {
            if(no_of_days_sign[i]!=min_no_of_days_sign) {
                continue;
            }
            int tmp_score=0;
            for(int j=0;j<no_of_books_in_lib[i];j++) {
                if(book_is_taken[books_in_lib[i].get(j)]) {
                    continue;
                }
                tmp_score+=b_score[books_in_lib[i].get(j)];
            }
            if(tmp_score>best_score) {
                best_lib=i;
                best_score=tmp_score;
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

