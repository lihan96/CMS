package com.lihan.QueryManager;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    private String[] cloNames;
    private Class[] dataType;

    public CustomTableModel(int r, int c, String[] cn, Class[] DateType) {
        super(r, c);
        cloNames = cn;
        this.dataType = DateType;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public String getColumnName(int c) {
        return cloNames[c];
    }

    public Class getColumnClass(int c) {
        return dataType[c];
    }
}