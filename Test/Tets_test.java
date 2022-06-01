class Tets_test {
	public static void main(String args[]) {
		double sum=0;
		for(double d=0;d<1;)
		{
		System.out.println(sum+" ");
		sum+=sum+d;
		d+=0.1;
		}
		System.out.println(sum);
	}
}