import java.util.Scanner;
import java.io.*;
class Compare_hash_with_hashed_wordlist {
	public static void main(String args[]) throws IOException {
		System.out.println("\n\n----------------------------------This will compare a hashed Wordlist file with the given hash----------------------------------\n\n");
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the name of the file in which Hashes are stored: ");
		String filename=input.next();
		System.out.println("Enter the Hash : ");
		String hash=input.next();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line="";
		int i=0;
		System.out.println("STATUS-->'=' means the program has read 10000 words \n");
		System.out.println("\n\n----------------------------------------------STARTING TO READ FILE----------------------------------------------\n\n");
		while((line=br.readLine())!=null) {
		i++;
		if(line.equals(hash)) {
			System.out.println("\n\nHash found in the given file\n\n");
			break;
		}
		if(i%10000==0)
			System.out.print("=");
		}
		System.out.println("Enter the name of the wordlist file: ");
		filename=input.next();
		line="";
		BufferedReader br1 = new BufferedReader(new FileReader(filename));
		for(int k=1;k<i;k++)
			line=br1.readLine();
		line=br1.readLine();
		System.out.println("\n\nCracked: "+line);
		br.close();
	}
}