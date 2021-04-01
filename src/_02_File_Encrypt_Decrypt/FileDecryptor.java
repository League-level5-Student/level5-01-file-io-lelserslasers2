package _02_File_Encrypt_Decrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 * 
	 */
	
	public static char[] decode(char[] alpha, char[] key, char[] encodedMessage) {
		
		for (int i = 0; i < encodedMessage.length; i++) {
			
			int indexInKey = -1;
			for (int j = 0; j < key.length; j++) {
				if(key[j] == encodedMessage[i]) {
					indexInKey = j;
				}
			}
			
			if(indexInKey != -1) {
				encodedMessage[i] = alpha[indexInKey];
			}
		}
		
		return encodedMessage;
	}
	
	
	public static char[] genKey(char[] alpha, int shift) {
		
		char[] doubleAlpha = new char[alpha.length * 2];
		
		for (int i = 0; i < alpha.length; i++) {
			doubleAlpha[i] = alpha[i];
		}
		for (int i = alpha.length; i < doubleAlpha.length; i++) {
			doubleAlpha[i] = alpha[i - alpha.length];
		}
		
		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = doubleAlpha[i + shift];
		}
		
		return alpha;
	}
	
	public static void main(String[] args) {
		int shift = 4;
		
		String alphaString = "abcdefghijklmnopqrstuvwxyz";
		char[] alpha = alphaString.toCharArray();
		char[] key = genKey(Arrays.copyOf(alpha, alpha.length), shift);
		
		String encodedMessageString = "";
		
		try {
			FileReader fr = new FileReader("src/_02_File_Encrypt_Decrypt/encode.txt");
			int c = fr.read();
			while(c != -1){
				encodedMessageString = encodedMessageString + (char)c;
				c = fr.read();
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		char[] encodedMessage = encodedMessageString.toCharArray();
		char[] message = decode(alpha,key, encodedMessage);
		String messageString = new String(message);
		System.out.println(messageString);
	}
}
Copyright © 2019 Millan Kumar