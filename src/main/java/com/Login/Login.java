package com.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		try {
			// Initialize all the information regarding
			// Database Connection
			String dbDriver = "com.mysql.cj.jdbc.Driver";
			String dbURL = "jdbc:mysql:// localhost:3306/";
			// Database name to access
			String dbName = "Register";
			String dbUsername = "root";
			String dbPassword = "root@123";

			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
			
			String email=req.getParameter("email");
			String pass=req.getParameter("pass");
			
			PreparedStatement pstmt=con.prepareStatement("select email,pass from user where email=? and pass=?");
			
			pstmt.setString(1,email);
			pstmt.setString(2, pass);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("home.html");
				rd.forward(req, resp);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, resp);
				pw.println("<p><center><b>Note : </b>Please Checked Email and Password.</center><p>");

			}
 

		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}
	
	
}
