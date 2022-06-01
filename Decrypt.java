import java.util.Scanner;
class Decrypt {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the Encrypted word");
		String s=input.nextLine();
		int key,l,i,temp,temp1;
		char c,DecryptedChar;
		System.out.println("Enter the key");
		key=input.nextInt();
		l=s.length();
		for(i=0;i<l;i++) {
		c=s.charAt(i);
		temp1=(c-32)+127-key;
		temp=temp1+key;
		DecryptedChar=(char)temp1;
		if(temp>126 && temp1<128)
		System.out.print(DecryptedChar);
		else {
		temp1=c-key;
		DecryptedChar=(char)temp1;
		System.out.print(DecryptedChar);
		}
		}
	}
}
		
		
		