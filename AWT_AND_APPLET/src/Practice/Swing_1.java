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
import java.awt.*;
import java.awt.event.*;

public class Swing_1 extends JApplet implements ActionListener {
    ButtonGroup bg1,bg2;
    JLabel label1;
    JTextField txtfld1,txtfld2;
    JButton b1,b2;
    JToggleButton tb1;
    JCheckBox cb1,cb2;
    JRadioButton rb1,rb2;
    JComboBox combx1;
    JApplet app;
    public void init() {
        app=new JApplet();
        setLayout(null);
        setSize(800,500);
        label1=new JLabel("Label");
        label1.setBounds(100,100,50,50);
        add(label1);
        txtfld1=new JTextField();
        txtfld1.setBounds(200, 100, 200, 20);
        txtfld1.addActionListener(this);
        add(txtfld1);
        b1=new JButton("Button1");
        b1.setBounds(100,200,100 , 50);
        b1.addActionListener(this);
        b2=new JButton("Button2");
        b2.setBounds(200,200,100 , 50);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        tb1=new JToggleButton("ToggleButton1");
        tb1.addActionListener(this);
        tb1.setBounds(100,300,150 , 50);
        add(tb1);
        cb1=new JCheckBox("CheckBox1");
        cb1.addActionListener(this);
        cb1.setBounds(100,400,100 , 50);
        cb2=new JCheckBox("CheckBox2");
        cb2.addActionListener(this);
        cb2.setBounds(200,400,100 , 50);
        add(cb1);
        add(cb2);
        bg1=new ButtonGroup();
        cb1.addActionListener(this);
        cb2.addActionListener(this);
        bg1.add(cb1);
        bg1.add(cb2);
        rb1=new JRadioButton("RadioButton1");
        rb1.addActionListener(this);
        rb1.setBounds(100,500,150 , 50);
        rb2=new JRadioButton("RadioButton2");
        rb2.addActionListener(this);
        bg2=new ButtonGroup();
        bg2.add(rb1);
        bg2.add(rb2);
        rb2.setBounds(250,500,150 , 50);
        add(rb1);
        add(rb2);
        String combx_items[]={"Option1","Option2","Option3","Option4"};
        combx1=new JComboBox(combx_items);
        combx1.addActionListener(this);
        combx1.setBounds(100,600,100 , 50);
        add(combx1);
        txtfld2=new JTextField();
        txtfld2.setBounds(100, 700, 200, 20);
        txtfld2.addActionListener(this);
        add(txtfld2);
    }
   public void actionPerformed(ActionEvent ae) {
       if(ae.getSource()==txtfld1) {
            txtfld2.setText(txtfld1.getText());
           
       }
       if(ae.getSource()==b1) {
           txtfld2.setText(b1.getText());
       }
       if(ae.getSource()==b2) {
           txtfld2.setText(b2.getText());
       }
       if(ae.getSource()==tb1) {
           txtfld2.setText(tb1.getText()+" "+tb1.isSelected());
       }
       if(ae.getSource()==cb1) {
           txtfld2.setText(cb1.getText()+" "+cb1.isSelected());
       }
       if(ae.getSource()==cb2) {
           txtfld2.setText(cb2.getText()+" "+cb2.isSelected());
       }
       if(ae.getSource()==rb1) {
           txtfld2.setText(rb1.getText()+" "+rb1.isSelected());
       }
       if(ae.getSource()==rb2) {
           txtfld2.setText(rb2.getText()+" "+rb2.isSelected());
       }
       if(ae.getSource()==combx1) {
           txtfld2.setText(""+combx1.getSelectedItem());
       }
   }
}
