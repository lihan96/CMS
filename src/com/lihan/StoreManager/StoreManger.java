package com.lihan.StoreManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class StoreManger extends JPanel {

    public StoreManger() {
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("��ӭ����ֿ����ϵͳ");
        this.add(label, BorderLayout.NORTH);
        JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
        this.add(tabbed, BorderLayout.CENTER);
        StockDemo ID = new StockDemo();
        tabbed.addTab("���������", ID);
        ReturnDemo RD = new ReturnDemo();
        tabbed.addTab("�˻������", RD);
    }
}
