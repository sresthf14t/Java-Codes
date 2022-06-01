import java.io.*;
import java.util.Scanner;
import java.lang.*;
class Test_cls {
	public static void main(String args[]) throws IOException {
		String s="abc";
		System.out.println(s);	
		clrscr();
		System.out.println(s);
	}
	public static void clrscr(){
    //Clears Screen in java
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {}
}
}