package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DbContext;

/**
 * Servlet implementation class deletePerson
 */
@WebServlet("/deletePerson")
public class deletePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletePerson() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String id = request.getParameter("id");
    	
        // Criar uma nova instância de DbContext
        DbContext dbContext = new DbContext();

        try {
            // Verifica se o id não é nulo ou vazio
            if (id != null && !id.isEmpty()) {
                int personId = Integer.parseInt(id);
                // Utilizar DbPessoa para eliminar a pessoa
                dbContext.getDbPerson().deletePersonById(personId);
            } else {
                request.setAttribute("errorMessage", "ID inválido.");
            }

            // Redirecionar de volta para a página que lista as pessoas
            response.sendRedirect("getPersons");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Erro ao converter ID: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("getPersons");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erro ao eliminar a pessoa: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("getPersons");
            dispatcher.forward(request, response);
        } finally {
            // Fechar a conexão ao fim da operação
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
