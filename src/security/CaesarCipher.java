package security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CaesarCipher {
	
	/*
	 * Encrypt messages using Caesar Cipher algorithm and decrypt
	 * the message without the key, only using a dictionary of words
	 */
	
	public static String encryptMessage(String str, int k){
		String encrypted = "";
		char letter;
			for(int i = 0; i < str.length(); i++){
				letter = (char)((str.charAt(i)-'a' + k)%26 + 'a');
				encrypted= encrypted + letter;
			}
		return encrypted;
	}
	
	public static String decryptMessage(String str, ArrayList<String> words){
		String decryptLine = "", message = "";
		char letter;
		int k = 0, currentTimes = 0, max = 0;
		for(int j=0; j<26; j++){
			for(int i = 0; i < str.length(); i++){
				if(str.charAt(i)-'a' - j<0){
					letter = (char)(26 - Math.abs((str.charAt(i) - 'a' - j)) +'a');
				}else{
					letter = (char)((str.charAt(i)-'a' - j)%26 + 'a');
				}
				decryptLine= decryptLine + letter;
			}
			for(int i = 0; i < words.size(); i++){
				if(decryptLine.contains(words.get(i))){
					currentTimes++;
				}	
			}
			if(currentTimes > max){
				max = currentTimes;
				message = decryptLine;
				k = j;
			}
			currentTimes = 0;
			decryptLine = "";
		}
		System.out.println("Decrypted text  : " + message + "\nK is : " + k );
		return message;
	}
	
	private static void buildDictinary(ArrayList<String> words){
		try{
            BufferedReader buf = new BufferedReader(new FileReader("words.txt"));
            String lineJustFetched = null;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    words.add(lineJustFetched);
                }
            }
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		buildDictinary(words);
		String plainText = "amoderndaywarriormeanmeanstridetodaystomsawyermeanmeanpridethoughhismindisnotforrentdon7tputhimdownasarroganthisreserveaquietdefenseridingoutthedayseventstheriver";
		System.out.println("Text to encrypt : " + plainText);
		String encrypteText = encryptMessage(plainText, 10);
		System.out.println("Encrypted text  : " + encrypteText);
		decryptMessage(encrypteText, words); 
	 }
}
