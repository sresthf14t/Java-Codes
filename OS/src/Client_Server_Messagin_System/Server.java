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
public class Server {
    public void write(String str) {
        String filename="C:\\Users\\User\\Desktop\\Chat Box.txt";
        PrintWriter outputstream=null;
        try {
            outputstream=new PrintWriter(new FileOutputStream(filename,true));
        }
        catch(FileNotFoundException e) {
            System.exit(0);
        }
        outputstream.println(str);
        outputstream.close();
    }
}
