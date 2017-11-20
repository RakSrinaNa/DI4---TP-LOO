package fr.polytech.hibernate.tp10.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.polytech.hibernate.tp10.Controller;
import fr.polytech.hibernate.tp10.model.Client;

public class ClientTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2976154803262941517L;
	
	private List<Client> clients;
	private final String[] header = {"ID", "Name", "Address", "Phone"};
	
	public ClientTableModel(Controller c) {
		clients = c.getElements(Client.class);
	}
	
	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return clients.get(rowIndex).getID();
		case 1:
			return clients.get(rowIndex).getName();
		case 2:
			return clients.get(rowIndex).getAddress();
		case 3:
			return clients.get(rowIndex).getPhone();
		default:
			return null;
		}
	}
	
	public void update() {
		fireTableDataChanged();
	}
	
	

}
