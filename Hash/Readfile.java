import java.io.*;
class Readfile {
	public static void main(String args[]) throws IOException {
		int ch;
		String s="";
		FileReader fr=new FileReader("in.txt");
		while((ch=fr.read())!=-1) {
			System.out.print((char)ch);
			s+=(char)ch;
		}
		System.out.print("\n\n"+s);
		fr.close();
	}
}