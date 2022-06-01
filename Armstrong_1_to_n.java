class Armstrong_1_to_n {
	public static void main(String args[]) {
		int n,m,temp,sum=0,d=0;
		n=1000;
		for(int i=1;i<=n;i++)
		{
		sum=0;
		d=0;
		m=i;
		temp=i;
		while(temp!=0)
		{
		temp=temp/10;
		d++;
		}
		temp=i;
		while(temp!=0)
		{
		m=m%10;
		sum=sum+((int)Math.pow(m,d));
		temp=temp/10;
		m=m%10;
		m=temp;
		}
		if(sum==i)
		System.out.println(i);
		}
	}
}
		