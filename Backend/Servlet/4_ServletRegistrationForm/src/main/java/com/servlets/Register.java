package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

//		Getting Parameters
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

//		Database location
		String dbLocation = "E:\\\\Resources\\\\Java Full Stack Development\\\\Backend\\\\Servlet\\\\4_ServletRegistrationForm\\\\src\\\\main\\\\webapp\\\\Database\\\\users.db";

		
		
		try {
//			Loading Database Driver class for sqlite database.
			Class.forName("org.sqlite.JDBC");
			
//			Making Connection to database
			Connection con = DriverManager.getConnection("jdbc:sqlite:"+dbLocation);

//			Query to insert data into database.
			String query = "insert into Users(name, password, email) values(?,?,?)";
			
//			Making prepared statement to replace '?' with some data in query string.
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			
//			Executing the query.
			statement.executeUpdate();
			
			out.println("<h1>Success...</h1>");
			
		} catch (Exception e) {
			out.println("<h1>Error...</h1>");
			e.printStackTrace();
		}
	}
}
