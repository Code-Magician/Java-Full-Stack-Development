package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet1 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = req.getRequestDispatcher("/servlet2");
		PrintWriter out = resp.getWriter();
		
		System.out.println(req.getParameter("num1"));
		System.out.println(req.getParameter("num2"));
		
		Integer num1 = Integer.parseInt(req.getParameter("num1"));
		Integer num2 = Integer.parseInt(req.getParameter("num2"));
		
		out.print("<h1>The sum of " + num1 + " + " + num2 +" is : </h1>");
		
		req.setAttribute("sum", num1+num2);
		rd.include(req, resp);
	}
}
