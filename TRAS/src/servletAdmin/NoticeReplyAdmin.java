package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDAO;
import model.Account;
import model.Notice;

/**
 * Servlet implementation class NoticeReplyAdmin
 */
@WebServlet("/NoticeReplyAdmin")
public class NoticeReplyAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeReplyAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/admin/questionCofirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Notice notice = new Notice();

		if(request.getParameter("confirm")!= null){
			System.out.println(request.getParameter("noticeTitle"));
			notice.setNoticeGroup(request.getParameter("noticeTitle"));
			notice.setMessage(request.getParameter("noticeContent"));

			session.setAttribute("notice", notice);
			getServletContext().getRequestDispatcher("/admin/noticeReplyConfirmAdmin.jsp").forward(request, response);
		}


		if(request.getParameter("register")!= null){

			NoticeDAO noticeDao = new NoticeDAO();
			Account account = new Account();

			account = (Account)session.getAttribute("accountInfo");

			notice = (Notice)session.getAttribute("notice");
			String noticegroup = notice.getNoticeGroup();
			String message = notice.getMessage();

			try {
				noticeDao.createUserNotice(account, noticegroup, message);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
		}

		if(request.getParameter("edit")!=null){
			getServletContext().getRequestDispatcher("/admin/noticeReplyAdmin.jsp").forward(request, response);
		}

		if(request.getParameter("returnQuestionList")!=null){
			getServletContext().getRequestDispatcher("/admin/questionListAdmin.jsp").forward(request, response);
		}
	}

}
