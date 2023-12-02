package com.itsc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.*;
import com.mysql.jdbc.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
	public class RegistrationServlet extends HttpServlet {
		DBConnection dbConn = new DBConnection();
		protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException{
		// Retrieve form data
		String name = rq.getParameter("name");
		String email = rq.getParameter("email");
		String password = rq.getParameter("password");
		boolean isRegistered = dbConn.Register(name, email, password);
		if(isRegistered) {
			rp.sendRedirect("confirmation.jsp");
		}
		
		
	}
}

