import java.util.Scanner;
class String_last_char_rev {
	public static void main(String args[]) {
		System.out.println("\n\nThe program will run until you enter a string such that\nif yot put the first alphabet in the last and read it \nbackwards it will read the same as the original one");
		Scanner input=new Scanner(System.in);
		for(;;) {
		String s=input.next();
		int l=s.length();
		char a[]=new char[l];
		for(int i=0;i<l;i++)
		a[i]=s.charAt(i);
		char r[]=new char[l];
		r=rev(a,l);
		boolean b;
		b=check(a,r,l);
		if(b)
		break;
		}
	}
	public static char[] rev(char a[],int l) {
		char r[]=new char[l];
		r[l-1]=a[0];
		for(int i=0;i<l-1;i++)
		r[i]=a[i+1];
		char temp[]=new char[l];
		for(int i=0;i<l;i++)
		temp[i]=r[l-i-1];
		return temp;
	}
	public static boolean check(char a[],char r[],int l) {
		boolean b=false;
		for(int i=0;i<l;i++) {
		if(a[i]==r[i])
		b=true;
		else {
		b=false;
		break;
		}
		}
		return b;
	}
}


		
		