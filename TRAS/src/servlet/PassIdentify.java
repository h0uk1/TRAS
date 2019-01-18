package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class PassIdentify
 */
@WebServlet("/PassIdentify")
public class PassIdentify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassIdentify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("result", false);
		getServletContext().getRequestDispatcher("/passIdentify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("passIdentify")!=null){
			Account account = new Account();
			AccountDAO accountDao = new AccountDAO();
			boolean result = false;

			account.setUserName(request.getParameter("userName"));
			account.setSecretQuestion(request.getParameter("secretQuestion"));
			account.setSecretQuestionAnswer(request.getParameter("secretQuestionAnswer"));

			System.out.println("1");
			try {
				result=accountDao.fogotUserCheck(account);
				System.out.println("2");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(result==true){
				request.setAttribute("result", result);
				getServletContext().getRequestDispatcher("/passIdentify.jsp").forward(request, response);
			}else if(result==false){
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				getServletContext().getRequestDispatcher("/passReset.jsp").forward(request, response);
			}
		}
		if(request.getParameter("cancel")!=null){
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
