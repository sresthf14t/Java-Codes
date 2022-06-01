/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;

/**
 *
 * @author User
 */
import java.util.*;
public class AES {
    	static int s[][]={{0x63 ,0x7c ,0x77 ,0x7b ,0xf2 ,0x6b ,0x6f ,0xc5 ,0x30 ,0x01 ,0x67 ,0x2b ,0xfe ,0xd7 ,0xab ,0x76}
					,{0xca ,0x82 ,0xc9 ,0x7d ,0xfa ,0x59 ,0x47 ,0xf0 ,0xad ,0xd4 ,0xa2 ,0xaf ,0x9c ,0xa4 ,0x72 ,0xc0}
					 ,{0xb7 ,0xfd ,0x93 ,0x26 ,0x36 ,0x3f ,0xf7 ,0xcc ,0x34 ,0xa5 ,0xe5 ,0xf1 ,0x71 ,0xd8 ,0x31 ,0x15}
					 ,{0x04 ,0xc7 ,0x23 ,0xc3 ,0x18 ,0x96 ,0x05 ,0x9a ,0x07 ,0x12 ,0x80 ,0xe2 ,0xeb ,0x27 ,0xb2 ,0x75}
					 ,{0x09 ,0x83 ,0x2c ,0x1a ,0x1b ,0x6e ,0x5a ,0xa0 ,0x52 ,0x3b ,0xd6 ,0xb3 ,0x29 ,0xe3 ,0x2f ,0x84}
					 ,{0x53 ,0xd1 ,0x00 ,0xed ,0x20 ,0xfc ,0xb1 ,0x5b ,0x6a ,0xcb ,0xbe ,0x39 ,0x4a ,0x4c ,0x58 ,0xcf}
					 ,{0xd0 ,0xef ,0xaa ,0xfb ,0x43 ,0x4d ,0x33 ,0x85 ,0x45 ,0xf9 ,0x02 ,0x7f ,0x50 ,0x3c ,0x9f ,0xa8}
					 ,{0x51 ,0xa3 ,0x40 ,0x8f ,0x92 ,0x9d ,0x38 ,0xf5 ,0xbc ,0xb6 ,0xda ,0x21 ,0x10 ,0xff ,0xf3 ,0xd2}
					 ,{0xcd ,0x0c ,0x13 ,0xec ,0x5f ,0x97 ,0x44 ,0x17 ,0xc4 ,0xa7 ,0x7e ,0x3d ,0x64 ,0x5d ,0x19 ,0x73}
					 ,{0x60 ,0x81 ,0x4f ,0xdc ,0x22 ,0x2a ,0x90 ,0x88 ,0x46 ,0xee ,0xb8 ,0x14 ,0xde ,0x5e ,0x0b ,0xdb}
					 ,{0xe0 ,0x32 ,0x3a ,0x0a ,0x49 ,0x06 ,0x24 ,0x5c ,0xc2 ,0xd3 ,0xac ,0x62 ,0x91 ,0x95 ,0xe4 ,0x79}
					 ,{0xe7 ,0xc8 ,0x37 ,0x6d ,0x8d ,0xd5 ,0x4e ,0xa9 ,0x6c ,0x56 ,0xf4 ,0xea ,0x65 ,0x7a ,0xae ,0x08}
					 ,{0xba ,0x78 ,0x25 ,0x2e ,0x1c ,0xa6 ,0xb4 ,0xc6 ,0xe8 ,0xdd ,0x74 ,0x1f ,0x4b ,0xbd ,0x8b ,0x8a}
					 ,{0x70 ,0x3e ,0xb5 ,0x66 ,0x48 ,0x03 ,0xf6 ,0x0e ,0x61 ,0x35 ,0x57 ,0xb9 ,0x86 ,0xc1 ,0x1d ,0x9e}
					 ,{0xe1 ,0xf8 ,0x98 ,0x11 ,0x69 ,0xd9 ,0x8e ,0x94 ,0x9b ,0x1e ,0x87 ,0xe9 ,0xce ,0x55 ,0x28 ,0xdf}
					 ,{0x8c ,0xa1 ,0x89 ,0x0d ,0xbf ,0xe6 ,0x42 ,0x68 ,0x41 ,0x99 ,0x2d ,0x0f ,0xb0 ,0x54 ,0xbb ,0x16}};
	static int inv_s[][] = {{0x52 ,0x09 ,0x6a ,0xd5 ,0x30 ,0x36 ,0xa5 ,0x38 ,0xbf ,0x40 ,0xa3 ,0x9e ,0x81 ,0xf3 ,0xd7 ,0xfb}
						,{0x7c ,0xe3 ,0x39 ,0x82 ,0x9b ,0x2f ,0xff ,0x87 ,0x34 ,0x8e ,0x43 ,0x44 ,0xc4 ,0xde ,0xe9 ,0xcb}
						,{0x54 ,0x7b ,0x94 ,0x32 ,0xa6 ,0xc2 ,0x23 ,0x3d ,0xee ,0x4c ,0x95 ,0x0b ,0x42 ,0xfa ,0xc3 ,0x4e}
						,{0x08 ,0x2e ,0xa1 ,0x66 ,0x28 ,0xd9 ,0x24 ,0xb2 ,0x76 ,0x5b ,0xa2 ,0x49 ,0x6d ,0x8b ,0xd1 ,0x25}
						,{0x72 ,0xf8 ,0xf6 ,0x64 ,0x86 ,0x68 ,0x98 ,0x16 ,0xd4 ,0xa4 ,0x5c ,0xcc ,0x5d ,0x65 ,0xb6 ,0x92}
						,{0x6c ,0x70 ,0x48 ,0x50 ,0xfd ,0xed ,0xb9 ,0xda ,0x5e ,0x15 ,0x46 ,0x57 ,0xa7 ,0x8d ,0x9d ,0x84}
						,{0x90 ,0xd8 ,0xab ,0x00 ,0x8c ,0xbc ,0xd3 ,0x0a ,0xf7 ,0xe4 ,0x58 ,0x05 ,0xb8 ,0xb3 ,0x45 ,0x06}
						,{0xd0 ,0x2c ,0x1e ,0x8f ,0xca ,0x3f ,0x0f ,0x02 ,0xc1 ,0xaf ,0xbd ,0x03 ,0x01 ,0x13 ,0x8a ,0x6b}
						,{0x3a ,0x91 ,0x11 ,0x41 ,0x4f ,0x67 ,0xdc ,0xea ,0x97 ,0xf2 ,0xcf ,0xce ,0xf0 ,0xb4 ,0xe6 ,0x73}
						,{0x96 ,0xac ,0x74 ,0x22 ,0xe7 ,0xad ,0x35 ,0x85 ,0xe2 ,0xf9 ,0x37 ,0xe8 ,0x1c ,0x75 ,0xdf ,0x6e}
						,{0x47 ,0xf1 ,0x1a ,0x71 ,0x1d ,0x29 ,0xc5 ,0x89 ,0x6f ,0xb7 ,0x62 ,0x0e ,0xaa ,0x18 ,0xbe ,0x1b}
						,{0xfc ,0x56 ,0x3e ,0x4b ,0xc6 ,0xd2 ,0x79 ,0x20 ,0x9a ,0xdb ,0xc0 ,0xfe ,0x78 ,0xcd ,0x5a ,0xf4}
						,{0x1f ,0xdd ,0xa8 ,0x33 ,0x88 ,0x07 ,0xc7 ,0x31 ,0xb1 ,0x12 ,0x10 ,0x59 ,0x27 ,0x80 ,0xec ,0x5f}
						,{0x60 ,0x51 ,0x7f ,0xa9 ,0x19 ,0xb5 ,0x4a ,0x0d ,0x2d ,0xe5 ,0x7a ,0x9f ,0x93 ,0xc9 ,0x9c ,0xef}
						,{0xa0 ,0xe0 ,0x3b ,0x4d ,0xae ,0x2a ,0xf5 ,0xb0 ,0xc8 ,0xeb ,0xbb ,0x3c ,0x83 ,0x53 ,0x99 ,0x61}
						,{0x17 ,0x2b ,0x04 ,0x7e ,0xba ,0x77 ,0xd6 ,0x26 ,0xe1 ,0x69 ,0x14 ,0x63 ,0x55 ,0x21 ,0x0c ,0x7d}};
	static String MixColumn[][]= {{"02","03","01","01"},
									{"01","02","03","01"},
									{"01","01","02","03"},
									{"03","01","01","02"}};
	static String InvMixColumn[][]= {{"0E","0B","0D","09"},
									{"09","0E","0B","0D"},
									{"0D","09","0E","0B"},
									{"0B","0D","09","0E"}};
	static String dtb(int x) {								//Decimal to binary 8 bit representation
		String s="";
		while(x!=0) {
			s=x%2+s;
			x=x/2;
		}
		int i=s.length();
		if(s.length()%4!=0) {
			i=i+4-(s.length()%4);
		}
		while((s.length()!=i)) {
			s=0+s;
		}
		return s;
	}
	static String[][] dtba2(int x[][]){								//Decimal 2-D array to binary string array representation
		String s[][]=new String[x.length][x[0].length];
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<x[i].length;j++) {
				s[i][j]=dtb(x[i][j]);
			}
		}
		return s;
	}
	static String[] dtba1(int x[]){								//Decimal 1-D array to binary string array representation
		String s[]=new String[x.length];
		for(int i=0;i<x.length;i++) {
			s[i]=dtb(x[i]);
		}
		return s;
	}
	static String[][] btHa1(String x[][]){								
		String s[][]=new String[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++){
				s[i][j]="";
				s[i][j]=s[i][j]+Convert_Hex_to_Bin(x[i][j]);}
		}
		return s;
	}
	static String MixColumnMatrix[][]=btHa1(MixColumn);
	static String InvMixColumnMatrix[][]=btHa1(InvMixColumn);
	static String Sbox[][]=dtba2(s);
	static String InvS_box[][]=dtba2(inv_s);
	static String SubBytes(String b) {
		while(b.length()<8) {
			b=0+b;
		}
		int j[]=new int[2]; 
		for(int k=0;k<2;k++) {
			int f=8;
			for(int y=0;y<=3;y++) {
				if(b.charAt((k*4)+y)=='1') {
					j[k]+=f;
				}
				f=f/2;
			}
		}
		b=Sbox[j[0]][j[1]];
		while(b.length()<8) {
			b=0+b;
		}
		return b;
	}
	static String InvSubBytes(String b) {
		while(b.length()<8) {
			b=0+b;
		}
		int j[]=new int[2]; 
		for(int k=0;k<2;k++) {
			int f=8;
			for(int y=0;y<=3;y++) {
				if(b.charAt((k*4)+y)=='1') {
					j[k]+=f;
				}
				f=f/2;
			}
		}
		b=InvS_box[j[0]][j[1]];
		while(b.length()<8) {
			b=0+b;
		}
		return b;
	}
	static int Rcj[]= {0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1b,0x36};
	static String RConj[]=dtba1(Rcj);
	static String XOR(String a,String b){
		String s="";
		for(int p=0;p<a.length();p++) {
			if(a.charAt(p)==b.charAt(p)) {
				s=s+'0';
			}
			else {
				s=s+'1';
			}
		}
		return s;
	}
	static String leftShift(String a) {
		String s="";
		for(int i=1;i<a.length();i++) {
			s=s+a.charAt(i);
		}
		s=s+0;
		return s;
	}
	static String[] f1(String a,String key) {
		String y="";
		for(int j=1;j<key.length();j++) {
			y=y+key.charAt(j);
		}
		key=y;
		String[] p=new String[8];
		for(int i=p.length-1;i>=0;i--) {
			if(i==(p.length-1)) {
				p[i]=a; 
			}
			else {
				p[i]=leftShift(p[i+1]);
			}
			if(i!=(p.length-1)&&p[i+1].charAt(0)=='1') {
				p[i]=XOR(p[i],key);
			}
		}
		return p;
	}
	static String mul(String b,String f[]) {
		String l="00000000";
		for(int i=0;i<b.length();i++) {
			if(b.charAt(i)=='1') {
				l=XOR(l,f[i]);
			}
		}
		return l;
	}
	static String f(String w,String RCon) {
		String b[]=new String[4];
		b[0]="";
		b[1]="";
		b[2]="";
		b[3]="";
		String b1="";
		for(int i=0;i<w.length();i++) {
			b[i/8]=b[i/8]+w.charAt(i);
		}
		for(int i=0;i<4;i++) {
			int j[]=new int[2];
			j[0]=j[1]=0;
			int f=0;
			for(int k=0;k<2;k++) {
				f=8;
				for(int y=0;y<=3;y++) {
					if(b[i].charAt((k*4)+y)=='1') {
						j[k]+=f;
					}
					f=f/2;
				}
			}
			b[i]=Sbox[j[0]][j[1]];
			while(b[i].length()<8) {
				b[i]=0+b[i];
			}
		}
		while(RCon.length()<8) {
			RCon=0+RCon;
		}
		b1=b1+XOR(b[1],RCon)+b[2]+b[3]+b[0];
		return b1;
	}
	static String Convert_Bin_to_Hex(String in) {       //Convert Binary to Hexadecimal
        String out="";
        for(int i=0;i<in.length();i=i+4) {
            String temp=""+in.charAt(i)+in.charAt(i+1)+in.charAt(i+2)+in.charAt(i+3);
            if(temp.equals("0000")) {
                out+="0";
            }
            else if(temp.equals("0001")) {
                out+="1";
            }
            else if(temp.equals("0010")) {
                out+="2";
            }
            else if(temp.equals("0011")) {
                out+="3";
            }
            else if(temp.equals("0100")) {
                out+="4";
            }
            else if(temp.equals("0101")) {
                out+="5";
            }
            else if(temp.equals("0110")) {
                out+="6";
            }
            else if(temp.equals("0111")) {
                out+="7";
            }
            else if(temp.equals("1000")) {
                out+="8";
            }
            else if(temp.equals("1001")) {
                out+="9";
            }
            else if(temp.equals("1010")) {
                out+="A";
            }
            else if(temp.equals("1011")) {
                out+="B";
            }
            else if(temp.equals("1100")) {
                out+="C";
            }
            else if(temp.equals("1101")) {
                out+="D";
            }
            else if(temp.equals("1110")) {
                out+="E";
            }
            else if(temp.equals("1111")) {
                out+="F";
            }
        }
        return out;
    }
	static String Convert_Hex_to_Bin(String in) {      //Convert Hexadecimal to Binary
        String out="";
        for(int i=0;i<in.length();i++) {
            if(in.charAt(i)=='0') {
                out+="0000";
            }
            else if(in.charAt(i)=='1') {
                out+="0001";
            }
            else if(in.charAt(i)=='2') {
                out+="0010";
            }
            else if(in.charAt(i)=='3') {
                out+="0011";
            }
            else if(in.charAt(i)=='4') {
                out+="0100";
            }
            else if(in.charAt(i)=='5') {
                out+="0101";
            }
            else if(in.charAt(i)=='6') {
                out+="0110";
            }
            else if(in.charAt(i)=='7') {
                out+="0111";
            }
            else if(in.charAt(i)=='8') {
                out+="1000";
            }
            else if(in.charAt(i)=='9') {
                out+="1001";
            }
            else if(in.charAt(i)=='A') {
                out+="1010";
            }
            else if(in.charAt(i)=='B') {
                out+="1011";
            }
            else if(in.charAt(i)=='C') {
                out+="1100";
            }
            else if(in.charAt(i)=='D') {
                out+="1101";
            }
            else if(in.charAt(i)=='E') {
                out+="1110";
            }
            else if(in.charAt(i)=='F') {
                out+="1111";
            }  
        }
        return out;
    }
	public static String[] keyGenerator(String K) {
			K=Convert_Hex_to_Bin(K);
			String w[]=new String[44];
			for(int i=0;i<44;i++) {
				w[i]="";
			}
			String K1[]=new String[11];
			for(int i=0;i<K.length();i++) {
				w[i/32]=w[i/32]+K.charAt(i);
			}
			for(int i=4;i<44;i++) {
				if(i%4==0) {
					w[i]=XOR(w[i-4],f(w[i-1],RConj[(i/4)-1]));
				}
				else {
					w[i]=XOR(w[i-1],w[i-4]);
				}
			}
			for(int i=0;i<w.length;i++) {
				K1[i/4]=K1[i/4]+w[i];
			}
			for(int i=0;i<11;i++) {
				K1[i]=Convert_Bin_to_Hex(K1[i]);
			}
			return K1;
		}
	static String[][] ShiftRows(String r[][]){
		String b[][]=new String[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				b[i][j]=r[i][(j+i)%4];
			}
		}
		return b;
	}
	static String[][] InvShiftRows(String r[][]){
		String b[][]=new String[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				int f=(j+4-i);
				b[i][j]=r[i][f%4];
			}
		}
		return b;
	}
	static String[][] MatMulti(String r[][],String M[][]){
		String P[][]=new String[4][4];
		String Key="100011011";
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				P[i][j]="00000000";
			}
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<4;k++) {
					String O[]=f1(r[i][k],Key);
					String L=mul(M[k][j],O);
					P[i][j]=XOR(P[i][j],L);
				}
			}
		}
		return P;
	}
	static String[][] TextToState(String s){
		String P[][]=new String[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				P[i][j]="";
			}
		}
		for(int i=0;i<s.length();i++) {
			P[(i-(i/8)*8)/2][i/8]=P[(i-(i/8)*8)/2][i/8]+s.charAt(i);
		}
		P=btHa1(P);
		return P;
	}
	static String StateToText(String s[][]){
		String P="";
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				P=P+s[j][i];
			}
		}
		return P;
	}
	static void Encryption(String PT,String Key) {
		String F[]=keyGenerator(Key);
		PT=Convert_Hex_to_Bin(PT);
		F[0]=Convert_Hex_to_Bin(F[0]);
		PT=XOR(PT,F[0]);
		PT=Convert_Bin_to_Hex(PT);
		String Y[][];
		for(int i=0;i<10;i++) {
			Y=TextToState(PT);
			for(int l=0;l<4;l++) {
				for(int m=0;m<4;m++) {
					Y[l][m]=SubBytes(Y[l][m]);
				}
			}
			Y=ShiftRows(Y);
			if(i!=9) {
				Y=MatMulti(MixColumnMatrix,Y);
			}
			PT=StateToText(Y);
			F[i+1]=Convert_Hex_to_Bin(F[i+1]);
			PT=XOR(PT,F[i+1]);
			PT=Convert_Bin_to_Hex(PT);
		}
		System.out.println(PT);
	}
	static void Decryption(String PT,String Key) {
		String F[]=keyGenerator(Key);
		PT=Convert_Hex_to_Bin(PT);
		F[10]=Convert_Hex_to_Bin(F[10]);
		PT=XOR(PT,F[10]);
		PT=Convert_Bin_to_Hex(PT);
		String Y[][];
		for(int i=0;i<10;i++) {
			Y=TextToState(PT);
			for(int l=0;l<4;l++) {
				for(int m=0;m<4;m++) {
					Y[l][m]=InvSubBytes(Y[l][m]);
				}
			}
			Y=InvShiftRows(Y);
			PT=StateToText(Y);
			F[9-i]=Convert_Hex_to_Bin(F[9-i]);
			PT=XOR(PT,F[9-i]);
			PT=Convert_Bin_to_Hex(PT);
			Y=TextToState(PT);
			if(i!=9) {
				Y=MatMulti(InvMixColumnMatrix,Y);
			}
			PT=StateToText(Y);
			PT=Convert_Bin_to_Hex(PT);
		}
		System.out.println(PT);
	}
	public static void main(String Args[]) {
		Scanner s=new Scanner(System.in);
		System.out.print("Enter the hexadecimal text u want to Encrypt or Decrypt : ");
		String PT=s.next();
		PT=PT.toUpperCase();
		System.out.print("\nEnter the hexadecimal key: ");
		String Key=s.next();
		Key=Key.toUpperCase();
		System.out.print("\n1.Encrypt\n2.Decrypt\nEnter your choice:");
		int l2=s.nextInt();
		switch(l2) {
			case 1:System.out.print("\nThe encrypted hexadecimal text is:");Encryption(PT,Key);break;
			case 2:System.out.print("\nThe decrypted hexadecimal text is:");Decryption(PT,Key);break;
		}
	}
}
