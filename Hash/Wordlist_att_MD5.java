import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner;
import java.io.*;
import java.lang.*;
//Enter the filename first the the hashed string
class Wordlist_att_MD5 {
	static Scanner input=new Scanner(System.in);
	static String filename=input.next();
	static String hash_in=input.next();		
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
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line="",hash;
		int c=0;
		while((line=br.readLine())!=null) {
		hash=getMd5(line);
		if(hash.equals(hash_in)) {
			System.out.println(hash+":"+line);
			break;
		}
		}
		br.close();
	}
}		