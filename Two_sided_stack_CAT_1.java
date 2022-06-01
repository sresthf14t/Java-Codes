import java.util.Scanner;
class Two_sided_stack_CAT_1 { 
	static int a[],top1=-1,top2,n,cont2=0;
	static Scanner input=new Scanner(System.in);
	public static void main(String args[]) {
		System.out.println("Enter the size of the stack: ");
		a=new int[input.nextInt()];
		top2=n=a.length;
		int choice=0;
		while(choice!=4) {
			System.out.println("\nEnter the choice\n	1.Push\n	2.pop\n	3.display\n	4.Exit");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the data to be pushed: ");push(input.nextInt());break;
				case 2:pop();break;
				case 3:display();break;
				case 4:break;
				default: System.out.println("Invalid Choice");
			}
		}
	}
	public static void push(int data) {
		if(top1+cont2==n-1) {
			System.out.println("Stack is full");
			return;
		}
		System.out.println("Where should the data be pushed\n	1.Stack Front\n	2.Stack Back");
		int c=input.nextInt();
		if(c==1) {
			top1++;
			a[top1]=data;
		}
		else if(c==2) {
			top2--;
			a[top2]=data;
			cont2++;
		}
		else
			System.out.println("Invalid choice");
	}
	public static void pop() {
		System.out.println("Where should the data be poped\n	1.Stack Front\n	2.Stack Back");
		int c=input.nextInt();
		if(c==1) {
			if(top1==-1) {
				System.out.println("Stack is empty");
				return;
			}
			System.out.println(a[top1]+" has been poped out");
			top1--;
		}
		else if(c==2) {
			if(top2==n) {
				System.out.println("Stack is empty");
				return;
			}
			System.out.println(a[top2]+" has been poped out");
			top2++;
			cont2--;
		}
		else
			System.out.println("Invalid choice");
	}
	public static void display() {
		System.out.println("\n Stack Front\n");
		for(int i=0;i<=top1;i++)
			System.out.println(a[i]);
		System.out.println("\n Stack Back\n");
		for(int i=n-1;i>=top2;i--)
			System.out.println(a[i]);
		System.out.println("\n Full Array\n");
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
	}
}