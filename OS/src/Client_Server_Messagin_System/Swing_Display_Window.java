/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Server_Messagin_System;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
public class Swing_Display_Window extends JFrame implements ActionListener, Runnable {   // implements ActionListener
    Container cont; 
    JTextField txt;
    JButton send;
    JLabel msg;
    JScrollPane scrollPane;
    int thread;
    static boolean aval=true;
    int curr_coord=0;
    static Queue<String> msg_que_1 = new LinkedList<>();
    static Queue<String> msg_que_2 = new LinkedList<>();
    public Swing_Display_Window() {
        setTitle("Chat Box"); 
        setBounds(0, 0, 900, 800); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
        cont = getContentPane(); 
        cont.setLayout(null); 
        
        //Text Field
        txt=new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 15)); 
        txt.setSize(400, 20); 
        txt.setLocation(200, 700); 
        cont.add(txt); 
        setVisible(true); 
        
        //Button
        send = new JButton("Send"); 
        send.setFont(new Font("Arial", Font.PLAIN, 15)); 
        send.setSize(100, 20); 
        send.setLocation(650, 700); 
        send.addActionListener(this); 
        cont.add(send);
        setVisible(true);
    }
    public void run() { 
        while(true) {
            System.out.print("");
            while(thread==1 && !msg_que_1.isEmpty()) {
                paint(10,msg_que_1.poll());
            }
            while(thread==2 && !msg_que_2.isEmpty()) {
                paint(10,msg_que_2.poll());
            }
        }
    }
    public void paint(int x_coord,String str) {
        msg = new JLabel(str); 
        msg.setFont(new Font("Arial", Font.PLAIN, 20)); 
        msg.setSize(100, 20); 
        msg.setLocation(x_coord, curr_coord); 
        curr_coord+=25;
        cont.add(msg);
        setVisible(true);
        cont.repaint();
    }
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == send) {
            while(!aval) {
                continue;
            }
            aval=false;
//            System.out.println(txt.getText());
            Server s=new Server();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
           LocalDateTime now = LocalDateTime.now();  
            s.write(dtf.format(now)+"\tPerson "+thread+": "+txt.getText());
            paint(600,txt.getText());
            if(thread==1) {
                msg_que_2.add(txt.getText());
            }
            else {
               msg_que_1.add(txt.getText());
            }
            txt.setText("");
            aval=true;
        }
    }
    public static void main(String args[]) {
        Swing_Display_Window s1=new Swing_Display_Window();
        Swing_Display_Window s2=new Swing_Display_Window();
        s1.thread=1;
        s2.thread=2;
        Thread t1 = new Thread(s1);  
        Thread t2 = new Thread(s2);
        t1.start();
        t2.start();
    }
}
