package com.lihan.QueryManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class QueryManager extends JPanel{

    public QueryManager() {
        this.setLayout(new BorderLayout());
		JLabel label = new JLabel("欢迎进入查询管理系统");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);
        CustomerQuery a = new CustomerQuery();
        tabbed.addTab("客户查询", a);
        ProductQuery b = new ProductQuery();
        tabbed.addTab("商品查询", b);
        SupplierQuery c = new SupplierQuery();
        tabbed.addTab("供应商查询", c);
        SaleQuery d = new SaleQuery();
        tabbed.addTab("销售查询", d);
        StockQuery e = new StockQuery();
        tabbed.addTab("进货查询", e);
        ReturnQuery f = new ReturnQuery();
        tabbed.addTab("退货查询", f);
	}
}

