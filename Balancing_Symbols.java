import java.util.Scanner;
class Balancing_Symbols {
	static char a[];
	static int top=-1;
	public static void push(char data) {
		if(top==a.length-1) 
			System.out.println("Stack is full");
		else {
			top++;
			a[top]=data;
		}
	}
	public static void pop() {
		if(top==-1) 
			System.out.println("Stack is empty");
		else {
			System.out.println("\n"+a[top]+" is poped out of the stack");
			top--;
		}
	}
	public static void display() {
		System.out.println("\n---------------------------------------------------------------------------------------\n");
		if(top==-1)
			System.out.println("Stack is empty");
		else {
			for(int i=0;i<=top;i++)
				System.out.print(a[i]+" ");
		}
		System.out.println("\n\n---------------------------------------------------------------------------------------\n");
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);		
		System.out.println("Enter the symbols String");
		String s=input.next();
		a=new char[s.length()];
		boolean b=true;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='{'||s.charAt(i)=='('||s.charAt(i)=='[')
				push(s.charAt(i));
			else if((top!=-1)&&((s.charAt(i)=='}'&&a[top]=='{')||(s.charAt(i)==')'&&a[top]=='(')||(s.charAt(i)==']'&&a[top]=='[')))
				pop();
			else 
				b=false;
			System.out.println();
			display();
			System.out.println();
		}
		if(top==-1&&b)
			System.out.println("\nValid Symbol String");
		else
			System.out.println("\nInvalid Symbol String");
	}
}