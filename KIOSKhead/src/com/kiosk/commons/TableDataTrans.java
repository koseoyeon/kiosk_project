package com.kiosk.commons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;

import com.kiosk.gui.TableModel;

public class TableDataTrans{
	public void TabeDataExportToExcel(JTable table,File file) throws IOException{
		
		TableModel model = (TableModel) table.getModel();
		FileWriter writer = new FileWriter(file);
		for(int i=0;i<model.getColumnCount();i++) {
			writer.write(model.getColumnName(i)+"\t");
		}
		writer.write("\n");
		for(int i=0;i<model.getRowCount();i++) {
			for(int j=0;j<model.getColumnCount();j++) {
				writer.write(model.getValueAt(i, j).toString()+"\t");
			}
			writer.write("\n");
		}
		writer.close();		
	}
}
