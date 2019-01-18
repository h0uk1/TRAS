package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeAllDAOadmin;
import model.NoticeAll;

/**
 * Servlet implementation class NoticeSendAdmin
 */
@WebServlet("/NoticeSendAdmin")
public class NoticeSendAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSendAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/noticeSendAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		NoticeAll noticeAll =new NoticeAll();

		//メニューから遷移
		if(request.getParameter("toNoticeSendAdmin")!=null){
			getServletContext().getRequestDispatcher("/admin/noticeSendAdmin.jsp").forward(request, response);
		}


		//確認ボタンから確認画面に遷移
		if(request.getParameter("confirm") != null){

			noticeAll.setNoticeTitle(request.getParameter("noticeTitle"));
			noticeAll.setMessage(request.getParameter("noticeContent"));

			session.setAttribute("noticeAll", noticeAll);
			getServletContext().getRequestDispatcher("/admin/noticeConfirmAdmin.jsp").forward(request, response);
		}


		//修正する
		if(request.getParameter("edit")!=null){
			getServletContext().getRequestDispatcher("/admin/noticeSendAdmin.jsp").forward(request, response);
		}


		//送信する
		if(request.getParameter("register") != null){
			NoticeAllDAOadmin noticeAllDao = new NoticeAllDAOadmin();

			noticeAll = (NoticeAll)session.getAttribute("noticeAll");

			try {
				noticeAllDao.createNoticeAll(noticeAll);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/admin/noticeSendComp.jsp").forward(request, response);
		}


		//もう一度送信
		if(request.getParameter("ReSend")!=null){
			getServletContext().getRequestDispatcher("/admin/noticeSendAdmin.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
