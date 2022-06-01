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
public class Try_5 {
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
        
        int count_arr[]=new int[10];
//        Arrays.sort(no_of_days_sign);
        for(int i=0;i<Lib;i++) {
//            System.out.println(i+".)"+no_of_days_sign[i]);
            count_arr[no_of_days_sign[i]-1]++;
        }
        
//        for(int i=0;i<Lib;i++) {
//            if(no_of_days_sign[i]>Days/18) {
//                Lib_is_taken[i]=true;
//            }
//        }
        
        boolean book_is_taken[]=new boolean[Books];
        
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("e_so_many_books.txt",true));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        
        
        int count_lib=Lib,count_arr_counter=0;
        while(Days>0 && count_lib>0) {
            int no_of_days_sign_const=count_arr_counter+1;
            
            int best_lib=find_best_Lib(Lib,Days,b_score , no_of_days_sign, no_of_books_to_scan, books_in_lib, book_is_taken,no_of_books_in_lib,no_of_days_sign_const);
//            System.out.println(best_lib);
            if(best_lib==-1) {
                break;
            }
            
            count_arr[no_of_days_sign[best_lib]]--;
            if(count_arr[count_arr_counter]==0) {
                count_arr_counter++;
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
            for(int j=0;j<books_rem_to_send.length && j<=books_can_send;j++) {
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
    }
    public static int find_best_Lib(int Lib,int Days, int[] b_score ,int[] no_of_days_sign, int[] no_of_books_to_scan, ArrayList<Integer>[] books_in_lib, boolean[] book_is_taken,int[] no_of_books_in_lib, int no_of_days_sign_const) {
        int best_lib=-1,best_score=0;
        for(int i=0;i<Lib;i++) {
            if(Lib_is_taken[i]) {
                continue;
            }
            if(no_of_days_sign_const==no_of_days_sign[i] || no_of_days_sign_const+1==no_of_days_sign[i]) {
                
            }
            else {
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
