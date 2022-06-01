/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pro_Con;

/**
 *
 * @author User
 */
public class Q {
    int a[]=new int [10000];
    int rear=-1;
    void enqueue(int ele) {
        rear++;
        a[rear]=ele;
    }
    int dequeue() {
        rear--;
        return a[rear+1];
    }
    synchronized void produce(int ele) {
        if(rear==a.length-1) {
            try {
                wait();
            }
            catch(Exception e) {
                System.out.println(e);
            }        
        }
        System.out.println("Product "+ele+" is produced");
        enqueue(ele);
        notify();
    }
    synchronized void consume() {
        if(rear==-1) {
            try {
                wait();
            }
            catch(Exception e) {
                System.out.println(e);
            }        
        }
        System.out.println("Product "+dequeue()+" is comsumed");
        notify();
    }
}
