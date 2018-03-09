package com.lihan.SystemManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class SystemManger extends JPanel{

    public SystemManger() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("您进入了系统管理");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);

		ChangePasswordPanel b = new ChangePasswordPanel();
		tabbed.addTab("密码管理", b);
	}
}

