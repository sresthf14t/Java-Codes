import java.util.Scanner;
import java.io.*;
class File_copy {
	public static void main(String args[]) throws IOException {
		System.out.println("\n\nThis will take input a existing wordlist file and copy it to a output file\n\n");
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the name of the file to store copies items: ");
		String filename=input.next();
		System.out.println("Enter the name of the existing wordlist : ");
		String wdlst=input.next();
		BufferedReader br = new BufferedReader(new FileReader(wdlst));
		PrintWriter outputstream=new PrintWriter(filename);
		String line="",temp;
		while((line=br.readLine())!=null) {
		outputstream.println(line);
		}
		br.close();
		outputstream.close();
	}
}

	