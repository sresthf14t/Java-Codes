import java.util.Scanner;
class Ques3 {
	public static void decrypt(String s,int key) {
		String dec="";
		for(int j=0;j<s.length();j++) {
			int temp=(int)s.charAt(j);
			temp-=65;
			temp-=key;
			temp%=26;
			if(temp<0)
				temp+=26;
			temp+=65;
			dec+=(char)temp;
		}
		check_dec_msg(dec);
	}
	public static void check_dec_msg(String s) {
		int e=0,t=0,a=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='E')
				e++;
			else if(s.charAt(i)=='T')
				t++;
			else if(s.charAt(i)=='A')
				a++;	
		}
		float ep,tp,ap;
		float len=s.length();
		float e1=e,t1=t,a1=a;
		ep=(e1/len)*100;
		tp=(t1/len)*100;
		ap=(a1/len)*100;
		if(ep>=11.0&&ep<=14.0)
			System.out.println(s);
		else if(tp>=8.0&&tp<=10.5)
			System.out.println(s);
		else if(ap>=7.0&&ap<=9.5)
			System.out.println(s);
		
		
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String s=input.next();
		for(int k=0;k<26;k++) {
			decrypt(s,k);
		}
	}
}
		