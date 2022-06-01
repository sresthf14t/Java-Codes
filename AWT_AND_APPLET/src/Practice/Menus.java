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
public class Menus implements ActionListener{
    MenuItem item1;
    Frame f;
    Menus() {
        f=new Frame("NEW FRAME");
        MenuBar mb=new MenuBar();
        Menu menu=new Menu("Menubar1");
        item1=new MenuItem("MenuItem1");
        item1.addActionListener(this);
        MenuItem item2=new MenuItem("MenuItem2");
        MenuItem item3=new MenuItem("MenuItem3");
        MenuItem item4=new MenuItem("MenuItem4");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.setEnabled(true);
        mb.add(menu);
        f.setMenuBar(mb);
        f.setSize(1000,1000);
        f.setVisible(true);
    }
    public static void main(String args[]) {
        Menus m1=new Menus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==item1) {
            Label l1=new Label("Item1");
            l1.setBounds(100, 100, 100,100);
            f.add(l1);
            System.out.println(1);
        }
    }
}
