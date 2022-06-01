/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticBlock;

/**
 *
 * @author User
 */
public class StaticTest {
    public static void main(String args[]) {
        for(int i=1;i<1000000000;i++) {
            for(int j=1;j<1;j++) {
                if((i&j)<0) {
                    System.out.println(i+" "+j+" "+(i&j));
                }
            }
        }
    }
}
