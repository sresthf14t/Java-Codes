class Logical_operator_test {
	public static void main(String args[]) {
		int x=0,y=0;
		for(int i=0;i<5;i++)
		{
		if((++x>2)||(++y>2))
		x++;
		System.out.println("Iteration-"+i+"-->>x:"+x+" y:"+y);
		}
		System.out.println("x:"+x+" y:"+y);
	}
}