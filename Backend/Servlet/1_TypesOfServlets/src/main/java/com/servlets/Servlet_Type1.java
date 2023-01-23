package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;



public class Servlet_Type1 implements Servlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Creating Servlet");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.println("<h1> Servlet Created using Servlet Interface.</h1>");
		
		System.out.println("Servicing");
	}
	
	@Override
	public void destroy() {
		System.out.println("Deleting Servlet");
	}

	
	

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	
	@Override
	public String getServletInfo() {
		return "First Servlet created by Warloack Perry.";
	}
}
