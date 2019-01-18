package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.QuestionDAOadmin;
import model.Account;
import model.Question_m;

/**
 * Servlet implementation class QuestionInfoAdmin
 */
@WebServlet("/QuestionInfoAdmin")
public class QuestionInfoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionInfoAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
			Question_m questionInfo = new Question_m();
			QuestionDAOadmin questionDao = new QuestionDAOadmin();
			Account accountInfo = new Account();
			AccountDAO accountDao = new AccountDAO();

			String questionID = request.getParameter("action");
			try {
				questionInfo = questionDao.getElement(questionID);
				accountInfo = accountDao.getAccountOnID(questionInfo.getUserID());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			session.setAttribute("accountInfo", accountInfo);
			session.setAttribute("questionInfo", questionInfo);

			getServletContext().getRequestDispatcher("/admin/questionComfirmAdmin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/noticeReplyAdmin.jsp").forward(request, response);
	}

}
