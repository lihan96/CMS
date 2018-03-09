package com.lihan.StoreManager;

import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

import com.lihan.Dao.DataBase;

public class ReturnDemo extends JPanel{
	private Image backgroundImage = new ImageIcon("image/background.jpg").getImage();
	private JTextField [] text = new JTextField[5];

	ReturnDemo(){
		this.setSize(600,510);
		this.setLayout(null);
		JLabel title = new JLabel("�˻������");
		title.setBounds(10, 10, 270, 80);
		title.setForeground(new Color(0, 152, 210));
		title.setFont(new Font("������", java.awt.Font.PLAIN, 32));
		this.add(title);

		JLabel[] jLabels = {new JLabel("* �˻����"), new JLabel("* ��Ӧ�̱��:"),
				new JLabel("* ��Ʒ���:"), new JLabel("* �˻�ʱ��:"), new JLabel("* ��    ��:")};
		JLabel info = new JLabel("�������˻���Ϣ��");
		info.setBounds(10, 100, 200, 20);

		for (int i = 0; i < jLabels.length; i++) {
			text[i] = new JTextField();
			jLabels[i].setBounds(50, 100+30*i, 140, 25);
			text[i].setBounds(200, 100+30*i, 160, 25);
			jLabels[i].setHorizontalAlignment(JLabel.RIGHT);
			this.add(text[i]);
			this.add(jLabels[i]);
		}

		JLabel tip = new JLabel("(�����ʽ \"YYYY-MM-DD\")");
		tip.setBounds(370, 190, 200, 20);

		JButton btnAdd = new JButton("���");
		btnAdd.setBounds(210, 300, 150, 90);
		btnAdd.setBackground(new Color(0, 152, 210));

		this.add(info);
		this.add(tip);
		this.add(btnAdd);

		btnAdd.addActionListener(e -> {
			String[] str = new String[5];
			for(int i = 0;i <str.length;i++){
				str[i] = text[i].getText();
			}
			try {
				java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(str[3]);
				Date date = new Date(temp.getTime());
				String sql = "INSERT INTO tb_return(ret_id,sup_id,pro_id,date,number)" +
						"VALUES('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+date+"','"+str[4]+"')";
				DataBase db = new DataBase();
				db.updateDb(sql);
				db.dbClose();
				JOptionPane.showMessageDialog(null, "��ӳɹ�����");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
