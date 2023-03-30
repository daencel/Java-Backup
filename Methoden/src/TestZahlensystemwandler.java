
public class TestZahlensystemwandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("getDigit(\"9\") ergibt " + 
				MeinZahlensystemwandler.getDigit(9));
		System.out.println("getDigit(\"W\") ergibt " + 
				MeinZahlensystemwandler.getDigit('W'));
		System.out.println("getDigit(\"#\") ergibt " + 
				MeinZahlensystemwandler.getDigit('#'));
		System.out.println("getDigit(\"9\") ergibt " + 
				MeinZahlensystemwandler.getDigit(9));
		System.out.println("getDigit(\"26\") ergibt " + 
				MeinZahlensystemwandler.getDigit(26));
		System.out.println("getDigit(\"200\") ergibt " + 
				MeinZahlensystemwandler.getDigit(200));
		System.out.println("numToDec(\"011110110\", 2) ergibt " + 
				MeinZahlensystemwandler.numToDec("01110110", 2));
		System.out.println("numToDec(\"3A\", 16) ergibt " + 
				MeinZahlensystemwandler.numToDec("3A", 16));
		System.out.println("numToDec(\"3A\", 7) ergibt " + 
				MeinZahlensystemwandler.numToDec("3A", 7));
		System.out.println("decToNum(61898,8) ergibt " + 
				MeinZahlensystemwandler.decToNum(61898,8));
		System.out.println("decToNum(118,2) ergibt " + 
				MeinZahlensystemwandler.decToNum(118,28));
		System.out.println("hexToDec(3AA) ergibt " + 
				MeinZahlensystemwandler.hexToDec("3AA"));
		System.out.println("hexToDec(16) ergibt " + 
				MeinZahlensystemwandler.hexToDec("16"));
		System.out.println("decToHex(1024) ergibt " + 
				MeinZahlensystemwandler.decToHex(1024));
		System.out.println("decToHex(909) ergibt " + 
				MeinZahlensystemwandler.decToHex(909));
		System.out.println("dualToDec(101010) ergibt " + 
				MeinZahlensystemwandler.dualToDec(101010));
		System.out.println("dualToDec(1234) ergibt " + 
				MeinZahlensystemwandler.dualToDec(1234));
		System.out.println("decToDual(111) ergibt " + 
				MeinZahlensystemwandler.decToDual(111));
		System.out.println("decToDual(1024) ergibt " + 
				MeinZahlensystemwandler.decToDual(1024));
		System.out.println("numToNum(\"101010\",2,16) ergibt " + 
				MeinZahlensystemwandler.numToNum("101010",2,16));
		System.out.println("numToNum(\"3AB\",14,5) ergibt " + 
				MeinZahlensystemwandler.numToNum("3AB",14,5));
		System.out.println("numToNum(\"8A\",2,8) ergibt " + 
				MeinZahlensystemwandler.numToNum("8A",2,8));
	}

}
