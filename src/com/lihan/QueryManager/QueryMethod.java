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
        int r = tableModel.getRowCount();//得到之前的行数
        if (q == 0) {
            JOptionPane.showMessageDialog(null, "对不起，没有查询到你要的结果，请重新输入查询条件！！",
                    "错误消息", JOptionPane.ERROR_MESSAGE);
        } else {
            if (r > q) {
                while ((r - q) > 0) {
                    tableModel.removeRow(r - 1);
                    r = tableModel.getRowCount();
                }
            } else if (r <= q) {
                for (int i = 0; i < q - r; i++) {
                    tableModel.addRow(new Object[]{});
                    System.out.print("增加行：" + i + "、");
                }
            }
            for (String[] str : strings) {
                for (int i = 0; i < cloNames.length; i++) {
                    jtable.setValueAt(str[i], p, i);//向表格中添加数据
                }
                p++;
                if (p == q) {
                    break;
                }
            }
        }
    }
}
