/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractClass;

/**
 *
 * @author User
 */
public class ABCD {
    static class c1 {
        int p;
        boolean q;
        public void print() {
            System.out.println(p+" "+q);
        }
    }
    public static void main(String args[]) {
        c1 c=new c1(); 
        c.print();
    }
}
