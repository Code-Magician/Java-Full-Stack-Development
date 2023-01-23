package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;

public class Servlet_Type2 extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.println("<h1>Servlet Created Using GenericServlet Class.</h1>");
	}

}
