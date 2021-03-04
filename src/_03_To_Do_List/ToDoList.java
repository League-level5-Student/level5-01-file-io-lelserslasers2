package _03_To_Do_List;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList {
	
	public void run() {
		
		ArrayList<String> lst = new ArrayList<String>();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton addTask = new JButton();
		JButton viewTasks = new JButton();
		JButton removeTask = new JButton();
		JButton saveList = new JButton();
		JButton loadList = new JButton();
		
		addTask.setText(" Add Task ");
		viewTasks.setText("View Tasks");
		removeTask.setText("Remove Task");
		saveList.setText(" Save List ");
		loadList.setText(" Load List ");
		
		addTask.addActionListener( (e) ->
		{
			String newTask = JOptionPane.showInputDialog("Add Task:");
			lst.add(newTask);
		}
		);
		
		viewTasks.addActionListener( (e) ->
		{
			String tasks = showTasks(lst);
			JOptionPane.showMessageDialog(null, tasks);
		}
		);
		
		removeTask.addActionListener( (e) ->
		{
			String tasks = showTasks(lst);
			String index = JOptionPane.showInputDialog("Pick which number task to remove:\n" + tasks);
			int indexInt = Integer.parseInt(index) - 1;
			lst.remove(indexInt);
		}
		);
		
		saveList.addActionListener( (e) ->
		{
			String fileName = "";
			
			String[] choices = { "Ok", "Create new save file" };
			
			int choice = JOptionPane.showOptionDialog(null, "Pick a location to save to:", "Save List", 0, JOptionPane.INFORMATION_MESSAGE, null,
					choices, null);
			
			if (choice == 0) {
			
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileName = jfc.getSelectedFile().getAbsolutePath();
				}
			}
			else {
				fileName = JOptionPane.showInputDialog("Enter File Path:");
			}
		
			
			try {
				FileWriter fw = new FileWriter(fileName);
				
				for (int i = 0; i < lst.size(); i++) {
					fw.write(lst.get(i) + "\n");
				}
				
				fw.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		);
		
		loadList.addActionListener( (e) ->
		{
			String fileName = "";
			
			lst.clear();
			
			JOptionPane.showMessageDialog(null, "Pick file to load list from");
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				fileName = jfc.getSelectedFile().getAbsolutePath();
			}
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				
				String line = br.readLine();
				while(line != null){
					lst.add(line);
					line = br.readLine();
				}
				
				br.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}
		);
		
		
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		panel.add(loadList);
		
		frame.setPreferredSize(new Dimension(250, 150));
		frame.pack();
		frame.add(panel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String showTasks(ArrayList<String> lst) {
		String tasks = "TASKS:\n";
		for (int i = 0; i < lst.size(); i++) {
			int count = i + 1;
			tasks = tasks + count + ") " + lst.get(i) + "\n";
		}
		return tasks;
	}
}
