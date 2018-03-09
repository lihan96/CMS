package com.lihan.LoginSystem;

import com.lihan.Dao.DataBase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.Vector;
import javax.swing.*;

public class Administrators extends JFrame implements ActionListener {
	private JLabel[] jl = {
	        new JLabel("用户名(username)"),
            new JLabel("密码(password)"),
            new JLabel("用户全称(name)"),
            new JLabel("权限")
	};
	private JTextField[] jt = {new JTextField(),new JTextField(),new JTextField()};
	private final JComboBox<String> jComboBox= new JComboBox<>();
	private JButton[] jb = {new JButton("查询"),new JButton("添加"),new JButton("更改"),new JButton("删除")};
    private String[] cloNames = {"username","password","name","power"};
	private JTable jtable;
	private CustomTableModel tableModel;

    Administrators(){
	    ImagePanelAdmin imagePanelAdmin = new ImagePanelAdmin("image/background.jpg");

	    this.setTitle("欢迎登录管理员界面");
		this.setSize(600, 450);
		this.setResizable(false);
		
		//设置在屏幕的中间显示
		Dimension dimension;
        dimension =Toolkit.getDefaultToolkit().getScreenSize();
        int x= (dimension.width - this.getWidth())/2;
        int y= (dimension.height- this.getHeight())/2;
        this.setLocation(x, y);
        
		imagePanelAdmin.setLayout(null);

		//添加标签
		for(int i = 0;i < jl.length;i++){
			jl[i].setBounds(10,30*i+20,140,30);
			imagePanelAdmin.add(jl[i]);
		}
		
		//添加文本框
		for(int i = 0;i < jt.length;i++){
			jt[i].setBounds(120,30*i+20,150,25);
			imagePanelAdmin.add(jt[i]);
		}

		this.jt[1].addCaretListener(e -> {
            int p = (jt[1].getText()).length();
            if(p >10){
                JOptionPane.showMessageDialog(null, "密码重置过长！！","警告消息",JOptionPane.WARNING_MESSAGE);
            }
        });
		
		//添加下拉列表框
		jComboBox.setBounds(120,110,150,25);
		jComboBox.addItem("管理员");
		jComboBox.addItem("操作员");
		jComboBox.setBackground(Color.white);
		imagePanelAdmin.add(jComboBox);
		//添加操作按钮
		for(int i = 0;i < jb.length;i++){
			jb[i].setBounds(500,30*i+20,70,25);
			jb[i].addActionListener(this);
			jb[i].setBackground(new Color(0, 152, 210));
			imagePanelAdmin.add(jb[i]);
		}
	
		//设置表格
        Class[] dataType = new Class[]{String.class, String.class, String.class, String.class};
        tableModel = new CustomTableModel(0, cloNames.length, cloNames, dataType);
		jtable = new JTable(tableModel);

		//向模型中添加数据
        Object[][] data = {{"", "", "", ""}, {"", "", "", ""}};
        for (Object[] aData : data) {
            tableModel.addRow(aData);
        }
		JScrollPane scroll = new JScrollPane(jtable);//添加滚动条
		scroll.setBounds(10, 190, 575, 220);
		scroll.setBorder(BorderFactory.createTitledBorder("用户基本信息"));
        imagePanelAdmin.add(scroll);
        
        this.add(imagePanelAdmin);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jb[0]){//查询
			this.query();
		}else if(e.getSource() == jb[1]){//添加
			this.insert();
		}else if(e.getSource() == jb[2]){//更改
			this.change();
		}else{//删除
			this.delete();
		}
	}
	
	//查询
    private void query() {
        String[] sQuery = GetInput();
        
        sQuery[0]= "%" + sQuery[0] + "%";
        sQuery[1] = "%" +sQuery[1] + "%";
        sQuery[2] = "%" +sQuery[2] + "%";
        if(!this.jt[0].getText().equals("")){
            sQuery[0] = "%"+sQuery[0]+"%";
            sQuery[2] = "%"+sQuery[2]+"%";

            String sql = "SELECT * FROM tb_operator WHERE username LIKE '"+sQuery[0]+"' AND password LIKE '"
                    +sQuery[1]+"'AND name LIKE '"+sQuery[2]+"'" +"And power = '"+sQuery[3]+"'";
            DataBase db = new DataBase();
            Vector<String[]> vTemp = db.Select(sql, cloNames.length);
            int p = 0;
            int q = vTemp.size();
            int r = this.tableModel.getRowCount();//得到之前的行数

            if(r > q){
                while((r-q)>0){
                    this.tableModel.removeRow(r-1);
                    r = this.tableModel.getRowCount();
                }
            }else if(r <= q){
                for(int i = 0;i < q-r;i++){
                    this.tableModel.addRow(new Object[]{});
                }
            }
            for (String[] str : vTemp) {
                for (int i = 0; i < cloNames.length; i++) {
                    jtable.setValueAt(str[i], p, i);//向表格中添加数据
                }
                p++;
                if (p == q)
                    break;
            }
            db.dbClose();
        }else{
            JOptionPane.showMessageDialog(null, "该用户信息不存在，请重新输入用户名！！","错误消息",JOptionPane.ERROR_MESSAGE);
        }
    }

	//插入
    private void insert(){
	    if(this.jt[0].getText().length() < 5){
	        JOptionPane.showMessageDialog(null, "用户名长度过短，请重新输入！！","警告消息",JOptionPane.WARNING_MESSAGE);
		}else if(this.jt[1].getText().length() < 5){
			JOptionPane.showMessageDialog(null, "密码强度不够，请重新输入！！","警告消息",JOptionPane.WARNING_MESSAGE);
		}else{
			boolean f ;
			f = Tell();
			if(f){
				DataBase db1 = new DataBase();
				String[] str = GetInput();
				boolean b = db1.TellKey("username", str[0], "tb_operator");		//判断用户名是否重复
				if(!b){
					DataBase db = new DataBase();
					String sql = "INSERT INTO tb_operator(username,password,name,power) VALUES('"+str[0]+"','"+str[1]+"'" +
					",'"+str[2]+"','"+str[3]+"')";
					db.updateDb(sql);
					JOptionPane.showMessageDialog(null,"添加成功！！");					
					db.dbClose();
				}else{
					JOptionPane.showMessageDialog(null,"添加失败！！\n用户名已经存在！！","错误消息",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	//更改
    private void change(){
        if(this.jt[0].getText().length() < 5){
            JOptionPane.showMessageDialog(null, "用户名长度过短，请重新输入！！","警告消息",JOptionPane.WARNING_MESSAGE);
        }else if(this.jt[1].getText().length() < 5){
            JOptionPane.showMessageDialog(null, "密码强度不够，请重新输入！！","警告消息",JOptionPane.WARNING_MESSAGE);
        }else{
            String[] str = GetInput();
            DataBase db1 = new DataBase();
            boolean b = db1.TellKey("username", str[0], "tb_operator");
            if(b){
                boolean bb = Tell();
                if(bb){
                    String sql = "UPDATE tb_operator SET password= '"+str[1]+"',name = '"
                            +str[2]+"'" +",power = '"+str[3]+"' WHERE username = '"+str[0]+"'";
                    DataBase db = new DataBase();
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(null, "更改成功！！");
                    db.dbClose();
                }
            }else{
                JOptionPane.showMessageDialog(null, "该用户信息不存在，请重新输入用户名！！","错误消息",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //删除
    private void delete(){
        String[] str = GetInput();
        DataBase db1 = new DataBase();
        boolean b = db1.TellKey("username", str[0], "tb_operator");
        if(b){
            int j = JOptionPane.showConfirmDialog(null, "你确定删除该用户信息吗？","警告消息",JOptionPane.YES_NO_OPTION);
            if(j==0){
                String sql = "DELETE FROM tb_operator WHERE username = '"+str[0]+"'";
                DataBase db = new DataBase();
                db.updateDb(sql);
                JOptionPane.showMessageDialog(null, "删除成功！！");
                db.dbClose();
            }
        }else{
            JOptionPane.showMessageDialog(null, "该用户信息不存在，请重新输入用户名！！","错误消息",JOptionPane.ERROR_MESSAGE);
        }
    }

	private boolean Tell(){//判断文本框是否为空
        boolean flag;
        String strings[] = GetInput();
        if(strings[0].equals("")){
            JOptionPane.showMessageDialog(null, jl[0].getText()+ "不能为空！！","警告消息",JOptionPane.WARNING_MESSAGE);
            flag = false;
        }else if(strings[1].equals("")){
            JOptionPane.showMessageDialog(null, jl[1].getText()+ "不能为空！！","警告消息",JOptionPane.WARNING_MESSAGE);
			flag = false;
        }else if(strings[2].equals("")){
            JOptionPane.showMessageDialog(null, jl[2].getText()+ "不能为空！！","警告消息",JOptionPane.WARNING_MESSAGE);
			flag = false;
        }else{
            flag = true;
        }
        return flag;
    }

    private String[] GetInput(){
	    String[] s = new String[4];
	    String s1 = jt[0].getText();//获取用户名
        String s2 = jt[1].getText();//获取用户名密码
        String s3 = jt[2].getText();//获取用户全称
        String s4 = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();
        s[0] = s1;
        s[1] = s2;
        s[2] = s3;
        s[3] = s4;
        return s;
	}

    protected void processWindowEvent(WindowEvent e){
		if(e.getID() == WindowEvent.WINDOW_CLOSING) {
		    int j = JOptionPane.showConfirmDialog(null, "是否退出系统？？","警告消息",JOptionPane.YES_NO_OPTION);
			if(j == 0){
				System.exit(0);
			}
		}
	}
}

class ImagePanelAdmin extends JPanel {
    private Image backgroundImage;
    ImagePanelAdmin(String imageName) {
        backgroundImage = new ImageIcon(imageName).getImage();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage ,0, 0, 600,450, this);
    }
}
