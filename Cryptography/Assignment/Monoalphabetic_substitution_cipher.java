// CODE TO BREAK ADDITIVE , MULTIPLICATIVE AND AFFIE CIPHERS BY LETTER FREQUENCY ANALYSYS
// PLEASE GIVE THE ENCRYPTED MESSAGE IN CAPITAL LETTERS ONLY
import java.util.Scanner;
class Monoalphabetic_substitution_cipher {
	static int t1=0,t2=0;
	static int dia[][]=new int[26][26];  // TO FIND NUMBER OF DIAGRAMS IN THE DECRYPTED STRING
	static int tria[][]=new int[26][26]; // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
	static int mul_inv[]={1,3,5,7,9,11,15,17,19,21,23,25};  //MULTIPLICATIVE INVERSES IN MOD 26
	static float ep,tp,ap,op,ip,np,sp; // TO STORE FREQUENCY OF EACH OF THE APLHABETS
	public static String Add__cipher_inv(String s,int key) { //TO DECRYPT ADDITIVE CIPHER
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
		return dec;
	}
	public static String Multplicative_cipher_inv(String s,int key) { //TO DECRYPT MULTIPLICATIVE CIPHER
		String dec="";
		for(int j=0;j<s.length();j++) {
			int temp=(int)s.charAt(j);
			temp-=65;
			temp*=key;
			temp%=26;
			if(temp<0)
				temp+=26;
			temp+=65;
			dec+=(char)temp;
		}
		return dec;
	}
	public static void check_Letter_freq(String S) {		//TO CHECK FREQURNCY OF ALPHABETS
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
		ep=(e/len)*100;
		tp=(t/len)*100;
		ap=(a/len)*100;
		op=(o/len)*100;
		ip=(i/len)*100;
		np=(n/len)*100;
		sp=(s/len)*100;
	}
	public static String Affine_cipher_inv(String s, int key1, int key2,boolean b) { //TO DECRYPT AFFINE CIPHER
		String dec=Multplicative_cipher_inv(Add__cipher_inv(s,key1),key2);
		if(b) //TO ONLY FIND DECRYPTED STRING
			return dec;
		check_Letter_freq(dec);  														//TO CHECK FREQURNCY OF ALPHABETS
		if((ep>=10.0)||(tp>=7.5&&tp<=10.5)||(ap>=6.5&&tp<=9.5)||(op>=6.0&&op<=8.5)||(ip>=5.5&&ip<=8.5)||(np>5.0&&np<=8.0)||(sp>=4.5&&sp<=7.5)) {
			dia[key1][key2]=check_diagrams(dec); 												// TO FIND NUMBER OF DIAGRAMS IN THE DECRYPTED STRING
			tria[key1][key2]=check_triagrams(dec);											       // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
		}
		return dec;
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
	public static int[] find_max() {	//TO FIND WHICH DECRYPTED MESSAGE PASSES MAX NUMBER OF DIAGRAMS AND TRIAGRAMS
		int max=0;
		int indx[]=new int[2];
		indx[0]=indx[1]=-1;
		for(int i=0;i<26;i++) {
			for(int j=0;j<mul_inv.length;j++) {
				if(dia[i][mul_inv[j]]+tria[i][mul_inv[j]]>max) {
					max=dia[i][mul_inv[j]]+tria[i][mul_inv[j]];
					indx[0]=i;indx[1]=mul_inv[j];
				}
			}
		}
		t1=dia[indx[0]][indx[1]];
		t2=tria[indx[0]][indx[1]];
		dia[indx[0]][indx[1]]=tria[indx[0]][indx[1]]=-1;	//REPLACES THE CURRENT MAX WITH -1 TO FIND THE NEXT MAX
		return indx;						//RETURNS THE INDEX OF CURRENT MAX
	}	
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		for(int i=0;i<26;i++) {
			for(int j=0;j<26;j++) {
				dia[i][j]=tria[i][j]=-1;
			}
		}
		System.out.println("Enter the encrypted String[In capital letters only]:-");
		String s=input.next();
		for(int i=0;i<26;i++) {
			for(int j=0;j<mul_inv.length;j++) {
				Affine_cipher_inv(s,i,mul_inv[j],false);
			}
		}	
		for(int i=1;i<=50;i++) {
			int key1[]=new int[2];
			key1=find_max();
			System.out.println(i+"\t"+Affine_cipher_inv(s,key1[0],key1[1],true)+"--->"+t1+"--->"+t2);
		}
	}
}
