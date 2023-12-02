package com.itsc;
import java.io.IOException;
import java.sql.DriverManager;

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
import com.mysql.jdbc.ResultSet;
public class LoginServlet extends HttpServlet {
	DBConnection dbConn = new DBConnection();
	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException{
		String email = rq.getParameter("email");
		String password = rq.getParameter("password");
		String name = dbConn.Login(email, password);
		if(name != null) {
				HttpSession session = rq.getSession();
				session.setAttribute("name", name);				
				rp.sendRedirect("welcome.jsp");
		}
		else {
				//authentication failed
			rp.sendRedirect("login.jsp");
		}
			
			
		}
		
	}


