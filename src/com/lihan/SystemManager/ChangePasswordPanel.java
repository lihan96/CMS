package com.lihan.SystemManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.lihan.Dao.DataBase;

public class ChangePasswordPanel extends JPanel implements ActionListener{
	private Image backgroundImage = new ImageIcon("image/background.jpg").getImage();
	private JButton btnChange = new JButton("�޸�");
	private JButton cancel = new JButton("ȡ��");
    private JTextField text = new JTextField();
	private JPasswordField[] passwordFields = {new JPasswordField(),new JPasswordField(),new JPasswordField()};

	ChangePasswordPanel(){
		this.setSize(600,590);
		this.setLayout(null);
        JLabel[] label = {new JLabel("�û���:"), new JLabel("������:"),
                new JLabel("������(5~10���ַ�):"), new JLabel("�ظ�������:")};
        for (int i = 0; i < label.length; i++) {
			label[i].setBounds(100, 100+25*i, 140, 20);
			this.add(label[i]);
			
			btnChange.setBounds(120, 300, 120, 60);
			cancel.setBounds(320, 300, 120, 60);
			btnChange.setBackground(new Color(0, 152, 210));
			cancel.setBackground(new Color(0, 152, 210));
			this.add(btnChange);
			this.add(cancel);
		}
		this.text.setBounds(250,100,160,20);
		this.add(text);
		
		//�������
		for(int i = 0; i < passwordFields.length; i++){
			this.passwordFields[i].setBounds(250,125+25*i,160,20);
			this.add(passwordFields[i]);
		}
		this.passwordFields[1].addCaretListener(e -> {
            int p = (new String(passwordFields[1].getPassword())).length();
            if(p >10){
                JOptionPane.showMessageDialog(null, "�������ù�������","������Ϣ",JOptionPane.WARNING_MESSAGE);
            }
        });
		this.passwordFields[2].addCaretListener(e -> {
            int p = (new String(passwordFields[2].getPassword())).length();
            if(p >10){
                JOptionPane.showMessageDialog(null, "�������ù�������","������Ϣ",JOptionPane.WARNING_MESSAGE);
            }
        });
		this.btnChange.addActionListener(this);
		this.cancel.addActionListener(this);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
    public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == btnChange){
	        String[] str = new String[4];
	        str[0] = this.text.getText().trim();
	        str[1] = new String(this.passwordFields[0].getPassword());
	        str[2] = new String(this.passwordFields[1].getPassword());
	        str[3] = new String(this.passwordFields[2].getPassword());
	        boolean b = TellUser(str[0],str[1]);
	        if(!b){
	            JOptionPane.showMessageDialog(null, "�û��������벻ƥ�䣬���������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
			}else{
                if(str[2].equals("")||str[3].equals("")){
                    JOptionPane.showMessageDialog(null, "�޸ĵ����벻��Ϊ�գ������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
                }else{
                    if(str[2].equals(str[3])){
                        DataBase db = new DataBase();
                        String sql = "UPDATE tb_operator SET password = '"+str[3]+"'" ;
                        db.updateDb(sql);
                        JOptionPane.showMessageDialog(null, "�޸ĳɹ�����");
                    }else{
                        JOptionPane.showMessageDialog(null, "������������벻һ�������������룡��","������Ϣ",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }else if(e.getSource() == cancel){
            text.setText("");
            for (JPasswordField passwordField : passwordFields) {
                passwordField.setText("");
            }
        }
	}

    private boolean TellUser(String username, String password){
	    boolean flag = false;
	    DataBase db1 = new DataBase();
	    String sql = "select password from tb_operator where username = '"+username+"'";
	    db1.selectDb(sql);
	    try {
	        while(db1.rs.next()){
	            String ss = db1.rs.getString(1).trim();
	            if(ss.equals(password)){
	                flag = true;
	                break;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    db1.dbClose();
	    return flag;
	}
}

