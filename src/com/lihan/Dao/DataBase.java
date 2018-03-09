package com.lihan.Dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DataBase {
	private Connection con = null;//数据库连接
	private Statement stat ;//数据库操作
	public ResultSet rs ;//声明结果集 

	public DataBase(){
		try {
			//加载 MySQL 驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库系统
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_products","root","123456");
			stat=con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//数据库查询
	public void selectDb(String sql){
		try {
			sql = new String(sql.getBytes(),"UTF-8");
			rs = stat.executeQuery(sql);//执行查询
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	//数据库更新：添加、修改、删除
	public void updateDb(String sql){
		try {
			sql = new String(sql.getBytes(),"UTF-8");
			stat.executeUpdate(sql);//执行更新
		} catch (UnsupportedEncodingException | SQLException e) {
			e.printStackTrace();
		}
	}

	//利用查询，查询主键是否存在
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
	
	//关闭数据库
	public void dbClose(){
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
}