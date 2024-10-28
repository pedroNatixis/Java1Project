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
 * Servlet implementation class editPerson
 */
@WebServlet("/editPerson")
public class editPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPerson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		
		int ageInt;
		int idInt;

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
	    
	    try {
	        // Tenta converter a idade de String para int
	        idInt = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
	        // Se a conversão falhar, define a mensagem de erro e redireciona
	        request.setAttribute("errorMessage", "Erro: O id deve ser um inteiro.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("getPersons");
	        dispatcher.forward(request, response);
	        return; // Sai do método
	    }

	    Person person = new Person(idInt,name, ageInt, email);

	    // Criar uma nova instância de DbContext
	    DbContext dbContext = new DbContext();

	    try {
	        // Utilizar DbPessoa para adicionar a pessoa
	        dbContext.getDbPerson().updatePerson(person);
	        
	        // Redirecionar para o servlet que busca as pessoas
	        response.sendRedirect("getPersons");
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Erro ao atualizar as pessoa: " + e.getMessage());
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
