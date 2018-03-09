package com.lihan.Dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DataBase {
	private Connection con = null;//���ݿ�����
	private Statement stat ;//���ݿ����
	public ResultSet rs ;//��������� 

	public DataBase(){
		try {
			//���� MySQL ����
			Class.forName("com.mysql.jdbc.Driver");
			//�������ݿ�ϵͳ
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_products","root","123456");
			stat=con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//���ݿ��ѯ
	public void selectDb(String sql){
		try {
			sql = new String(sql.getBytes(),"UTF-8");
			rs = stat.executeQuery(sql);//ִ�в�ѯ
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	//���ݿ���£���ӡ��޸ġ�ɾ��
	public void updateDb(String sql){
		try {
			sql = new String(sql.getBytes(),"UTF-8");
			stat.executeUpdate(sql);//ִ�и���
		} catch (UnsupportedEncodingException | SQLException e) {
			e.printStackTrace();
		}
	}

	//���ò�ѯ����ѯ�����Ƿ����
	public boolean TellKey(String key, String value, String tableName){
		boolean flag = false;		
		String sql = "SELECT "+key+" FROM "+tableName+" WHERE "+key+" = '"+value+"'";
		selectDb(sql);
		try {
			while(rs.next()){
				String keyValue = rs.getString(1).trim();
				if(value.equals(keyValue)){
					flag = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return flag;
	}

	public Vector<String[]> Select(String sql,int n){
		Vector<String[]> strings = new Vector<>();
		selectDb(sql);
		try {
			while(rs.next()){
				String[] tb_customer = new String[n];
				for(int i = 0;i < n;i++){
					tb_customer[i] = rs.getString(i+1);
				}
				strings.add(tb_customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return strings;
	}
	
	//�ر����ݿ�
	public void dbClose(){
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
}