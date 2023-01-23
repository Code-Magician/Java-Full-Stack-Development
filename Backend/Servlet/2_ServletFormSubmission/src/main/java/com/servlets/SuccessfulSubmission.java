package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessfulSubmission extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String name 		= req.getParameter("name");
		String password 	= req.getParameter("password");
		String email 		= req.getParameter("email");
		String gender 		= req.getParameter("gender");
		String language		= req.getParameter("language");
		
		out.println("<h1>You have been Successfully Registered</h1><br>");
		out.println("Name : " + name);
		out.println("<br>");
		out.println("Password : " + password);
		out.println("<br>");
		out.println("email : " + email);
		out.println("<br>");
		out.println("gender : " + gender);
		out.println("<br>");
		out.println("Programming Language : " + language);
	}
}
