class Ascii_1_127 {
	public static void main(String args[]) {
		char c=0;
		System.out.println("ASCII 0="+c);
		for(int i=1;i<=127;i++) {
		c=(char)i;
		System.out.println("ASCII "+i+"="+c);
		}
	}
}