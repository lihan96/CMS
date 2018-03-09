package com.lihan.BaseInfoManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class BaseInfoManager extends JPanel {
    public BaseInfoManager() {
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("欢迎进入信息管理系统");
        this.add(label, BorderLayout.NORTH);
        JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
        this.add(tabbed, BorderLayout.CENTER);

        this.add(tabbed);
        ManagerCustomer managerCustomer = new ManagerCustomer();
        tabbed.addTab("客户信息", managerCustomer);
        ManagerProduct managerProduct = new ManagerProduct();
        tabbed.addTab("商品信息", managerProduct);
        ManagerSupplier managerSupplier = new ManagerSupplier();
        tabbed.addTab("供应商信息", managerSupplier);
    }
}
