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
 * Servlet implementation class PassReset
 */
@WebServlet("/PassReset")
public class PassReset extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("chenge")!=null){
			Account account = (Account)session.getAttribute("account");
			AccountDAO accountDao = new AccountDAO();

			account.setPass(request.getParameter("newPass"));

			try {
				accountDao.passReset(account);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/passResetComp.jsp").forward(request, response);
		}
		if(request.getParameter("cancel")!=null){
			session.invalidate();
			request.setAttribute("result", false);
			getServletContext().getRequestDispatcher("/passIdentify.jsp").forward(request, response);
		}
		if(request.getParameter("toLogin")!=null){
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
