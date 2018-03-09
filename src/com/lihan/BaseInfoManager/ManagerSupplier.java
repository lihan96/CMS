package com.lihan.BaseInfoManager;

import java.awt.*;
import javax.swing.*;

import com.lihan.Dao.DataBase;

public class ManagerSupplier extends JPanel {
	private Image backgroundImage = new ImageIcon("image/background.jpg").getImage();
    private JTextField [] text = new JTextField[5];

    ManagerSupplier() {
        this.setSize(600,510);
        this.setLayout(null);
        JLabel title = new JLabel("供应商信息管理");
        title.setBounds(10, 10, 270, 80);
        title.setForeground(new Color(0, 152, 210));
        title.setFont(new Font("新宋体", java.awt.Font.PLAIN, 32));
        this.add(title);

        JLabel[] label = {new JLabel("* 供应商编号:"), new JLabel("* 供应商名称:"),
                new JLabel("公司地址:"), new JLabel("公司电话:"), new JLabel("电子邮箱:")};

        for (int i = 0; i < label.length; i++) {
            text[i] = new JTextField();
            label[i].setBounds(50, 100+25*i, 140, 20);
            text[i].setBounds(200, 100+25*i, 160, 20);
            label[i].setHorizontalAlignment(JLabel.RIGHT);
            this.add(text[i]);
            this.add(label[i]);
        }

        JButton btnAdd = new JButton("添  加");
        btnAdd.setBounds(100, 250, 100, 30);
        JButton btnMed = new JButton("修  改");
        btnMed.setBounds(240, 250, 100, 30);
        JButton btnDel = new JButton("删  除");
        btnDel.setBounds(380, 250, 100, 30);
        btnAdd.setBackground(new Color(0, 152, 210));
        btnMed.setBackground(new Color(0, 152, 210));
        btnDel.setBackground(new Color(0, 152, 210));
        this.add(btnAdd);
        this.add(btnMed);
        this.add(btnDel);

		//添加数据
		btnAdd.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();
            }
            //判断必填项是否全部填写
            if((a[0].equals(""))||(a[1].equals(""))){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "请完善你的信息","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                //将数据写入数据库
                DataBase db = new DataBase();
                String sql = "insert into tb_supplier(sup_id,sup_name,address,telephone,email) values ('"
                        +a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"')";
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "添加成功！！");
            }
        });

		//修改数据
		btnMed.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();
            }
            if(a[0].equals("")){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "客户信息为空，请输入客户信息","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                //修改语句,并将数据写入数据库
                DataBase db = new DataBase();			
                String sql = "UPDATE tb_supplier SET sup_id='"+a[0]+"',sup_name= '"+a[1]+"'"+",address= '"
                        +a[2]+"',telephone= '"+a[3]+"',email= '"+a[4]+"' WHERE sup_id='"+a[0]+"'";
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "修改成功！！");
            }
        });

		//删除数据
		btnDel.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();				
            }
            if(a[0].equals("")){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "供应商信息为空，请输入供应商信息","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                DataBase db = new DataBase();
                String sql = "delete from tb_supplier where sup_id='"+a[0]+"' " ;
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "删除成功！！");
                for (int i = 0; i < 5; i++) {
                    text[i].setText("");				
                } 
            }
        });
	}

	public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

