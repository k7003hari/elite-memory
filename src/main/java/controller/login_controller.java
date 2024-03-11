package controller;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;

import model.log_model;

	public class login_controller {
		static String dbUrl="jdbc:mysql://localhost:3306/onlinesite";
		static String dbName="root";
		static String dbPass="";
		static String dbDriver="com.mysql.cj.jdbc.Driver";
		log_model model;
		public login_controller(log_model model) {
			this.model=model;
		}
		public boolean check() {
			try {
				Class.forName(dbDriver);
				Connection con=DriverManager.getConnection(dbUrl,dbName,dbPass);
				String sql="SELECT * FROM entry WHERE username='"+model.getUname()+"' and password='"+model.getPass()+"'";
				Statement s=con.createStatement();
				ResultSet res=s.executeQuery(sql);
				if(res.next()) {
					return true;
				}
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			return false;
		}

	}

