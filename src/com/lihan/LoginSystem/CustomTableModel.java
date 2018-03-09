package com.lihan.LoginSystem;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
	private String[] cloNames;
	private Class[] dataType;
	public CustomTableModel(int r,int c,String[] cn,Class[] DateType){
		super(r,c);
		cloNames = cn;
		this.dataType = DateType;
	}
	public boolean isCellEditable(int row,int col){
		boolean flag = true;
		if (col != 1) {
			flag = false; 
		}
		return flag;
	}
	public String getColumnName(int c){
		return cloNames[c];
	}
	public Class getColumnClass(int c){
		return dataType[c];
	}
}