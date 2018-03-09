package com.lihan.SellManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class SellManger extends JPanel{

	public SellManger(){
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("欢迎进入销售管理系统");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);
		SaleDemo saleDemo = new SaleDemo();
		tabbed.addTab("销售单添加", saleDemo);
	}
}
