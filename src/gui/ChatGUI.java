package gui;

import javax.swing.*;

public class ChatGUI {
	
	public static void main(String Args[]){
		
		JFrame myWindow=new JFrame("Welcome to the chat system!!!");
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GUI chatGUI=new GUI();
		myWindow.getContentPane().add(chatGUI);
		
		myWindow.setSize(1000,600);
		myWindow.setVisible(true);
	}

}
