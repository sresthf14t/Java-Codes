import java.util.Scanner;
class Postfix_Evaluation {
	static int a[],top=-1;
	static Scanner input=new Scanner(System.in);
	public static int string_to_int(String s) {
		int sum=0;
		for(int i=0;i<s.length();i++)
			sum+=(int)((s.charAt(i)-48)*Math.pow(10,(s.length()-i-1)));
		return sum;
	}
	public static void push(int data) {
		top++;
		a[top]=data;
	}
	public static int pop() {
		int temp=a[top];
		top--;
		return temp;
	}
	public static void display() {
		System.out.println("\n-------------------------------STACK-------------------------------");
		for(int i=0;i<=top;i++)
			System.out.print(a[i]+" ");
		System.out.println("\n-------------------------------------------------------------------");
	}
	public static void main(String args[]) {
		System.out.println("Enter the postfix string");
		String s=input.nextLine();
		a=new int[s.length()];
		String temp="";
		int a,b;
		for(int i=0;i<s.length();i++) {
			if((s.charAt(i)>=48&&s.charAt(i)<=57)||s.charAt(i)==' ') {
				if(s.charAt(i)!=' ')
					temp+=s.charAt(i);
				else if (s.charAt(i)==' '&&temp!=""){ 
					push(string_to_int(temp));
					temp="";
				}
			}
			else if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='%') {
				a=pop();
				b=pop();
				switch(s.charAt(i)) {
					case '+':push(b+a);break;
					case '-':push(b-a);break;
					case '*':push(b*a);break;
					case '/':push(b/a);break;
					case '%':push(b%a);break;
				}
			}
			display();
		}
		System.out.println("\n\nEvaluated postfix expression is: "+pop());
	}
	
}	
			  
	