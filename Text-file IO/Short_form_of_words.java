import java.util.Scanner;
import java.io.*;
class Short_form_of_words {
	public static void main(String args[]) throws IOException {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the file name");
		String filename=input.next();
		PrintWriter outputstream =new PrintWriter(new FileOutputStream(filename,true));
		System.out.println("Enter the number of Lines to enter");
		int n=input.nextInt();
		String s;
		for(int i=1;i<=n;i++) {
		Scanner in=new Scanner(System.in);
		s=in.nextLine();
		for(int j=0;j<s.length();j++) {
		if(s.charAt(j)>='A'&&s.charAt(j)<'Z')
		outputstream.print(s.charAt(j));
		}
		outputstream.println();
		}
		outputstream.close();
	}
}
