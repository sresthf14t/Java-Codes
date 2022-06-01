/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sync_2;

/**
 *
 * @author User
 */
public class Calc {
    int count=0;
    synchronized void inc() {
        for(int i=0;i<10000;i++) {
            count++;
        }
    }
    synchronized void dec() {
        for(int i=0;i<10000;i++) {
            count--;
        }
    } 
}