import java.util.Scanner;
class Infix_to_postfix {
	static char a[];
	static int top=-1;
	static Scanner input=new Scanner(System.in);
	static String post="";
	public static void push(char data) {
		top++;
		a[top]=data;
	}
	public static char pop() {
		top--;
		return a[top+1];
	}
	public static int op_prec(char data) {
		if(data=='*'||data=='/'||data=='%')
			return 2;
		else if(data=='+'||data=='-')
			return 1;
		else
			return 0;
	}
	public static void operator_push_handle(char s) {
		int a1=op_prec(s),b1;
		if(top==-1)
			b1=0;
		else 
			b1=op_prec(a[top]);
		if(a1>b1)
			push(s);
		else {
			post+=pop();
			operator_push_handle(s);
		}
	}	
	public static void display() {
		System.out.println("\n----------------------------------STACK----------------------------------\n");
		for(int i=0;i<=top;i++)
			System.out.print(a[i]+" ");
		System.out.println("\n-------------------------------------------------------------------------\n");
	}
	public static void main(String args[]) {
		System.out.println("Enter the infix String: ");
		String s=input.next();
		a=new char[s.length()];
		for(int i=0;i<s.length();i++) {
			if((s.charAt(i)>=48&&s.charAt(i)<=57)||(s.charAt(i)>=65&&s.charAt(i)<=90)||(s.charAt(i)>=97&&s.charAt(i)<=122))
				post+=s.charAt(i);
			else if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='%') {
				if(top==-1||a[top]=='(')
					push(s.charAt(i));
				else
					operator_push_handle(s.charAt(i));
			}
			else if(s.charAt(i)=='(')
				push(s.charAt(i));
			else if(s.charAt(i)==')') {
				while(a[top]!='(')
					post+=pop();
				pop();
			}
			display();
			System.out.println();
		}
		while(top!=-1)
			post+=pop();
		System.out.println("The postfix Expression is:\n"+post);
	}
}
				
					
			  