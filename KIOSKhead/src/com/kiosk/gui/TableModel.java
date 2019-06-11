package com.kiosk.gui;

import javax.swing.table.AbstractTableModel;

import org.w3c.dom.views.AbstractView;

public class TableModel extends AbstractTableModel{
	String[] columnName;
	Object[][] data;
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columnName[col];
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
