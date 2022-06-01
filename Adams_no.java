import java.util.Scanner;
class Adams_no {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in); 
		int n=input.nextInt();
		int rev=0,rev_n=0,m,temp,check;
		check=n;
		m=n;
		temp=n;
		while(temp!=0)
		{
		m=m%10;
		rev=(rev*10)+m;
		temp=temp/10;
		m=temp;
		}
		n=(int)Math.pow(n,2);
		rev=(int)Math.pow(rev,2);
		m=n;
		temp=n;
		while(temp!=0)
		{
		m=m%10;
		rev_n=(rev_n*10)+m;
		temp=temp/10;
		m=temp;
		}
		if(rev==rev_n)
		System.out.println(check+" is an Adams number");		
		else
		System.out.println(check+" is not an Adams number");
	}
}
		
		