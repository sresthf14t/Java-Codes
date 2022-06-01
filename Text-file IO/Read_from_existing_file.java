import java.util.Scanner;
import java.io.*;
class Read_from_existing_file {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String filename=input.next();
		Scanner inputstream=null;
		try {
		inputstream=new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
		System.out.println("The file \""+filename+"\" could not be opened");
		System.exit(0);
		}
		String line;
		int c=0;
		System.out.println("\n");
		while(inputstream.hasNextLine()) {
		c++;
		if(c==100)
			break;
		line=inputstream.nextLine();
		System.out.print("\""+line+"\""+",");
		}
		inputstream.close();
	}
}