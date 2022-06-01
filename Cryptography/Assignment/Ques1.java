import java.util.Scanner;
class Ques1 {
	static int dia[]=new int[26];	//TO STORE THE NUMBER OF DIAGRAMS EACH DECRYPTED PASSED
	static int tria[]=new int[26];	//TO STORE THE NUMBER OF TRIAGRAMS EACH DECRYPTED PASSED
	public static void decrypt(String s,int key,boolean b) {	//TO DECRYPT THE STRING WITH A KEY
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
		if(!b)
			check_Letter_freq(dec,key);
		else
			System.out.println(dec);
	}
	public static void check_Letter_freq(String S,int key) {		//TO CHECK FREQURNCY OF ALPHABETS
		float e,t,a,o,i,n,s,len1;
		int len;
		e=t=a=o=i=n=s=0;
		len=S.length();
		len1=(float)len;
		for(int j=0;j<len;j++) {
			switch(S.charAt(j)) {
				case 'E':e++;break;
				case 'T':t++;break;
				case 'A':a++;break;
				case 'O':o++;break;
				case 'I':i++;break;
				case 'N':n++;break;
				case 'S':s++;break;
			}
		}
		float ep,tp,ap,op,ip,np,sp;
		ep=(e/len)*100;
		tp=(t/len)*100;
		ap=(a/len)*100;
		op=(o/len)*100;
		ip=(i/len)*100;
		np=(n/len)*100;
		sp=(s/len)*100;
		if((ep>=10.0)||(tp>=7.5&&tp<=10.5)||(ap>=6.5&&tp<=9.5)||(op>=6.0&&op<=8.5)||(ip>=5.5&&ip<=8.5)||(np>5.0&&np<=8.0)||(sp>=4.5&&sp<=7.5)) {
			dia[key]=check_diagrams(S);
			tria[key]=check_triagrams(S);
		}
	}
	public static int check_diagrams(String s) {	//TO CHECK DIAGRAMS
		int points=0;
		String temp[]={"TH","HE","IN","ER","AN","RE","ED","ON","ES","ST","EN","AT","TO","NT","HA","ND","OU","EA","NG","AS","OR","TI","IS","ET","IT","AR","TE","SE","HI","OF"};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points++;
		}
		return points;
	}
	public static int check_triagrams(String s) {	// TO CHECK TRIAGRAMS
		int points=0;
		String temp[]={"THE","ING","AND","HER","ERE","THA","NTH","WAS","ETH","FOR","DTH"};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points++;
		}
		return points;
		
	}
	public static int find_max() {	//TO FIND WHICH DECRYPTED MESSAGE PASSES MAX NUMBER OF DIAGRAMS AND TRIAGRAMS
		int max=0,indx=-1;
		for(int i=0;i<26;i++) {
			if(dia[i]+tria[i]>max) {
				max=dia[i]+tria[i];
				indx=i;
			}
		}
		if(indx==-1) {
			return -1;
		}
		dia[indx]=tria[indx]=-1;
		return indx;
	}	
	public static void main(String args[]) {
		System.out.println("PLEASE ENTER THE STRING IN CAPITAL LETTERS\n\n");
		Scanner input=new Scanner(System.in);
		for(int i=0;i<=25;i++) {
			dia[i]=-1;tria[i]=-1;
		}
		String s=input.next();
		for(int k=0;k<26;k++) {
			decrypt(s,k,false);
		}
		System.out.println("Most probable results:-\n\n");
		for(int i=1;i<=10;i++) {
			System.out.print(i+"  ");
			int kk=find_max();
			if(kk!=-1) {
				decrypt(s,kk,true);
			}
			else {
				break;
			}
			System.out.println();
		}
	}
}
		