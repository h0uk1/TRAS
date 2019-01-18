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
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		AccountDAO dao = new AccountDAO();

		account.setName(request.getParameter("familyName")+request.getParameter("firstName"));
		account.setUserName(request.getParameter("userName"));
		account.setPass(request.getParameter("pass"));
		account.setGrade(request.getParameter("grade"));
		account.setUnderGraduate(request.getParameter("underGraduate"));
		account.setDepartment(request.getParameter("department"));
		account.setSecretQuestion(request.getParameter("secretQuestion"));
		account.setSecretQuestionAnswer(request.getParameter("secretQuestionAnswer"));


		boolean result = true;

		try{
			result = dao.createCheck(account);
		}catch(SQLException e){
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("register", result);
		if(result==false){
			//既に入力されたユーザ名が利用されている場合
			getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
		}else{
			//入力されたユーザ名で登録が可能な場合
			session.setAttribute("account", account);
			getServletContext().getRequestDispatcher("/createAccountConfirm.jsp").forward(request, response);
		}
	}

}
