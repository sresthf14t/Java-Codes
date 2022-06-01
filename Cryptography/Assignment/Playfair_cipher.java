
// CODE TO ENCRYPT AND DECYPT USING PLAYFAIR CIPHER
// PLEASE USE ONLY CAPITAL LETTERS

import java.util.Scanner;
class Playfair_cipher {
	public static char[][] create_cipher(String s) {
		char key[][]=new char[5][5]; 
		String NotInKey="";
		for(char i='A';i<='Z';i++) {
			if(!s.contains(""+i))
				NotInKey+=""+i;
		}
		s=s+NotInKey;
		String temp="";
		boolean b=false;
		for(int i=s.length()-1;i>=0;i--) {
			if(!b) {
				if(s.charAt(i)=='I'||s.charAt(i)=='J')  {
					b=true;
				}
				else {
					temp=""+s.charAt(i)+temp;
				}
			}
			else {
				temp=""+s.charAt(i)+temp;
			}
		}
		int count=0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				key[i][j]=temp.charAt(count);
				count++;	
			}
		}
		return key;
	}
	public static String encipher(String s,char key[][]) {			//TO ENCIPHER PLAINTEXT
		String cipher="";
		char c1,c2;							//TO STORE THE TWO CHARS TO BE ENCIPHERED TOGETHER
		for(int I=0;I<s.length();I++) {					//TO CHECK FOR REPETATION OF CHARECTERS AND APPEND 'Z' IF NEEDED	
			if((s.length()!=1)&&(s.charAt(I)!=s.charAt(I+1))) {
				c1=s.charAt(I);
				c2=s.charAt(I+1);
				I++;
			}
			else {
				c1=s.charAt(I);
				c2='Z';
			}
			int c1_pos[]=new int[2];				// TO STORE POS OF C1 IN KEY MATRIX
			int c2_pos[]=new int[2];				// TO STORE POS OF C2 IN KEY MATRIX
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(key[i][j]==c1) {
						c1_pos[0]=i;
						c1_pos[1]=j;
					}
					if(key[i][j]==c2) {
						c2_pos[0]=i;
						c2_pos[1]=j;
					}	
				}
			}
			if(c1_pos[0]==c2_pos[0]) {				//TO ENCRYPT USING KEY MATRIX
				cipher+=key[c1_pos[0]][(c1_pos[1]+1)%5];
				cipher+=key[c2_pos[0]][(c2_pos[1]+1)%5];
			}
			else if(c1_pos[1]==c2_pos[1]) {
				cipher+=key[(c1_pos[0]+1)%5][c1_pos[1]];
				cipher+=key[(c2_pos[0]+1)%5][c2_pos[1]];
			}
			else {
				cipher+=key[c1_pos[0]][c2_pos[1]];
				cipher+=key[c2_pos[0]][c1_pos[1]];
			}
		}
		return cipher;
	}
	public static String decipher(String s,char key[][]) {			//TO DECIPHER CIPHERTEXT ALMOST SAME METHORD AS ENCIPHER
		String cipher="";
		char c1,c2;							//TO STORE THE TWO CHARS TO BE DECIPHERED TOGETHER
		for(int I=0;I<s.length();I=I+2) {
			c1=s.charAt(I);						
			c2=s.charAt(I+1);
			int c1_pos[]=new int[2];				// TO STORE POS OF C1 IN KEY MATRIX
			int c2_pos[]=new int[2];				// TO STORE POS OF C2 IN KEY MATRIX
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(key[i][j]==c1) {
						c1_pos[0]=i;
						c1_pos[1]=j;
					}
					if(key[i][j]==c2) {
						c2_pos[0]=i;
						c2_pos[1]=j;
					}	
				}
			}
			if(c1_pos[0]==c2_pos[0]) {				//TO DECRYPT USING KEY MATRIX
				cipher+=key[c1_pos[0]][((c1_pos[1]+4)%5)];
				cipher+=key[c2_pos[0]][((c2_pos[1]+4)%5)];
			}
			else if(c1_pos[1]==c2_pos[1]) {
				cipher+=key[((c1_pos[0]+4)%5)][c1_pos[1]];
				cipher+=key[((c2_pos[0]+4)%5)][c2_pos[1]];
			}
			else {
				cipher+=key[c1_pos[0]][c2_pos[1]];
				cipher+=key[c2_pos[0]][c1_pos[1]];
			}
		}
		return cipher;
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("PLEASE USE ONLY CAPITAL LETTERS");
		System.out.println("Enter the Key");		
		char[][] key=create_cipher(input.next());
		System.out.println("Enter the String");
		String en=encipher(input.next(),key);
		System.out.println("Enciphered String="+en);
		System.out.println("Deciphered String="+decipher(en,key));
	}
}