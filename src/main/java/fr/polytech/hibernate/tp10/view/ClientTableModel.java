package fr.polytech.hibernate.tp10.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fr.polytech.hibernate.tp10.model.Client;

public class ClientTableModel extends AbstractTableModel {

	private ArrayList<Client> clients;
	
	@Override
	public int getColumnCount() {
		
		return 0;
	}

	@Override
	public int getRowCount() {
		
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return null;
	}
	
	public void update() {
		fireTableDataChanged();
	}
	
	

}
