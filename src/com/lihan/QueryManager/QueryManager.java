package com.lihan.QueryManager;

import java.awt.BorderLayout;
import javax.swing.*;

public class QueryManager extends JPanel{

    public QueryManager() {
        this.setLayout(new BorderLayout());
		JLabel label = new JLabel("��ӭ�����ѯ����ϵͳ");
		this.add(label,BorderLayout.NORTH);
		JTabbedPane tabbed = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbed,BorderLayout.CENTER);
        CustomerQuery a = new CustomerQuery();
        tabbed.addTab("�ͻ���ѯ", a);
        ProductQuery b = new ProductQuery();
        tabbed.addTab("��Ʒ��ѯ", b);
        SupplierQuery c = new SupplierQuery();
        tabbed.addTab("��Ӧ�̲�ѯ", c);
        SaleQuery d = new SaleQuery();
        tabbed.addTab("���۲�ѯ", d);
        StockQuery e = new StockQuery();
        tabbed.addTab("������ѯ", e);
        ReturnQuery f = new ReturnQuery();
        tabbed.addTab("�˻���ѯ", f);
	}
}

