import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
class Brute_force {
	static Scanner input=new Scanner(System.in);
	static int n=input.nextInt();	//length of words
	static int a[]=new int[n];
	static int t=(int)Math.pow(26,n);
	static int c=t/1000;
	static String hash=input.next();	//hashed code
	public static void main(String args[]) throws IOException {
		long startTime = System.currentTimeMillis();
		for(int i=0;i<n;i++)
			a[i]=97;
		int temp;
		String s[]=new String[t];
		String hash_check;
		System.out.println("\n\n----------------------------------Checking with Wordlist-----------------------------------\n\n");
		for(int i=1;i<=t;i++) {
			s[i-1]="";
			for(int j=0;j<n;j++) {
				s[i-1]=(char)a[j]+s[i-1];
				temp=(int)(Math.pow(26,j));
				if(i%temp==0)
					a[j]++;
				if(a[j]==123)
					a[j]=97;
			}
			hash_check=getMd5(s[i-1]);
			if(hash_check.equals(hash)) {
				System.out.println("\n\n"+hash+" : "+s[i-1]);
				break;
			}
			if(i%c==0)
				System.out.print("=");
		}
		long endTime = System.currentTimeMillis();


		long timeElapsed = endTime - startTime;


		System.out.println("Execution time in milliseconds: " + timeElapsed);


	}
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
}