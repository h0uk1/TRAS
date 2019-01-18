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
 * Servlet implementation class AccountDeleteComp
 */
@WebServlet("/AccountDeleteComp")
public class AccountDeleteComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteComp() {
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
		Account account = (Account)session.getAttribute("account");
		String userName = account.getUserName();
		String pass = account.getPass();
		if(userName.equals("admin")&&pass.equals("adminPass")){
			account = (Account)session.getAttribute("targetAccount");
		}

		AccountDAO accountDao = new AccountDAO();

		try {
			accountDao.delete(account.getUserID());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("aaa"+userName);
		if(userName.equals("admin")&&pass.equals("adminPass")){
			getServletContext().getRequestDispatcher("/admin/accountDeleteCompAdmin.jsp").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/accountDeleteComp.jsp").forward(request, response);
		}
	}

}
