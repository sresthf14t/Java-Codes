/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashCode_2020;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.*;
public class Try_2 {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\HashCode\\d_tough_choices.txt"));
        int Books=input.nextInt();
        int Lib=input.nextInt();
        int Days=input.nextInt();
        int b_score[]=new int[Books];
        int no_of_books_in_lib[][]=new int[Books+1][Lib];
        int no_of_days_sign[]=new int[Lib];
        int no_of_books_to_scan[]=new int[Lib];
        for(int i=0;i<Books;i++) {
            b_score[i]=input.nextInt();
        }
        for(int i=0;i<Lib;i++) {
            no_of_books_in_lib[0][i]=input.nextInt();
            no_of_days_sign[i]=input.nextInt();
            no_of_books_to_scan[i]=input.nextInt();
            for(int j=1;j<=no_of_books_in_lib[0][i];j++) {
                no_of_books_in_lib[j][i]=input.nextInt();
            }
        }
//        int sort_arr[][]=new int[4000][100000];
//        for(int i=0;i<Lib;i++) {
//            int tmp=no_of_days_sign[i];
//            sort_arr[0][tmp]++;
//            int pos=sort_arr[0][tmp];
//            sort_arr[pos][tmp]=i;
//        }
//        for(int i=0;i<100;i++) {
//            for(int j=0;j<100;j++) {
//                System.out.print(sort_arr[i][j]+" ");
//            }
//            System.out.println();
//        }
        
        
        int no_of_days_sign_sorted[]=new int[Lib];
        
        for(int i=0;i<Lib;i++) {
            no_of_days_sign_sorted[i]=find_min_indx(no_of_days_sign);
        }
        
        
//        for(int i=0,pos=0;i<100;i++) {
//            int len=sort_arr[0][i];
//            for(int j=1;j<=len;j++) {
//                no_of_days_sign_sorted[pos]=sort_arr[j][i];
//                pos++;
//            }
//        }
//        System.out.println();
//        for(int i=0;i<Lib;i++) {
//            System.out.print(no_of_days_sign_sorted[i]+" ");
//        }
//        System.out.println();
     
     
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("d_tough_choices.txt",true));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        
        
        boolean book_is_taken[]=new boolean[Books];
        
        int count_days=0;
        System.out.println(Lib);
        outputstream.println(Lib);
        for(int i=0;i<Lib;i++) {
            int lib_no=no_of_days_sign_sorted[i];
            count_days+=no_of_days_sign[lib_no];
            StringBuilder books_to_send=new StringBuilder("");
            int count_books_to_send=0,len=no_of_books_in_lib[0][lib_no];
            for(int j=1;j<=len;j++) {
                int book_no=no_of_books_in_lib[j][lib_no];
                if(!book_is_taken[book_no]) {
                    count_books_to_send++;
                    book_is_taken[book_no]=true;
                    if(count_books_to_send==1) {
                        books_to_send.append(book_no);
                    }
                    else {
                        books_to_send.append(" "+book_no);
                    }
                }
            }
            outputstream.println(lib_no+" "+count_books_to_send);
            System.out.println(lib_no+" "+count_books_to_send);
            outputstream.println(books_to_send);
            System.out.println(books_to_send);
            
            
        }
        outputstream.close();
    }
    public static int find_min_indx(int arr[]) {
        int min=arr[0],indx=0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]<min) {
                min=arr[i];
                indx=i;
            }
        }
        arr[indx]=99999999;
        return indx;
    }
}
