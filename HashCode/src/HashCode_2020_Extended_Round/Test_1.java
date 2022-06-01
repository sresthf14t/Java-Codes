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
public class Test_1 {
    static boolean Lib_is_taken[];
    static boolean book_is_taken[];
    public static void main(String args[]) throws IOException {
        
        //TAKING INPUT
        
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\HashCode\\f_libraries_of_the_world.txt"));
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
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\User\\Desktop\\HashCode\\Py_try\\f_libraries_of_the_world.csv",true));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        outputstream.println("Lib No"+","+"no_of_days_sign"+","+"score");
        for(int i=0;i<Lib;i++) {
            int score=0;
            for(int j=0;j<no_of_books_in_lib[i];j++) {
                score+= books_in_lib[i].get(j);
            }
            outputstream.println(i+","+no_of_days_sign[i]+","+score);
        }
        
        outputstream.close();
    }
    
}
