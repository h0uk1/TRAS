package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAOadmin;
import model.Question_m;

/**
 * Servlet implementation class QuestionListAdmin
 */
@WebServlet("/QuestionListAdmin")
public class QuestionListAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionListAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("toQuestionList")!=null){
			QuestionDAOadmin questionDao = new QuestionDAOadmin();

			ArrayList<Question_m> questionList = new ArrayList<Question_m>();

			try {
				questionList = questionDao.getList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("questionList",questionList);
		}

		if(request.getParameter("cancel")!=null){
			QuestionDAOadmin questionDao = new QuestionDAOadmin();

			ArrayList<Question_m> questionList = new ArrayList<Question_m>();

			try {
				questionList = questionDao.getList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("questionList",questionList);
		}

		if(request.getParameter("serch")!=null){
			QuestionDAOadmin questionDao = new QuestionDAOadmin();

			ArrayList<Question_m> questionList = new ArrayList<Question_m>();
			try {
				if(request.getParameter("selectQuestion").equals("未設定")){
					questionList = questionDao.getList();
				}else{
					questionList = questionDao.getList(request.getParameter("selectQuestion"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("questionList",questionList);
			request.setAttribute("selectQuestion", request.getParameter("selectQuestion"));
		}
		getServletContext().getRequestDispatcher("/admin/questionListAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/noticeSendAdmin.jsp").forward(request, response);
	}

}
