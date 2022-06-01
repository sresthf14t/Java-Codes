import java.util.Scanner;
class String_palindrom_check {
	public static void main(String argsp[]) {
		Scanner input=new Scanner(System.in);
		String s1=input.nextLine();
		String s2=s1;
		boolean b=true;
		for(int i=0,j=s2.length()-1;i<s1.length();i++,j--) {
		if(s1.charAt(i)!=s2.charAt(j)) {
		b=false;
		break;
		}
		}
		if(b)
		System.out.println("Palindrome");
		else
		System.out.println("Not Palindrome");
	}
}
		