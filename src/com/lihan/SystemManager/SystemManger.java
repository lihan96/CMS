package com.lihan.SystemManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class SystemManger extends JPanel{

    public SystemManger() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("��������ϵͳ����");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);

		ChangePasswordPanel b = new ChangePasswordPanel();
		tabbed.addTab("�������", b);
	}
}

