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
public class Tabels extends JApplet{
    public void init() {
        JApplet app=new JApplet();
        setLayout(null);
        setSize(1000,1000);
        String data[][]={ {"101","Amit","670000"},    
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    
        String column[]={"ID","NAME","SALARY"};         
        JTable jt=new JTable(data,column);    
        jt.setBounds(300,400,200,300);          
        //JScrollPane sp=new JScrollPane(jt);
        add(jt);
    }
}
