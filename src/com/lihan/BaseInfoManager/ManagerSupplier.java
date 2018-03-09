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
        JLabel title = new JLabel("��Ӧ����Ϣ����");
        title.setBounds(10, 10, 270, 80);
        title.setForeground(new Color(0, 152, 210));
        title.setFont(new Font("������", java.awt.Font.PLAIN, 32));
        this.add(title);

        JLabel[] label = {new JLabel("* ��Ӧ�̱��:"), new JLabel("* ��Ӧ������:"),
                new JLabel("��˾��ַ:"), new JLabel("��˾�绰:"), new JLabel("��������:")};

        for (int i = 0; i < label.length; i++) {
            text[i] = new JTextField();
            label[i].setBounds(50, 100+25*i, 140, 20);
            text[i].setBounds(200, 100+25*i, 160, 20);
            label[i].setHorizontalAlignment(JLabel.RIGHT);
            this.add(text[i]);
            this.add(label[i]);
        }

        JButton btnAdd = new JButton("��  ��");
        btnAdd.setBounds(100, 250, 100, 30);
        JButton btnMed = new JButton("��  ��");
        btnMed.setBounds(240, 250, 100, 30);
        JButton btnDel = new JButton("ɾ  ��");
        btnDel.setBounds(380, 250, 100, 30);
        btnAdd.setBackground(new Color(0, 152, 210));
        btnMed.setBackground(new Color(0, 152, 210));
        btnDel.setBackground(new Color(0, 152, 210));
        this.add(btnAdd);
        this.add(btnMed);
        this.add(btnDel);

		//�������
		btnAdd.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();
            }
            //�жϱ������Ƿ�ȫ����д
            if((a[0].equals(""))||(a[1].equals(""))){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "�����������Ϣ","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                //������д�����ݿ�
                DataBase db = new DataBase();
                String sql = "insert into tb_supplier(sup_id,sup_name,address,telephone,email) values ('"
                        +a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"')";
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "��ӳɹ�����");
            }
        });

		//�޸�����
		btnMed.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();
            }
            if(a[0].equals("")){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "�ͻ���ϢΪ�գ�������ͻ���Ϣ","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                //�޸����,��������д�����ݿ�
                DataBase db = new DataBase();			
                String sql = "UPDATE tb_supplier SET sup_id='"+a[0]+"',sup_name= '"+a[1]+"'"+",address= '"
                        +a[2]+"',telephone= '"+a[3]+"',email= '"+a[4]+"' WHERE sup_id='"+a[0]+"'";
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "�޸ĳɹ�����");
            }
        });

		//ɾ������
		btnDel.addActionListener(e -> {
            String a[] = new String[5];
            for (int i = 0; i < a.length; i++) {
                a[i] = text[i].getText();				
            }
            if(a[0].equals("")){
                JOptionPane.showMessageDialog(ManagerSupplier.this, "��Ӧ����ϢΪ�գ������빩Ӧ����Ϣ","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                DataBase db = new DataBase();
                String sql = "delete from tb_supplier where sup_id='"+a[0]+"' " ;
                db.updateDb(sql);
                db.dbClose();
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�����");
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

