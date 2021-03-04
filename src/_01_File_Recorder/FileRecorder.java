package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileRecorder {
	
	public void run() {
		
		String message = JOptionPane.showInputDialog("Enter message: ");
		
		try {
			FileWriter fw = new FileWriter("src/_01_File_Recorder/message.txt", true);
			fw.write("\n" + message);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
