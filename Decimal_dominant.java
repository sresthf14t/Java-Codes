import java.util.Scanner;
class Decimal_dominant {
	public static void main(String args[]) {
		boolean b=false;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the size of the array");
		int a[]=new int[input.nextInt()];
		int c=0;
		for(int i=0;i<a.length;i++)
			a[i]=input.nextInt();
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a.length;j++) {
				if(a[i]==a[j])
					c++;
			}
			if(c>(a.length/10)) {
				System.out.println("The decimal dominant number is "+a[i]);
				b=true;
				break;
			}
			c=0;
		}
		if(!b)
			System.out.println("\nThe array does not have a decimal doinant");
	}
}
	
