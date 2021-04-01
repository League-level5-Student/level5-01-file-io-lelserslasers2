package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a messager.
	 * Use any key you want (1 - 25) to shift each letter in the users input and save the final result to a file.
	 */
	
	public static char[] encode(char[] alpha, char[] key, char[] message) {
		
		for (int i = 0; i < message.length; i++) {
			
			int indexInAlpha = -1;
			for (int j = 0; j < alpha.length; j++) {
				if(alpha[j] == message[i]) {
					indexInAlpha = j;
				}
			}
			
			if(indexInAlpha != -1) {
				message[i] = key[indexInAlpha];
			}
		}
		
		return message;
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
		String messageString = JOptionPane.showInputDialog("Enter message:");
		char[] message = messageString.toCharArray();
		
		String alphaString = "abcdefghijklmnopqrstuvwxyz";
		char[] alpha = alphaString.toCharArray();
		char[] key = genKey(Arrays.copyOf(alpha, alpha.length), shift);
		
		char[] encodedMessage = encode(alpha, key, Arrays.copyOf(message, message.length));
		
		System.out.println(encodedMessage);
		System.out.println(alpha);
		System.out.println(key);
		
		messageString = new String(encodedMessage);
		
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/encode.txt");
			fw.write(messageString);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
Copyright © 2019 Millan Kumar