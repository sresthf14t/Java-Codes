import java.util.Scanner;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.io.*;
class Create_hash_file_of_wordlist {
public static String getMd5(String input) 
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	} 
	public static void main(String args[]) throws IOException {
		System.out.println("\n\n---------------------This will take input a existing wordlist file and generate hash code of each word and output to a new file---------------------\n\n");
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the name of the file to store Hashes: ");
		String filename=input.next();
		System.out.println("Enter the name of the existing wordlist : ");
		String wdlst=input.next();
		BufferedReader br = new BufferedReader(new FileReader(wdlst));
		PrintWriter outputstream=new PrintWriter(filename);
		String line="",temp;
		int i=0;
		while((line=br.readLine())!=null) {
		i++;
		temp=getMd5(line);
		outputstream.println(temp);
		if(i%10000==0)
		System.out.print("=");
		}
		br.close();
		outputstream.close();
	}
}
		
		

	