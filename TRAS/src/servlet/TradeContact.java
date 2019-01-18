package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.ExhibitDAO;
import dao.TradeContactDAO;
import model.Account;
import model.Chat;
import model.ExhibitInfo;

/**
 * Servlet implementation class TradeContact
 */
@WebServlet("/TradeContact")
public class TradeContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TradeContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Chat> userChatList = null;
		HttpSession session = request.getSession();
		if (request.getParameter("action") != null) {
			System.out.println("b");
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");
			ExhibitDAO exhibitDao = new ExhibitDAO();

			Chat chat = new Chat();

			TradeContactDAO tradeContactDao = new TradeContactDAO();

			Account bidSuccessUser = new Account();
			Account exhibitUser = new Account();

			AccountDAO accountDao = new AccountDAO();

			String serchExhibitID = request.getParameter("action");
			System.out.println(serchExhibitID);

			try {
				Account account = new Account();
				account = (Account)session.getAttribute("account");

				chat = (Chat)session.getAttribute("chat");
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID); // 出品情報をDAOから取得

				exhibitUser = accountDao.getAccountOnID(exhibitInfo.getUserID());
				bidSuccessUser = accountDao.getAccountOnID(exhibitInfo.getBidSuccessUserID());

				userChatList= tradeContactDao.getChatList(exhibitInfo);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			//session.setAttribute("exhibitInfo", exhibitInfo);
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			session.setAttribute("chat", chat);
			session.setAttribute("userChatList", userChatList);
			getServletContext().getRequestDispatcher("/tradeContact.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//List<Chat> userChatList = null;
		ArrayList<Chat> userChatList = new ArrayList<Chat>();

		HttpSession session = request.getSession();

		if (request.getParameter("send") != null) {
			System.out.println("b");
			ExhibitInfo exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");
			//ExhibitInfo exhibitInfo = new ExhibitInfo();
			Account account = (Account) session.getAttribute("account");
			Chat chat = new Chat();

			ExhibitDAO exhibitDao = new ExhibitDAO();
			TradeContactDAO tradeContactDao = new TradeContactDAO();

			String serchExhibitID = request.getParameter("action");
			System.out.println(serchExhibitID);

			chat.setContent(request.getParameter("content"));

			System.out.println(chat.getContent());

			AccountDAO accountDao = new AccountDAO();
			Account exhibitUser = new Account();
			Account bidSuccessUser = new Account();

			try {
				//exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID);// 出品情報をDAOから取得
				tradeContactDao.send(exhibitInfo, account, chat);
				userChatList = tradeContactDao.getChatList(exhibitInfo);
				System.out.println("確認"+userChatList);

				exhibitUser = accountDao.getAccountOnID(exhibitInfo.getUserID());
				bidSuccessUser = accountDao.getAccountOnID(exhibitInfo.getBidSuccessUserID());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("exhibitInfo", exhibitInfo);
			session.setAttribute("userChatList", userChatList);
			session.setAttribute("chat", chat);
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			getServletContext().getRequestDispatcher("/tradeContact.jsp").forward(request, response);
		}
	}
}
