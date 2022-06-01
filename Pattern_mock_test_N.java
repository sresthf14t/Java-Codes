class Pattern_mock_test_N {
	public static void main(String args[]) {
		int n=4;
		for(int i=0;i<=(2*n-1);i++) {
		for(int j=0;j<=(2*n);j++) {
		if(i+j==n)
		System.out.print(0);
		else if(i+j==n+1)
		System.out.print(1);
		else if(i+j==n+2)
		System.out.print(2);
		else if(i+j==n+3)
		System.out.print(3);
		else if(i+j==n+4)
		System.out.print(4);
		else
		System.out.print(" ");
		}
		System.out.println();	
		}
	}
}