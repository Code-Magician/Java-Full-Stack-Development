package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String condition	= req.getParameter("terms");
		
		if(condition != null && condition.equals("checked"))
		{
			RequestDispatcher rd = req.getRequestDispatcher("successful");
			rd.forward(req, resp);
		}
		else 
		{
			PrintWriter out = resp.getWriter();
			out.println("<h1>Accept the Terms and Conditions</h1><br>");
			
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);
		}
	}
}
