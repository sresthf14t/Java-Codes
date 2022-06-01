/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class CharTypecastTest {
    public static void main(String args[]) {
        for(int i=50000;i<=100000;i++) {
            System.out.println(i+" "+ (char)i);
        }
    }
}
