import java.util.Scanner;
class Additive_Cipher {
	public static String encipher(String s,int key) {
		int temp;
		String cipher="";
		for(int i=0;i<s.length();i++) {
			temp=(int)s.charAt(i);
			temp-=65;
			temp+=key;
			temp%=26;
			temp+=65;
			cipher+=(char)temp;
		}
		return cipher;
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("\n"+encipher(input.next(),input.nextInt()));
	}
}