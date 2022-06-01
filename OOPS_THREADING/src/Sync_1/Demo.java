/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sync_1;

/**
 *
 * @author User
 */
public class Demo {
    int count=0;
    public void inc() {
        for(int i=0;i<10000;i++) {
            count++;
        }
    }
    public void dec() {
        for(int i=0;i<10000;i++) {
            count--;
        }
    }
}
