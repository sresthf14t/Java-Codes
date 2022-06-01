import java.util.Scanner;
class Stacks_using_array {
	static int a[],top=-1;
	public static void push(int data) {
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
		System.out.println("Enter the size of the stack");
		int n=input.nextInt();
		a=new int[n];
		int choice=0;
		while(choice!=4) {
			System.out.println("\nEnter the choice\n	1.Push\n	2.pop\n	3.display\n	4.Exit");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the data to be pushed: ");push(input.nextInt());break;
				case 2:pop();break;
				case 3:display();break;
				default: System.out.println("Invalid Choice");
			}
		}
	}
}