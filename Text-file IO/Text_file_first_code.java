import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Text_file_first_code {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String filename=input.next();
		PrintWriter outputStream=null;
		try {
		outputStream=new PrintWriter(filename);
		}
		catch(FileNotFoundException e) {
			System.out.println("Error in opening file"+filename);
		System.exit(0);
		}
		System.out.println("Enter three lines");
		String line;
		for(int i=1;i<=3;i++) {
		line=input.next();
		outputStream.println(i+"\t"+line);
		}
		outputStream.close();
		System.out.println("Those three lines were written to "+filename);
	}
}