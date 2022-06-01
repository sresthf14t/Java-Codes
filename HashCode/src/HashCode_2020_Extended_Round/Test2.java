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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Test2 {
    public static void main(String args[]) throws IOException {
        ArrayList<Integer> lst = new ArrayList<Integer>();
        System.out.println(lst.size());
        lst.add(4);
        lst.add(3);
        lst.add(2);
        lst.add(1);
        System.out.println(lst.size());
        for(int i=0;i<lst.size();i++) {
            System.out.print(lst.get(i)+" ");
        }
        System.out.println();
        lst.remove(0);
        for(int i=0;i<lst.size();i++) {
            System.out.print(lst.get(i)+" ");
        }
        System.out.println();
        System.out.println(lst.size());
    }
}
