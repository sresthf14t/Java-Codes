import java.util.Scanner;
import java.io.*;
class Writing_in_existing_text_file {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String filename=input.next();
		PrintWriter outputstream=null;
		try {
		outputstream=new PrintWriter(new FileOutputStream(filename,true));
		}
		catch(FileNotFoundException e) {
		System.out.println("Error in opening file"+filename);
		System.exit(0);
		}
		System.out.println("How many new lines you want to enter");
		int n=input.nextInt();
		String line;
		for(int i=1;i<=n;i++) {
		line=input.next();
		outputstream.println(i+"\t"+line);
		}
		outputstream.close();
		System.out.println(n+" lines were written to the file "+filename);
	}
}
	