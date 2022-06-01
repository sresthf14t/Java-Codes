// CODE TO BREAK ADDITIVE , MULTIPLICATIVE AND AFFIE CIPHERS BY LETTER FREQUENCY ANALYSYS
// PLEASE GIVE THE ENCRYPTED MESSAGE IN CAPITAL LETTERS ONLY
import java.util.Scanner;
class Monoalphabetic_substitution_cipher_V3 {
	static int dia[][]=new int[26][26];  // TO FIND NUMBER OF DIAGRAMS IN THE DECRYPTED STRING
	static int tria[][]=new int[26][26]; // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
	static int quad[][]=new int[26][26]; // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
	static int quint[][]=new int[26][26]; // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
	static int mul_inv[]={1,3,5,7,9,11,15,17,19,21,23,25};  //MULTIPLICATIVE INVERSES IN MOD 26
	static float ep,tp,ap,op,ip,np,sp; // TO STORE FREQUENCY OF EACH OF THE APLHABETS
	static int t2=0,t3=0,t4=0,t5=0;
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
			return dec;														//TO CHECK FREQURNCY OF ALPHABETS
			dia[key1][key2]=check_diagrams(dec); 												// TO FIND NUMBER OF DIAGRAMS IN THE DECRYPTED STRING
			tria[key1][key2]=check_triagrams(dec);											       // TO FIND NUMBER OF TRIAGRAMS IN THE DECRYPTED STRING
			quad[key1][key2]=check_quadgrams(dec);											       // TO FIND NUMBER OF QUADAGRAMS IN THE DECRYPTED STRING
			quint[key1][key2]=check_quintgrams(dec);											       // TO FIND NUMBER OF QUINTGRAMS IN THE DECRYPTED STRING		
		return dec;
	}
	public static int check_diagrams(String s) {	//TO CHECK DIAGRAMS
		int points=0;
		String temp[]={"TH","HE","IN","ER","AN","RE","ES","ON","ST","NT","EN","AT","ED","ND","TO","OR","EA","TI","AR","TE","NG","AL","IT","AS","IS","HA","ET","SE","OU","OF","LE","SA","VE","RO","RA","RI","HI","NE","ME","DE","CO","TA","EC","SI","LL","SO","NA","LI","LA","EL","MA","DI","IC","RT","NS","RS","IO","OM","CH","OT","CA","CE","HO","BE","TT","FO","TS","SS","NO","EE","EM","AC","IL","DA","NI","UR","WA","SH","EI","AM","TR","DT","US","LO","PE","UN","NC","WI","UT","AD","EW","OW","GE","EP","AI","LY","OL","FT","OS","EO","EF","PR","WE","DO","MO","ID","IE","MI","PA","FI","PO","CT","WH","IR","AY","GA","SC","KE","EV","SP","IM","OP","DS","LD","UL","OO","SU","IA","GH","PL","EB","IG","VI","IV","WO","YO","RD","TW","BA","AG","RY","AB","LS","SW","AP","FE","TU","CI","FA","HT","FR","AV","EG","GO","BO","BU","TY","MP","OC","OD","EH","YS","EY","RM","OV","GT","YA","CK","GI","RN","GR","RC","BL","LT","YT","OA","YE","OB","DB","FF","SF","RR","DU","KI","UC","IF","AF","DR","CL","EX","SM","PI","SB","CR","TL","OI","RU","UP","BY","TC","NN","AK","SL","NF","UE","DW","AU","PP","UG","RL","RG","BR","CU","UA","DH","RK","YI","LU","UM","BI","NY","NW","QU","OG","SN","MB","VA","DF","DD","MS","GS","AW","NH","PU","HR","SD","TB","PT","NM","DC","GU","TM","MU","NU","MM","NL","EU"};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points++;
		}
		return points;
	}
	public static int check_triagrams(String s) {	// TO CHECK TRIAGRAMS
		int points=0;
		String temp[]={"THE","AND","ING","ENT","ION","HER","FOR","THA","NTH","INT","ERE","TIO","TER","EST","ERS","ATI","HAT","ATE","ALL","ETH","HES","VER","HIS","OFT","ITH","FTH","STH","OTH","RES","ONT","DTH","ARE","REA","EAR","WAS","SIN","STO","TTH","STA","THI","TIN","TED","ONS","EDT","WIT","SAN","DIN","ORT","CON","RTH","EVE","ECO","ERA","IST","NGT","AST","ILL","COM","ORE","IVE","NCE","ONE","EDI","PRO","ESS","OUT","EIN","ATT","MEN","HEC","ESA","HEN","INA","ERI","ERT","AME","ITI","OME","SON","ART","MAN","EAN","ONA","EOF","TOR","HEA","RAN","RIN","INE","EDA","NTO","AVE","NIN","OVE","OUN","AIN","ANT","STR","ETO","HEM","SOF","PER","NDE","STE","NTE","EAS","DTO","OUR","RED","ROM","TOF","GHT","TOT","ESE","CHA","ICA","HEI","HIN","IDE","NDT","HAN","TAN","LIN","NOT","DER","ECT","TRA","IGH","FRO","EAT","STI","HEP","NDI","INS","SHE","NAL","PLA","ALS","EEN","NTI","YOU","LAN","UND","NDA","RAT","LEA","CAN","HAS","NDS","NGA","HEL","HED","INC","USE","ESI","GTH","ASA","HET","NTS","HAV","HEW","THO","BUT","NAN","ASS","HEF","IES","RET","END","PAR","WER","CTI","REN","REC","CAL","ITS","REE","ENE","RST","EAL","ANA","NST","COU","TUR","MIN","ITY","YTH","HEY","ECA","OUL","LLE","ARD","ROU","ANC","OST","PRE","AGE","EFO","LES","SSI","EMA","ESO","TAT","ATH","WOR","UST","HEB","EWA","SHO","IND","SED","HOU","LLY","ULD","ASE","URE","ONO","ELE","ENC","NAT","EAD","WHE","ELL","BLE","KIN","ANS","TIC","ALI","SCO","ERO","WHI","CES","OWN","NTA","ACT","BER","VEN","TIM","DON","DAN","OSE","ICE","ISA","TON","DEN","NGS","UGH","NES","LAT",};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points+=2;
		}
		return points;
		
	}
	public static int check_quadgrams(String s) {	// TO CHECK QUADGRAMS
		int points=0;
		String temp[]={"TION","NTHE","THER","THAT","OFTH","FTHE","THES","WITH","INTH","ATIO","OTHE","TTHE","DTHE","INGT","ETHE","SAND","STHE","HERE","THEC","MENT","THEM","RTHE","THEP","FROM","THIS","TING","THEI","NGTH","IONS","ANDT","ONTH","TOTH","EDTO","THEF","THEY","HAVE","EDTH","INGA","NDTH","THET","OULD","ORTH","EVER","THEN","IGHT","EAND","ATTH","THEB","OVER","DING","IONA","GTHE","FORT","THEA","RING","ALLY","ANDS","EFOR","INGS","EDIN","ANDA","THIN","OUGH","WERE","ERTH","YEAR","WILL","THEL","SAID","HEIR","NTER","ENTS","ECON","NING","COMP","TAND","SION","TIME","OUND","FORM","NAND","LAND","THEW","KING","PORT","YTHE","HTHE","HECO","ATED","ANDI","THED","INTE","HING","NGTO","SOME","THEE","ALSO","ENTI","ONAL"};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points+=4;
		}
		return points;
		
	}
	public static int check_quintgrams(String s) {	// TO CHECK QUINTGRAMS
		int points=0;
		String temp[]={"OFTHE","ATION","INTHE","THERE","INGTH","TOTHE","NGTHE","OTHER","ATTHE","TIONS","ANDTH","NDTHE","ONTHE","EDTHE","THEIR","TIONA","ORTHE","FORTH","INGTO","THECO","CTION","WHICH","THESE","AFTER","EOFTH","ABOUT","ERTHE","IONAL","FIRST","WOULD","WITHT","STHAT","ASTHE","ITION","INTER","FROMT","ITHTH","THTHE","THEFI","STATE","IONOF","ESAND","THATT","HATTH","THING","TIONO","COUNT","NTHES","EMENT","NATIO","ROMTH","TOFTH","THEST","YEARS","THEPR","DTHAT","UTTHE","EOPLE","PEOPL","OMTHE","DWITH","WITHA","THEMA","OUGHT","HEWAS","SOFTH","EMBER","ROUGH","SINTH","ROUND","SWITH","CAUSE","INGIN","UNDER","ETHAT","EASON","BYTHE","URING","RATIO","THEMO","ENTHE","ERAND","SWERE","SSION","AGAIN","EDINT","POINT","ITWAS","WHERE","IDENT","INGAN","EFORE","SCHOO","EVERY","CHOOL","MENTS","ESTHE","COULD","ONAND"};
		for(int i=0;i<temp.length;i++) {
			if(s.contains(temp[i]))
				points+=5;
		}
		return points;
		
	}
	public static int[] find_max() {	//TO FIND WHICH DECRYPTED MESSAGE PASSES MAX NUMBER OF DIAGRAMS AND TRIAGRAMS
		int max=0;
		int indx[]=new int[2];
		indx[0]=indx[1]=-1;
		for(int i=0;i<26;i++) {
			for(int j=0;j<mul_inv.length;j++) {
				if(dia[i][mul_inv[j]]+tria[i][mul_inv[j]]+quad[i][mul_inv[j]]+quint[i][mul_inv[j]]>max) {
					max=dia[i][mul_inv[j]]+tria[i][mul_inv[j]]+quad[i][mul_inv[j]]+quint[i][mul_inv[j]];
					indx[0]=i;indx[1]=mul_inv[j];
				}
			}
		}
		t2=dia[indx[0]][indx[1]];
		t3=tria[indx[0]][indx[1]];
		t4=quad[indx[0]][indx[1]];
		t5=quint[indx[0]][indx[1]];
		System.out.print("\n-->"+t2+"-->"+t3+"-->"+t4+"-->"+t5+"-->"+max);
		dia[indx[0]][indx[1]]=tria[indx[0]][indx[1]]=quad[indx[0]][indx[1]]=quint[indx[0]][indx[1]]=-1;	//REPLACES THE CURRENT MAX WITH -1 TO FIND THE NEXT MAX
		return indx;						//RETURNS THE INDEX OF CURRENT MAX
	}	
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		for(int i=0;i<26;i++) {
			for(int j=0;j<26;j++) {
				dia[i][j]=tria[i][j]=quad[i][j]=quint[i][j]-1;
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
			System.out.println("\t"+i+"\t"+Affine_cipher_inv(s,key1[0],key1[1],true));
		}
	}
}
