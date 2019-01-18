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
import dao.BidDAO;
import dao.ExhibitDAO;
import dao.HistoryDAO;
import model.Account;
import model.BidInfo;
import model.ExhibitInfo;
import model.HistoryInfo;

/**
 * Servlet implementation class ExhibitDelete
 */
@WebServlet("/ExhibitDelete")
public class ExhibitDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExhibitDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		if(request.getParameter("action") != null){
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();
			System.out.println("デリート");

			String serchExhibitID=request.getParameter("action");
			System.out.println(serchExhibitID);

			try{
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID);		   //出品情報をDAOから取得

			}catch(SQLException e){
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			session.setAttribute("exhibitInfo",exhibitInfo);
			getServletContext().getRequestDispatcher("/exhibitDelete.jsp").forward(request, response);
		}

		if (request.getParameter("delete") != null) {
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();
			HttpSession session = request.getSession();


			exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");

			try {
				exhibitDao.delete(exhibitInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/exhibitDeleteComp.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String userName = account.getUserName();
		String pass = account.getPass();
		if(userName.equals("admin")&&pass.equals("adminPass")){
			ExhibitInfo exhibitInfo = new ExhibitInfo();

			Account exhibitUser = new Account();
			AccountDAO accountDao = new AccountDAO();

			BidInfo bidInfo = new BidInfo();
			Account bidSuccessUser = new Account();
			int bidNum=0;
			BidDAO bidDao = new BidDAO();

			HistoryInfo historyInfo = new HistoryInfo();
			HistoryDAO historyDao = new HistoryDAO();

			try{
				//出品情報をDAOから取得
				exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");

				//閲覧履歴に登録
				historyInfo.setExhibitID(exhibitInfo.getExhibitID());
				historyInfo.setUserID(((Account)session.getAttribute("account")).getUserID());
				historyDao.register(historyInfo);

				//出品者情報をDAOから取得
				exhibitUser = accountDao.getAccountOnID(exhibitInfo.getUserID());
				bidInfo = bidDao.getTopBid(exhibitInfo.getExhibitID());
				bidSuccessUser = accountDao.getAccountOnID(bidInfo.getUserID());
				bidNum = bidDao.getNum(exhibitInfo.getExhibitID());

			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			request.setAttribute("bidNum", bidNum);

			getServletContext().getRequestDispatcher("/admin/auctionAdmin.jsp").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/exhibitList.jsp").forward(request, response);
		}
	}

}
