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
public class Main {
    public static void main(String args[]) {
        Q que=new Q();
        Thread producer=new Thread(new Runnable(){
            public void run() {
                for(int i=0;;i++) {
                  que.produce(i);  
                } 
            }
        });
        Thread consumer=new Thread(new Runnable(){
            public void run() {
                for(int i=0;;i++) {
                  que.consume();
                } 
            }
        });
        producer.start();
        consumer.start();
        
    }
}
