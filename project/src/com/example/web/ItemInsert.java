package com.example.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.*;
import java.sql.*;

public class ItemInsert extends HttpServlet{
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {

		String bc = request.getParameter("barcode");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String des = request.getParameter("description");

		
		String url = "jdbc:mysql://localhost:3306/productsdb";
		String dbUser = "root";
		String dbPassword = "ksql4321!";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(url, dbUser, dbPassword); 
			Statement myStmt = myConn.createStatement();
			
			ResultSet searchResult = myStmt.executeQuery("select * from items " + " where barcode='" + bc + "';");
			
			if(!searchResult.next()) {
				myStmt.executeUpdate("insert into items " + " (barcode, name, color, description) " + " values('" + bc + "', '" + name + "', '"
						+ color + "','" + des + "');");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fail.jsp");
				dispatcher.forward(request, response);
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
