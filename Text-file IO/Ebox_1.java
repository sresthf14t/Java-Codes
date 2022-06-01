import java.util.Scanner;
import java.io.*;
class Ebox_1 {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of abbreviations");
        int n=input.nextInt();
        FileWriter fw=new FileWriter("output.txt");
        String s;
        for(int i=1;i<=n;i++) {
            Scanner in=new Scanner(System.in);
            s=in.nextLine();
            for(int j=0;j<s.length();j++) {
                if(s.charAt(j)>=65&&s.charAt(j)<=90)
                fw.write(s.charAt(j));
            }
		System.lineSeparator();
        }
        fw.close();
    }
}