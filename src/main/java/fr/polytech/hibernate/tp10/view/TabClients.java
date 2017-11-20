package fr.polytech.hibernate.tp10.view;

import javax.swing.*;

import fr.polytech.hibernate.tp10.Controller;

public class TabClients extends JPanel {
	
	ClientTableModel clientsModel;
	JTable clientsTable;

	private static final long serialVersionUID = 7682448424623449099L;
	
	public TabClients(Controller c) {
		clientsModel = new ClientTableModel(c);
		clientsTable = new JTable(clientsModel);
		JScrollPane clientsScroll = new JScrollPane(clientsTable);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(clientsScroll);
	}
	
}
