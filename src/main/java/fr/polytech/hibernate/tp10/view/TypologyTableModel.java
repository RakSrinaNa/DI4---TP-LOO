package fr.polytech.hibernate.tp10.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.polytech.hibernate.tp10.Controller;
import fr.polytech.hibernate.tp10.model.GroundTypology;
import fr.polytech.hibernate.tp10.model.Typology;

public class TypologyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6701580366527683459L;
	
	private List<Typology> typos;
	private final String[] header = {"Species", "Volume", "Ground"};
	
	public TypologyTableModel(Controller c) {
		typos = c.getElements(Typology.class);
	}
	
	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return typos.size();
	}

	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return typos.get(rowIndex).getSpecies();
		case 1:
			return typos.get(rowIndex).getWaterLiters();
		case 2:
			if(typos.get(rowIndex) instanceof GroundTypology)
				return ((GroundTypology)typos.get(rowIndex)).getGroundMaterial();
			else
				return "None";
		default:
			return null;
		}
	}
}
