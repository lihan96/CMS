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
        //设置背景
        ImagePanelJxc loginImagePanel = new ImagePanelJxc("image/login.jpg");
        loginImagePanel.setLayout(null);

        //添加用户名输入框
        final JTextField nameTextField = new JTextField(5);
        nameTextField.setBounds(150, 50, 120, 27);
        loginImagePanel.add(nameTextField);

        //添加密码输入框
        final JPasswordField passwordField = new JPasswordField(5);
        passwordField.setEchoChar('*');
        passwordField.setBounds(150, 100, 120, 27);
        loginImagePanel.add(passwordField);

        //添加登陆按钮
        JButton loginButton = new JButton("登陆");
        loginButton.setBounds(50, 160, 80, 36);
        loginButton.setBackground(new Color(0, 152, 210));
        loginButton.setForeground(new Color(255, 255, 255));
        loginImagePanel.add(loginButton);

        //添加取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(150, 160, 80, 36);
        cancelButton.setBackground(new Color(0, 152, 210));
        cancelButton.setForeground(new Color(255, 255, 255));
        loginImagePanel.add(cancelButton);

        //添加组合框选择权限
        final JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("操作员");
        jComboBox.addItem("管理员");
        jComboBox.setBounds(250, 160, 100, 36);
        jComboBox.setBackground(new Color(255, 255, 255));
        loginImagePanel.add(jComboBox);

        //添加背景面板
        this.add(loginImagePanel);

        //设置登陆界面大小   以及在屏幕中央显示
        Dimension loginDimension;
        this.setSize(400, 250);
        loginDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (loginDimension.width - this.getWidth()) / 2;
        int y = (loginDimension.height - this.getHeight()) / 2;
        this.setLocation(x, y);

        //设置登陆界面为:可见、不可拉伸
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //对用户名框体进行事件监听
        nameTextField.addActionListener(e -> passwordField.requestFocus());

        //对密码框体进行事件监听   可以直接回车登录
        passwordField.addActionListener((ActionEvent e) -> {
            String user = nameTextField.getText();
            String password = new String(passwordField.getPassword());
            String power = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();

            if ((user.equals("")) || (password.equals(""))) {
                JOptionPane.showMessageDialog(null, "用户名和密码不能为空！！", "警告消息", JOptionPane.WARNING_MESSAGE);
            } else {
                DataBase db = new DataBase();
                String sql = "SELECT username,password,power FROM tb_operator";
                db.selectDb(sql);
                boolean flag = false;//用于表示登录的状态：true为登录成功，false为登录失败
                try {
                    while (db.rs.next()) {
                        String s1 = db.rs.getString(1).trim();
                        String s2 = db.rs.getString(2).trim();
                        String s3 = db.rs.getString(3).trim();
                        if ((user.equals(s1)) && (password.equals(s2))) {
                            if (power.equals("管理员") && s3.equals("管理员")) {
                                //管理员界面
                                showManagerFrame();
                                flag = true;
                            } else if (power.equals("操作员") && s3.equals("操作员")) {
                                //进入主界面
                                showLoginFrame();
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "用户权限错误,请正确选择用户权限！！", "警告消息", JOptionPane.WARNING_MESSAGE);
                                flag = false;
                            }
                        }
                    }
                    if (!flag) {//如果登录失败
                        JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录！！", "错误消息", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });


        //对登录按钮进行事件监听
        loginButton.addActionListener(e -> {
            String user = nameTextField.getText();
            String password = new String(passwordField.getPassword());
            String power = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();

            if ((user.equals("")) || (password.equals(""))) {
                JOptionPane.showMessageDialog(null, "用户名和密码不能为空！！", "警告消息", JOptionPane.WARNING_MESSAGE);
            } else {
                DataBase db = new DataBase();
                String sql = "SELECT username,password,power FROM tb_operator";
                db.selectDb(sql);
                boolean flag = false;//用于表示登录的状态：true为登录成功，false为登录失败
                try {
                    while (db.rs.next()) {
                        String s1 = db.rs.getString(1).trim();
                        String s2 = db.rs.getString(2).trim();
                        String s3 = db.rs.getString(3).trim();
                        if ((user.equals(s1)) && (password.equals(s2))) {
                            if (power.equals("管理员") && s3.equals("管理员")) {
                                showManagerFrame();
                                flag = true;
                            } else if (power.equals("操作员") && s3.equals("操作员")) {
                                showLoginFrame();
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "用户权限错误,请正确选择用户权限！！", "警告消息", JOptionPane.WARNING_MESSAGE);
                                flag = true;
                            }
                        }
                    }
                    if (!flag) {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录！！", "错误消息", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        //对取消按钮进行事件监听
        cancelButton.addActionListener(e -> {
            System.exit(0);
            showLoginFrame();
        });

    }

    public static void main(String[] args) {
        new Login("商品管理系统");
    }

    //显示主窗口并释放登录窗口
    private void showLoginFrame() {
        new LoginFrame();
        this.dispose();
    }

    //显示管理员界面并释放登录窗口
    private void showManagerFrame() {
        new Administrators();
        this.dispose();
    }
}

//登陆界面 背景图片设置
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








