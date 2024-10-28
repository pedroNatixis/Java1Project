package com.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.DbContext;

/**
 * Servlet implementation class logIn
 */
@WebServlet("/logIn")
public class logIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        // Criar uma nova instância de DbContext
	        DbContext dbContext = new DbContext();
	        
	        // Verificar se as credenciais estão corretas
	        boolean isAuthenticated = dbContext.getDbUser().authenticateUser(username, password);

	        if (isAuthenticated) {
	            // Criar uma nova sessão para o utilizador
	            HttpSession session = request.getSession();
	            session.setAttribute("loggedInUser", username);

	            // Redirecionar para a página inicial após login
	            response.sendRedirect("initialPage.jsp");
	        } else {
	            // Se as credenciais estiverem incorretas, redirecionar de volta com uma mensagem de erro
	            request.setAttribute("errorMessage", "Nome de utilizador ou senha incorretos.");
	            request.getRequestDispatcher("initialPage.jsp").forward(request, response);
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
