import java.util.Scanner;
class Queue_using_array_2nd_Max {
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
	public static void display() {
		if(front==-1)
			System.out.println("Queue is empty");
		else {
			for(int i=0;i<=rear;i++)
				System.out.println(a[i]);
		}
	}
	public static void sec_max() {
		int max=a[front],min=a[front],max_ind=front;
		for(int i=front+1;i<=rear;i++) {
			if(a[i]<min) {
				min=a[i];
			}
			if(a[i]>max) {
				max=a[i];
				max_ind=i;
			}
		}
		a[max_ind]=min;
		max=a[front];
		for(int i=front+1;i<=rear;i++) {
			if(a[i]>max)
				max=a[i];
		}
		System.out.println("The Second max element is="+max);
	} 
	public static void main(String args[]) {
		System.out.println("Enter the size of the queue: ");
		a=new int[input.nextInt()];
		for(int i=1;i<=a.length;i++) {
			System.out.println("Enter the data");
			enqueue(input.nextInt());
		}
		System.out.println();
		display();
		System.out.println();
		sec_max();
	}
}
		
		
				