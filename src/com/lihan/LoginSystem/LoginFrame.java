package com.lihan.LoginSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.lihan.BaseInfoManager.BaseInfoManager;
import com.lihan.QueryManager.QueryManager;
import com.lihan.StoreManager.StoreManger;
import com.lihan.SellManager.SellManger;
import com.lihan.SystemManager.SystemManger;

public class LoginFrame extends JFrame{

    LoginFrame(){
        super("��Ʒ����ϵͳ");
        JButton jButton1 = new JButton("��Ϣ����");
        JButton jButton2 = new JButton("�ֿ����");
        JButton jButton3 = new JButton("���۹���");
        JButton jButton4 = new JButton("��ѯ����");
        JButton jButton5 = new JButton("ϵͳ����");
        JButton jButton6 = new JButton("�˳�ϵͳ");

		jButton1.setBounds(0, 0, 200, 80);
		jButton2.setBounds(0, 80, 200, 80);
		jButton3.setBounds(0, 160, 200, 80);
		jButton4.setBounds(0, 240, 200, 80);
		jButton5.setBounds(0, 320, 200, 80);
		jButton6.setBounds(0, 400, 200, 80);
		
		jButton1.setBackground(new Color(0, 152, 210));
		jButton2.setBackground(new Color(0, 152, 210));
		jButton3.setBackground(new Color(0, 152, 210));
		jButton4.setBackground(new Color(0, 152, 210));
		jButton5.setBackground(new Color(0, 152, 210));
		jButton6.setBackground(new Color(0, 152, 210));
		
		JPanel jPanel = new JPanel();
		jPanel.setSize(200,510);
		jPanel.setLayout(null);

		jPanel.add(jButton1);
		jPanel.add(jButton2);
		jPanel.add(jButton3);
		jPanel.add(jButton4);
		jPanel.add(jButton5);
		jPanel.add(jButton6);
		
		//���÷ָ����
		LoginFrameImagePanel loginFrameImagePanel = new LoginFrameImagePanel("image/welcome.jpg");
		final JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jPanel, loginFrameImagePanel);
        jSplitPane.setDividerLocation(200);
        jSplitPane.setOneTouchExpandable(false);
        jSplitPane.setEnabled(false);

		this.setSize(800, 510);
		this.add(jSplitPane);
		Dimension loginFrameDimension;
		loginFrameDimension =Toolkit.getDefaultToolkit().getScreenSize();
        int x= (loginFrameDimension.width - this.getWidth())/2;
        int y= (loginFrameDimension.height - this.getHeight())/2;
        this.setLocation(x, y);

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//��ʼ�¼�����
		//�Ի�����Ϣ����ť�����¼����� (ģ��1)
		final BaseInfoManager baseInfoManger = new BaseInfoManager();
		jButton1.addActionListener(e -> {
		    jSplitPane.setDividerLocation(200);
		    jSplitPane.setRightComponent(baseInfoManger);
        });
		
		//�Խ�������ť���м��� (ģ��2)
		final StoreManger restockManger = new StoreManger();
		jButton2.addActionListener(e -> {
            jSplitPane.setDividerLocation(200);
            jSplitPane.setRightComponent(restockManger);
        });
		//�����۹���ť�����¼����� (ģ��3)
		final SellManger sellManger = new SellManger();
		jButton3.addActionListener(e -> {
            jSplitPane.setDividerLocation(200);
            jSplitPane.setRightComponent(sellManger);
        });
		
	    //�Բ�ѯ����ť�����¼����� (ģ��4)
		final QueryManager queryManager = new QueryManager();
		jButton4.addActionListener(e -> {
            jSplitPane.setDividerLocation(200);
            jSplitPane.setRightComponent(queryManager);
        });

		//��ϵͳ��������¼����� (ģ��5)
		final SystemManger systemManger = new SystemManger();
		jButton5.addActionListener(e -> {
            jSplitPane.setDividerLocation(200);
            jSplitPane.setRightComponent(systemManger);
        });
		
        //���˳���ť�����¼����� (ģ��6)
		jButton6.addActionListener(e -> {
		    int i=JOptionPane.showConfirmDialog(LoginFrame.this,"��ȷ��Ҫ�˳���","��Ϣ",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
		    if(i==0) System.exit(0);
        });
	}

	protected void processWindowEvent(WindowEvent e){
		if(e.getID() == WindowEvent.WINDOW_CLOSING) {
			int j = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ����","������Ϣ",JOptionPane.YES_NO_OPTION);
			if(j == 0) System.exit(0);
		}	
	}
}

class LoginFrameImagePanel extends JPanel{

    private Image backGroundImage;
	
	LoginFrameImagePanel(String imageName){
	    backGroundImage =new ImageIcon(imageName).getImage();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, 600, 510, this);
    }
}