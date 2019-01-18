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
 * Servlet implementation class CreateAccountComp
 */
@WebServlet("/CreateAccountComp")
public class CreateAccountComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountComp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("register")!=null){
			Account account = new Account();
			AccountDAO dao = new AccountDAO();

			HttpSession session = request.getSession();
			account = (Account) session.getAttribute("account");

			try{
				dao.create(account);
			}catch(SQLException e){
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/createComp.jsp").forward(request, response);
		}

		if(request.getParameter("toLogin")!=null){
			HttpSession session = request.getSession();
			session.invalidate();
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}
}
