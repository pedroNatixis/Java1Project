package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DbContext;
import Models.Person;

/**
 * Servlet implementation class addPerson
 */
@WebServlet("/addPerson")
public class addPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addPerson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");

	    String name = request.getParameter("name");
	    String age = request.getParameter("age");
	    String email = request.getParameter("email");

	    int ageInt; // Variável para armazenar a idade como int

	    try {
	        // Tenta converter a idade de String para int
	        ageInt = Integer.parseInt(age);
	    } catch (NumberFormatException e) {
	        // Se a conversão falhar, define a mensagem de erro e redireciona
	        request.setAttribute("errorMessage", "Erro: A idade deve ser um número inteiro.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("getPersons");
	        dispatcher.forward(request, response);
	        return; // Sai do método
	    }

	    Person person = new Person(name, ageInt, email);

	    // Criar uma nova instância de DbContext
	    DbContext dbContext = new DbContext();

	    try {
	        // Utilizar DbPessoa para adicionar a pessoa
	        dbContext.getDbPerson().addPerson(person);
	        
	        // Redirecionar para o servlet que busca as pessoas
	        response.sendRedirect("getPersons");
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Erro ao adicionar a pessoa: " + e.getMessage());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("getPersons");
	        dispatcher.forward(request, response);
	    } finally {
	        // Fechar a conexão no fim da operação
	        dbContext.closeConnection();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
