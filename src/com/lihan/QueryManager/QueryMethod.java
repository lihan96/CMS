package com.lihan.QueryManager;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lihan.Dao.DataBase;

class QueryMethod {
    static void Query(String sql, String[] cloNames, JTable jtable, CustomTableModel tableModel) {
        DataBase db = new DataBase();
        Vector<String[]> strings = db.Select(sql, cloNames.length);
        int p = 0;
        int q = strings.size();
        int r = tableModel.getRowCount();//�õ�֮ǰ������
        if (q == 0) {
            JOptionPane.showMessageDialog(null, "�Բ���û�в�ѯ����Ҫ�Ľ���������������ѯ��������",
                    "������Ϣ", JOptionPane.ERROR_MESSAGE);
        } else {
            if (r > q) {
                while ((r - q) > 0) {
                    tableModel.removeRow(r - 1);
                    r = tableModel.getRowCount();
                }
            } else if (r <= q) {
                for (int i = 0; i < q - r; i++) {
                    tableModel.addRow(new Object[]{});
                    System.out.print("�����У�" + i + "��");
                }
            }
            for (String[] str : strings) {
                for (int i = 0; i < cloNames.length; i++) {
                    jtable.setValueAt(str[i], p, i);//�������������
                }
                p++;
                if (p == q) {
                    break;
                }
            }
        }
    }
}
