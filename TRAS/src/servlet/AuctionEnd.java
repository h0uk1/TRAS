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
import dao.NoticeDAO;
import model.Account;
import model.BidInfo;
import model.ExhibitInfo;

/**
 * Servlet implementation class AuctionEnd
 */
@WebServlet("/AuctionEnd")
public class AuctionEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();

			Account exhibitUser = new Account();
			AccountDAO accountDao = new AccountDAO();

			NoticeDAO noticeDao = new NoticeDAO();

			BidInfo bidInfo = new BidInfo();
			Account bidSuccessUser = new Account();
			int bidNum=0;
			BidDAO bidDao = new BidDAO();

			boolean result = false; //ユーザが出品者or落札者のどちらかであるか判定
			//HistoryInfo historyInfo = new HistoryInfo();
			//HistoryDAO historyDao = new HistoryDAO();



			String serchExhibitID=request.getParameter("action");
			HttpSession session = request.getSession();
			try{
				//出品情報をDAOから取得
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID);

				//閲覧履歴に登録
				//historyInfo.setExhibitID(exhibitInfo.getExhibitID());
				//historyInfo.setUserID(((Account)session.getAttribute("account")).getUserID());
				//historyDao.register(historyInfo);

				//出品者情報をDAOから取得
				exhibitUser = accountDao.getAccountOnID(exhibitInfo.getUserID());
				bidInfo = bidDao.getTopBid(exhibitInfo.getExhibitID());
				bidSuccessUser = accountDao.getAccountOnID(exhibitInfo.getBidSuccessUserID());
				bidNum = bidDao.getNum(exhibitInfo.getExhibitID());

				result=exhibitDao.auctionFinishUserCheck((Account)session.getAttribute("account"));

				noticeDao.createNotice(bidSuccessUser, exhibitInfo, "落札した");
				noticeDao.createNotice(exhibitUser, exhibitInfo, "落札された");
			}catch(SQLException e){
				e.printStackTrace();
			}
			session.setAttribute("exhibitInfo", exhibitInfo);
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			request.setAttribute("bidNum", bidNum);
			request.setAttribute("result", result);
		}
		getServletContext().getRequestDispatcher("/auctionEnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
