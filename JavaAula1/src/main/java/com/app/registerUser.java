package com.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.DbContext;
import Models.User;

/**
 * Servlet implementation class registUser
 */
@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String username = request.getParameter("username");
	     String email = request.getParameter("email");
	     String password = request.getParameter("password");

		User user = new User(username, password, email);
		
		 // Criar uma nova instância de DbContext
	    DbContext dbContext = new DbContext();
	    
	    try {
	        // Verificar se já existe algum utilizador com aquele email
	        boolean userEmailExists = dbContext.getDbUser().userEmailExists(email);

	        if (userEmailExists) {
	            request.setAttribute("errorMessage", "Já existe um utilizador com esse email.");
	            request.getRequestDispatcher("initialPage.jsp").forward(request, response);
	            return;
	        }

	        // Verificar se já existe algum utilizador com aquele username
	        boolean userUserNameExists = dbContext.getDbUser().userUserNameExists(username);

	        if (userUserNameExists) {
	            request.setAttribute("errorMessage", "Já existe um utilizador com esse UserName.");
	            request.getRequestDispatcher("initialPage.jsp").forward(request, response);
	            return;
	        }

	        // Pode criar o user
	        boolean isRegistered = dbContext.getDbUser().addUser(user);

	        if (isRegistered) {
	            // Se o registo for bem-sucedido, criar a sessão
	            HttpSession session = request.getSession();
	            session.setAttribute("loggedInUser", username);

	            // Redirecionar para a página inicial
	            response.sendRedirect("initialPage.jsp");
	        } else {
	            // Se o registo falhar, redirecionar de volta com uma mensagem de erro
	            request.setAttribute("errorMessage", "Erro ao registar Utilizador.");
	            request.getRequestDispatcher("initialPage.jsp").forward(request, response);
	        }
	    } finally {
	        // Fechar o DbContext se este tiver método close
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
