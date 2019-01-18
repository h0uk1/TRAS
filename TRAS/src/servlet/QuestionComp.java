package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import model.Question_m;

/**
 * Servlet implementation class QuestionComp
 */
@WebServlet("/QuestionComp")
public class QuestionComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionComp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("register")!=null){
			Question_m question = new Question_m();
			QuestionDAO dao = new QuestionDAO();

			HttpSession session = request.getSession();
			question = (Question_m) session.getAttribute("question");

			try{
				dao.question(question);
			}catch(SQLException e){
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/questionComp.jsp").forward(request, response);
		}
	}

}
