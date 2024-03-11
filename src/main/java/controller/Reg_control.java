package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.Reg_model;

public class Reg_control {
	static String dbUrl="jdbc:mysql://localhost:3306/onlinesite";
	static String dbname="root";
	static String dbpass="";
	static String dbDriver="com.mysql.cj.jdbc.Driver";
	Reg_model model;
	public Reg_control(Reg_model model) {
		this.model=model;	
	} 
		public boolean toStore() {
			try {
				Class.forName(dbDriver);
				Connection con =DriverManager.getConnection(dbUrl,dbname,dbpass);
				String sql="insert into entry values('"+model.getUname()+"','"+model.getEmail()+"','"+model.getpassword()+"','"+model.getPhoneNo()+"')";
				Statement s =con.createStatement();
				s.execute(sql);
				con.close();
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return true;
		}			
}
