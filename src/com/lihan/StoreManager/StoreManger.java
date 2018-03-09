package com.lihan.StoreManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class StoreManger extends JPanel {

    public StoreManger() {
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("欢迎进入仓库管理系统");
        this.add(label, BorderLayout.NORTH);
        JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
        this.add(tabbed, BorderLayout.CENTER);
        StockDemo ID = new StockDemo();
        tabbed.addTab("进货单添加", ID);
        ReturnDemo RD = new ReturnDemo();
        tabbed.addTab("退货单添加", RD);
    }
}
