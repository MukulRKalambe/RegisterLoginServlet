package com.Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("birthday");
		String address = req.getParameter("address");
		String course = req.getParameter("course");
		String condition = req.getParameter("condition");

		if (condition != null) {

			if (email.endsWith(".com")) {

				// jdbc connection and send data to database

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
					String q = ("insert into user(name,email,pass,gender,dob,address,course) values(?,?,?,?,?,?,?)");

					PreparedStatement pstmt = con.prepareStatement(q);

					pstmt.setString(1, name);
					pstmt.setString(2, email);
					pstmt.setString(3, pass);
					pstmt.setString(4, gender);
					pstmt.setString(5, dob);
					pstmt.setString(6, address);
					pstmt.setString(7, course);

					pstmt.executeUpdate();
					
					RequestDispatcher rd = req.getRequestDispatcher("index.html");
					rd.include(req, resp);

					pw.println("<p><center><b>Note : </b>Register Sucessfully.</center><p>");


				} catch (Exception e) {
					System.out.println(e);
					pw.print(e);
				}
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, resp);

				pw.println("<p><center><b>Note : </b>Please filled all details.</center><p>");

			}

		} else {

			// request dispatcher using include

			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);

			pw.println("<p><center><b>Note : </b>Please Agree terms and condition.</center><p>");

		}

		pw.close();

	}

}
