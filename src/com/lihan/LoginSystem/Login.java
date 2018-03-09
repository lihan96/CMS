package com.lihan.LoginSystem;

import com.lihan.Dao.DataBase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.*;

public class Login extends JFrame {

    private Login(String name) {
        super(name);
        //���ñ���
        ImagePanelJxc loginImagePanel = new ImagePanelJxc("image/login.jpg");
        loginImagePanel.setLayout(null);

        //����û��������
        final JTextField nameTextField = new JTextField(5);
        nameTextField.setBounds(150, 50, 120, 27);
        loginImagePanel.add(nameTextField);

        //������������
        final JPasswordField passwordField = new JPasswordField(5);
        passwordField.setEchoChar('*');
        passwordField.setBounds(150, 100, 120, 27);
        loginImagePanel.add(passwordField);

        //��ӵ�½��ť
        JButton loginButton = new JButton("��½");
        loginButton.setBounds(50, 160, 80, 36);
        loginButton.setBackground(new Color(0, 152, 210));
        loginButton.setForeground(new Color(255, 255, 255));
        loginImagePanel.add(loginButton);

        //���ȡ����ť
        JButton cancelButton = new JButton("ȡ��");
        cancelButton.setBounds(150, 160, 80, 36);
        cancelButton.setBackground(new Color(0, 152, 210));
        cancelButton.setForeground(new Color(255, 255, 255));
        loginImagePanel.add(cancelButton);

        //�����Ͽ�ѡ��Ȩ��
        final JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("����Ա");
        jComboBox.addItem("����Ա");
        jComboBox.setBounds(250, 160, 100, 36);
        jComboBox.setBackground(new Color(255, 255, 255));
        loginImagePanel.add(jComboBox);

        //��ӱ������
        this.add(loginImagePanel);

        //���õ�½�����С   �Լ�����Ļ������ʾ
        Dimension loginDimension;
        this.setSize(400, 250);
        loginDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (loginDimension.width - this.getWidth()) / 2;
        int y = (loginDimension.height - this.getHeight()) / 2;
        this.setLocation(x, y);

        //���õ�½����Ϊ:�ɼ�����������
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //���û�����������¼�����
        nameTextField.addActionListener(e -> passwordField.requestFocus());

        //�������������¼�����   ����ֱ�ӻس���¼
        passwordField.addActionListener((ActionEvent e) -> {
            String user = nameTextField.getText();
            String password = new String(passwordField.getPassword());
            String power = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();

            if ((user.equals("")) || (password.equals(""))) {
                JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            } else {
                DataBase db = new DataBase();
                String sql = "SELECT username,password,power FROM tb_operator";
                db.selectDb(sql);
                boolean flag = false;//���ڱ�ʾ��¼��״̬��trueΪ��¼�ɹ���falseΪ��¼ʧ��
                try {
                    while (db.rs.next()) {
                        String s1 = db.rs.getString(1).trim();
                        String s2 = db.rs.getString(2).trim();
                        String s3 = db.rs.getString(3).trim();
                        if ((user.equals(s1)) && (password.equals(s2))) {
                            if (power.equals("����Ա") && s3.equals("����Ա")) {
                                //����Ա����
                                showManagerFrame();
                                flag = true;
                            } else if (power.equals("����Ա") && s3.equals("����Ա")) {
                                //����������
                                showLoginFrame();
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "�û�Ȩ�޴���,����ȷѡ���û�Ȩ�ޣ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
                                flag = false;
                            }
                        }
                    }
                    if (!flag) {//�����¼ʧ��
                        JOptionPane.showMessageDialog(null, "�û�����������������µ�¼����", "������Ϣ", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });


        //�Ե�¼��ť�����¼�����
        loginButton.addActionListener(e -> {
            String user = nameTextField.getText();
            String password = new String(passwordField.getPassword());
            String power = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();

            if ((user.equals("")) || (password.equals(""))) {
                JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            } else {
                DataBase db = new DataBase();
                String sql = "SELECT username,password,power FROM tb_operator";
                db.selectDb(sql);
                boolean flag = false;//���ڱ�ʾ��¼��״̬��trueΪ��¼�ɹ���falseΪ��¼ʧ��
                try {
                    while (db.rs.next()) {
                        String s1 = db.rs.getString(1).trim();
                        String s2 = db.rs.getString(2).trim();
                        String s3 = db.rs.getString(3).trim();
                        if ((user.equals(s1)) && (password.equals(s2))) {
                            if (power.equals("����Ա") && s3.equals("����Ա")) {
                                showManagerFrame();
                                flag = true;
                            } else if (power.equals("����Ա") && s3.equals("����Ա")) {
                                showLoginFrame();
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "�û�Ȩ�޴���,����ȷѡ���û�Ȩ�ޣ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
                                flag = true;
                            }
                        }
                    }
                    if (!flag) {
                        JOptionPane.showMessageDialog(null, "�û�����������������µ�¼����", "������Ϣ", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        //��ȡ����ť�����¼�����
        cancelButton.addActionListener(e -> {
            System.exit(0);
            showLoginFrame();
        });

    }

    public static void main(String[] args) {
        new Login("��Ʒ����ϵͳ");
    }

    //��ʾ�����ڲ��ͷŵ�¼����
    private void showLoginFrame() {
        new LoginFrame();
        this.dispose();
    }

    //��ʾ����Ա���沢�ͷŵ�¼����
    private void showManagerFrame() {
        new Administrators();
        this.dispose();
    }
}

//��½���� ����ͼƬ����
class ImagePanelJxc extends JPanel {
    private Image backgroundImage;

    ImagePanelJxc(String imageName) {
        backgroundImage = new ImageIcon(imageName).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, 400, 250, this);
    }
}








