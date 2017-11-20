package fr.polytech.hibernate.tp10.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7730911445585324656L;
	
	JTabbedPane tabs;
	TabClients tabClients;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("Test");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		
		tabs = new JTabbedPane();
		tabClients = new TabClients();
		tabs.addTab("Clients", tabClients);
		
		mainPanel.add(tabs);
		setContentPane(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
