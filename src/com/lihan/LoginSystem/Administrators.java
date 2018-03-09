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
	        new JLabel("�û���(username)"),
            new JLabel("����(password)"),
            new JLabel("�û�ȫ��(name)"),
            new JLabel("Ȩ��")
	};
	private JTextField[] jt = {new JTextField(),new JTextField(),new JTextField()};
	private final JComboBox<String> jComboBox= new JComboBox<>();
	private JButton[] jb = {new JButton("��ѯ"),new JButton("���"),new JButton("����"),new JButton("ɾ��")};
    private String[] cloNames = {"username","password","name","power"};
	private JTable jtable;
	private CustomTableModel tableModel;

    Administrators(){
	    ImagePanelAdmin imagePanelAdmin = new ImagePanelAdmin("image/background.jpg");

	    this.setTitle("��ӭ��¼����Ա����");
		this.setSize(600, 450);
		this.setResizable(false);
		
		//��������Ļ���м���ʾ
		Dimension dimension;
        dimension =Toolkit.getDefaultToolkit().getScreenSize();
        int x= (dimension.width - this.getWidth())/2;
        int y= (dimension.height- this.getHeight())/2;
        this.setLocation(x, y);
        
		imagePanelAdmin.setLayout(null);

		//��ӱ�ǩ
		for(int i = 0;i < jl.length;i++){
			jl[i].setBounds(10,30*i+20,140,30);
			imagePanelAdmin.add(jl[i]);
		}
		
		//����ı���
		for(int i = 0;i < jt.length;i++){
			jt[i].setBounds(120,30*i+20,150,25);
			imagePanelAdmin.add(jt[i]);
		}

		this.jt[1].addCaretListener(e -> {
            int p = (jt[1].getText()).length();
            if(p >10){
                JOptionPane.showMessageDialog(null, "�������ù�������","������Ϣ",JOptionPane.WARNING_MESSAGE);
            }
        });
		
		//��������б��
		jComboBox.setBounds(120,110,150,25);
		jComboBox.addItem("����Ա");
		jComboBox.addItem("����Ա");
		jComboBox.setBackground(Color.white);
		imagePanelAdmin.add(jComboBox);
		//��Ӳ�����ť
		for(int i = 0;i < jb.length;i++){
			jb[i].setBounds(500,30*i+20,70,25);
			jb[i].addActionListener(this);
			jb[i].setBackground(new Color(0, 152, 210));
			imagePanelAdmin.add(jb[i]);
		}
	
		//���ñ��
        Class[] dataType = new Class[]{String.class, String.class, String.class, String.class};
        tableModel = new CustomTableModel(0, cloNames.length, cloNames, dataType);
		jtable = new JTable(tableModel);

		//��ģ�����������
        Object[][] data = {{"", "", "", ""}, {"", "", "", ""}};
        for (Object[] aData : data) {
            tableModel.addRow(aData);
        }
		JScrollPane scroll = new JScrollPane(jtable);//��ӹ�����
		scroll.setBounds(10, 190, 575, 220);
		scroll.setBorder(BorderFactory.createTitledBorder("�û�������Ϣ"));
        imagePanelAdmin.add(scroll);
        
        this.add(imagePanelAdmin);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jb[0]){//��ѯ
			this.query();
		}else if(e.getSource() == jb[1]){//���
			this.insert();
		}else if(e.getSource() == jb[2]){//����
			this.change();
		}else{//ɾ��
			this.delete();
		}
	}
	
	//��ѯ
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
            int r = this.tableModel.getRowCount();//�õ�֮ǰ������

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
                    jtable.setValueAt(str[i], p, i);//�������������
                }
                p++;
                if (p == q)
                    break;
            }
            db.dbClose();
        }else{
            JOptionPane.showMessageDialog(null, "���û���Ϣ�����ڣ������������û�������","������Ϣ",JOptionPane.ERROR_MESSAGE);
        }
    }

	//����
    private void insert(){
	    if(this.jt[0].getText().length() < 5){
	        JOptionPane.showMessageDialog(null, "�û������ȹ��̣����������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
		}else if(this.jt[1].getText().length() < 5){
			JOptionPane.showMessageDialog(null, "����ǿ�Ȳ��������������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
		}else{
			boolean f ;
			f = Tell();
			if(f){
				DataBase db1 = new DataBase();
				String[] str = GetInput();
				boolean b = db1.TellKey("username", str[0], "tb_operator");		//�ж��û����Ƿ��ظ�
				if(!b){
					DataBase db = new DataBase();
					String sql = "INSERT INTO tb_operator(username,password,name,power) VALUES('"+str[0]+"','"+str[1]+"'" +
					",'"+str[2]+"','"+str[3]+"')";
					db.updateDb(sql);
					JOptionPane.showMessageDialog(null,"��ӳɹ�����");					
					db.dbClose();
				}else{
					JOptionPane.showMessageDialog(null,"���ʧ�ܣ���\n�û����Ѿ����ڣ���","������Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	//����
    private void change(){
        if(this.jt[0].getText().length() < 5){
            JOptionPane.showMessageDialog(null, "�û������ȹ��̣����������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
        }else if(this.jt[1].getText().length() < 5){
            JOptionPane.showMessageDialog(null, "����ǿ�Ȳ��������������룡��","������Ϣ",JOptionPane.WARNING_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "���ĳɹ�����");
                    db.dbClose();
                }
            }else{
                JOptionPane.showMessageDialog(null, "���û���Ϣ�����ڣ������������û�������","������Ϣ",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //ɾ��
    private void delete(){
        String[] str = GetInput();
        DataBase db1 = new DataBase();
        boolean b = db1.TellKey("username", str[0], "tb_operator");
        if(b){
            int j = JOptionPane.showConfirmDialog(null, "��ȷ��ɾ�����û���Ϣ��","������Ϣ",JOptionPane.YES_NO_OPTION);
            if(j==0){
                String sql = "DELETE FROM tb_operator WHERE username = '"+str[0]+"'";
                DataBase db = new DataBase();
                db.updateDb(sql);
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�����");
                db.dbClose();
            }
        }else{
            JOptionPane.showMessageDialog(null, "���û���Ϣ�����ڣ������������û�������","������Ϣ",JOptionPane.ERROR_MESSAGE);
        }
    }

	private boolean Tell(){//�ж��ı����Ƿ�Ϊ��
        boolean flag;
        String strings[] = GetInput();
        if(strings[0].equals("")){
            JOptionPane.showMessageDialog(null, jl[0].getText()+ "����Ϊ�գ���","������Ϣ",JOptionPane.WARNING_MESSAGE);
            flag = false;
        }else if(strings[1].equals("")){
            JOptionPane.showMessageDialog(null, jl[1].getText()+ "����Ϊ�գ���","������Ϣ",JOptionPane.WARNING_MESSAGE);
			flag = false;
        }else if(strings[2].equals("")){
            JOptionPane.showMessageDialog(null, jl[2].getText()+ "����Ϊ�գ���","������Ϣ",JOptionPane.WARNING_MESSAGE);
			flag = false;
        }else{
            flag = true;
        }
        return flag;
    }

    private String[] GetInput(){
	    String[] s = new String[4];
	    String s1 = jt[0].getText();//��ȡ�û���
        String s2 = jt[1].getText();//��ȡ�û�������
        String s3 = jt[2].getText();//��ȡ�û�ȫ��
        String s4 = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();
        s[0] = s1;
        s[1] = s2;
        s[2] = s3;
        s[3] = s4;
        return s;
	}

    protected void processWindowEvent(WindowEvent e){
		if(e.getID() == WindowEvent.WINDOW_CLOSING) {
		    int j = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ����","������Ϣ",JOptionPane.YES_NO_OPTION);
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
