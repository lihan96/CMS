package com.lihan.BaseInfoManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class BaseInfoManager extends JPanel {
    public BaseInfoManager() {
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("��ӭ������Ϣ����ϵͳ");
        this.add(label, BorderLayout.NORTH);
        JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
        this.add(tabbed, BorderLayout.CENTER);

        this.add(tabbed);
        ManagerCustomer managerCustomer = new ManagerCustomer();
        tabbed.addTab("�ͻ���Ϣ", managerCustomer);
        ManagerProduct managerProduct = new ManagerProduct();
        tabbed.addTab("��Ʒ��Ϣ", managerProduct);
        ManagerSupplier managerSupplier = new ManagerSupplier();
        tabbed.addTab("��Ӧ����Ϣ", managerSupplier);
    }
}
