package _04_Directory_Iteration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] files = directory.listFiles();
			if(files != null) {
				for(File f : files) {
					//System.out.println(f.getAbsolutePath());
					
					 String fred = (String)f.getAbsolutePath();
					 ArrayList<Character> charsLst = new ArrayList<Character>();
					 for (char c : fred.toCharArray()) {
						 charsLst.add(c);
					 }
					 
					 boolean isDir = charsLst.contains('.');
					 if (isDir == false) {
						File dir = f;
						File[] fis = dir.listFiles();
						
						if(fis != null) {
							System.out.println(f.getAbsolutePath());
							for(File x : fis) {
								
								 String joe = (String)x.getAbsolutePath();
	
						
								 if (joe.contains(".java")){
									 System.out.println("WE GOT ONE");
									 try {
										FileWriter fw = new FileWriter(joe, true);
										
										fw.write("//Copyright © 2019 Millan Kumar");
										fw.close();
										
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									 
									 
									 
									 
									 
								 }
								
							}
						}
						
					 }
				  
				  
				}
			}
		}
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
		
		
	}
}
//Copyright © 2019 Millan Kumar