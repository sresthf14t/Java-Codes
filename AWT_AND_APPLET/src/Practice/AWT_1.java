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
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class AWT_1 extends Applet implements ActionListener, ItemListener{
    Label label1;
    TextField txtfld1;
    Button b1,b2;
    Checkbox cb1,cb2;
    CheckboxGroup cbg1;
    Choice ch1;
    List l1;
    boolean B1=false,B2=false;
    public void init() {
        setSize(800,500);
        setLayout(null);
        label1=new Label("Label");
        label1.setBounds(100, 100, 50, 20);
        add(label1);
        txtfld1=new TextField();
        txtfld1.setBounds(200, 100, 100, 20);
        txtfld1.addActionListener(this);
        add(txtfld1);
        b1=new Button("Button1");
        b1.addActionListener(this);
        b2=new Button("Button2");
        b2.addActionListener(this);
        b1.setBounds(100, 200, 100, 20);
        b2.setBounds(200, 200, 100, 20);
        add(b1);
        add(b2);
        cbg1=new CheckboxGroup();
        cb1=new Checkbox("Checkbox1",cbg1,true);
        cb2=new Checkbox("Checkbox2",cbg1,false);
        cb1.setBounds(100, 300, 100, 20);
        cb2.setBounds(200, 300, 100, 20);
        add(cb1);
        cb1.addItemListener(this);
        cb2.addItemListener(this);
        add(cb2);
        ch1=new Choice();
        ch1.addItemListener(this);
        ch1.add("Choice1");
        ch1.add("Choice2");
        ch1.add("Choice3");
        ch1.add("Choice4");
        ch1.setBounds(100, 400, 100, 20);
        add(ch1);
        l1=new List();
        l1.addActionListener(this);
        l1.add("Choice1");
        l1.add("Choice2");
        l1.add("Choice3");
        l1.add("Choice4");
        l1.select(0);
        l1.setBounds(100, 500, 100, 20);
        add(l1);
    }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1) {
            B1=true;
        }
        if(ae.getSource()==b2) {
            B2=true;
        }
        repaint();
        
    }
    public void itemStateChanged(ItemEvent ie) {
        repaint();
    }
    public void paint(Graphics g) {
        g.drawString(l1.getSelectedItem(), 100,600);
        if(cb1.getState()) {
             g.drawString("Checkbox1", 200,600);
        }
        if(cb2.getState()) {
             g.drawString("CheckBox2", 400,600);
        }
        g.drawString(ch1.getSelectedItem(),100,700);
        g.drawString(txtfld1.getText(), 100, 800);
        if(B1) {
           g.drawString(b1.getLabel(), 100, 900); 
           B1=false;
        }
        if(B2) {
           g.drawString(b2.getLabel(), 200, 900); 
           B2=false;
        }
    }
}
