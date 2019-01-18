package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
//import dao.QuestionDAO;
import model.Question_m;

/**
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardURL = request.getParameter("action");
		if(forwardURL.equals("question")){
			HttpSession session = request.getSession();
			session.setAttribute("register", true);
			getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
		}
		//getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ok");
		Question_m question = new Question_m();
		//QuestionDAO dao = new QuestionDAO();
		HttpSession session = request.getSession();

		Account account = (Account) session.getAttribute("account");
		question.setUserID((String) account.getUserID());
		question.setQuestionGroup(request.getParameter("questionGroup"));
		question.setTextBox(request.getParameter("textBox"));


		if(request.getParameter("confirm")!=null){
			System.out.println("a");

		session.setAttribute("question", question);
		getServletContext().getRequestDispatcher("/questionConfirm.jsp").forward(request, response);
		}
	}
}
