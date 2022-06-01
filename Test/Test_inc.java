class Test_inc {
	public static void main(String args[]) {
		int x=0,y=0,z=0;
		int X=(++x+y--)*z++;
		System.out.println("X="+X);
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("z="+z);
	}
}