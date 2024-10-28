package com.app;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DbContext;
import Models.Person;


/**
 * Servlet implementation class getPersons
 */
@WebServlet("/getPersons")
public class getPersons extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DbContext dbContext = new DbContext();
	    List<Person> persons = new ArrayList<>();

	    try {
	        dbContext = new DbContext(); 
	       
	        persons =  dbContext.getDbPerson().getPersons(); 

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Opcional: adicionar um atributo para exibir uma mensagem de erro na página JSP
	        request.setAttribute("errorMessage", "Erro ao obter a lista de pessoas.");
	    } finally {
	    	// Fechar a conexão no fim da operação
            dbContext.closeConnection();
	    }

	    // Envia a lista de pessoas para a página JSP
	    request.setAttribute("getPersons", persons);
	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
