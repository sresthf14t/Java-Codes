import java.util.Scanner;
class Queue_using_array {
	static int a[],front=-1,rear=-1;
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
	public static void dequeue() {
		if(front==-1)
			System.out.println("Queue is empty");
		else {
			System.out.println("\n"+a[front]+" is dequeued");
			for(int i=0;i<rear;i++)
				a[i]=a[i+1];
			rear--;
			if(rear==-1)
			front=rear=-1;
		}
	}
	public static void display() {
		if(front==-1)
			System.out.println("Queue is empty");
		else {
			for(int i=0;i<=rear;i++)
				System.out.println(a[i]);
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the queue: ");
		a=new int[input.nextInt()];
		int choice=0;
		while(choice!=4) {
			System.out.println("Enter the choice\n	1.Enqueue\n	2.Dequeue\n	3.Display\n	4.Exit");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the data: ");enqueue(input.nextInt());break;
				case 2:dequeue();break;
				case 3:display();break;
				case 4:break;
				default:System.out.println("Wrong choice");
			}
		}
	}
}
		
		
				