/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import javax.swing.*;  
public class Tabbed_Panes extends JApplet{
    public void init() {
        JPanel p1=new JPanel();  
        JLabel label1=new JLabel("Label1");
        p1.add(label1);
        JPanel p2=new JPanel();  
        JPanel p3=new JPanel();
        JLabel label2=new JLabel("Label2");
        p2.add(label2);
        JLabel label3=new JLabel("Label3");
        p3.add(label3);
        JTabbedPane tp=new JTabbedPane();  
        tp.setBounds(50,50,200,200);  
        tp.add("main",p1);  
        tp.add("visit",p2);  
        tp.add("help",p3);
        add(tp);
    }
}
