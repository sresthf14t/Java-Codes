import java.util.Scanner;
class Reversing_Queue_using_stacks {
	static int a[],front=-1,rear=-1;
	static int as[],top=-1;
	static Scanner input=new Scanner(System.in);
	public static void enqueue(int data) {
		if(rear==a.length-1)
			System.out.println("Queue is full");
		else {
			if(front==-1)
				front=0;
			rear++;
			a[rear]=data;
		}
	}	
	public static int dequeue() {
		int temp=0;
		if(front==-1)
			System.out.println("Queue is empty");
		else {
			System.out.println("\n"+a[front]+" is dequeued");
			temp=a[front];
			for(int i=0;i<rear;i++)
				a[i]=a[i+1];
			rear--;
			if(rear==-1)
			front=rear=-1;
		}
		return temp;
	}
	public static void display() {
		if(front==-1)
			System.out.println("Queue is empty");
		else {
			for(int i=0;i<=rear;i++)
				System.out.println(a[i]);
		}
	}
	public static void push(int data) {
		if(top==as.length-1) 
			System.out.println("Stack is full");
		else {
			top++;
			as[top]=data;
		}
	}
	public static int pop() {
		if(top==-1) 
			System.out.println("Stack is empty");
		else {
			System.out.println("\n"+as[top]+" is poped out of the stack");
			top--;
		}
		return as[top+1];
	}
	public static void display_stack() {
		System.out.println("\n---------------------------------------------------------------------------------------\n");
		if(top==-1)
			System.out.println("Stack is empty");
		else {
			for(int i=0;i<=top;i++)
				System.out.print(as[i]+" ");
		}
		System.out.println("\n\n---------------------------------------------------------------------------------------\n");
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the queue: ");
		a=new int[input.nextInt()];
		as=new int[a.length];
		for(int i=1;i<=a.length;i++) {
			System.out.println("Enter the data: ");enqueue(input.nextInt());
		}
		System.out.println("\nThe queue you entered ");
		display();
		for(int i=0;i<a.length;i++) 
			push(dequeue());
		for(int i=0;i<a.length;i++)
			enqueue(pop());
		System.out.println("The reversed Queue is \n");
		display();
	}
}