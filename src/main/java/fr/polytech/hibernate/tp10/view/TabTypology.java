package fr.polytech.hibernate.tp10.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.polytech.hibernate.tp10.Controller;

public class TabTypology extends JPanel {
	
	TypologyTableModel typosModel;
	JTable typosTable;

	private static final long serialVersionUID = 7682448424623449099L;
	
	public TabTypology(Controller c) {
		typosModel = new TypologyTableModel(c);
		typosTable = new JTable(typosModel);
		JScrollPane clientsScroll = new JScrollPane(typosTable);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(clientsScroll);
	}
	
}
