package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeAllDAOadmin;
import dao.NoticeDAO;
import model.Account;
import model.NoticeAll;

/**
 * Servlet implementation class exhibitList
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwardURL = request.getParameter("action");
		System.out.println("a");
		HttpSession session = request.getSession();
		NoticeDAO noticeDao = new NoticeDAO();
		List<model.Notice> NoticeList = null;
		if(forwardURL.equals("NoticeList")){
			System.out.println("b");
			try {
				Account account = new Account();
				account = (Account)session.getAttribute("account");

				NoticeList = noticeDao.getNoticeList(account);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("NoticeList", NoticeList);
			getServletContext().getRequestDispatcher("/notice.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		String[] value = request.getParameterValues("notice");
		if(value != null){
			if(value[0].equals("notice")){
				HttpSession session = request.getSession();
				NoticeDAO noticeDao = new NoticeDAO();
				List<model.Notice> NoticeList = null;
				System.out.println("b");
				try {
					Account account = new Account();
					account = (Account)session.getAttribute("account");
					NoticeList = noticeDao.getNoticeList(account);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				session.setAttribute("NoticeList", NoticeList);
				getServletContext().getRequestDispatcher("/notice.jsp").forward(request, response);
			}


			if(value[0].equals("noticeAll")){
				HttpSession session = request.getSession();
				NoticeAllDAOadmin noticeAllDao = new NoticeAllDAOadmin();
				List<model.NoticeAll> NoticeAllList = null;
				System.out.println("確認A");
				try {
					Account account = new Account();
					account = (Account)session.getAttribute("account");
					NoticeAllList = noticeAllDao.getNoticeAllList(account);

				} catch (SQLException e) {
					e.printStackTrace();
				}

				for(NoticeAll notice : NoticeAllList){
					System.out.println("確認B"+notice.getMessage());
				}

				session.setAttribute("NoticeAllList", NoticeAllList);
				getServletContext().getRequestDispatcher("/noticeAll.jsp").forward(request, response);
			}
		}
	}
}

