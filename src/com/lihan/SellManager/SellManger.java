package com.lihan.SellManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class SellManger extends JPanel{

	public SellManger(){
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("��ӭ�������۹���ϵͳ");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);
		SaleDemo saleDemo = new SaleDemo();
		tabbed.addTab("���۵����", saleDemo);
	}
}
