import java.util.Scanner;
class Encrypt {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the string:");
		String s=input.nextLine();
		int l,key,temp=0;
		System.out.println("Enter the key:");
		key=input.nextInt();
		char c,EncrypterChar;
		l=s.length();
		System.out.println("\n\n");
		for(int i=0;i<l;i++) {
		c=s.charAt(i);
		temp=(int)c+key;
		if(temp>126)
		EncrypterChar=(char)(32+temp-127);
		else
		EncrypterChar=(char)temp;
		System.out.print(EncrypterChar);
		}
	}
}