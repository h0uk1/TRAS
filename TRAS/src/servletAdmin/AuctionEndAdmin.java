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
import dao.BidDAO;
import dao.ExhibitDAO;
import model.Account;
import model.BidInfo;
import model.ExhibitInfo;

/**
 * Servlet implementation class AuctionEndAdmin
 */
@WebServlet("/AuctionEndAdmin")
public class AuctionEndAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionEndAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action")!=null){
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();

			Account exhibitUser = new Account();
			AccountDAO accountDao = new AccountDAO();

			BidInfo bidInfo = new BidInfo();
			Account bidSuccessUser = new Account();
			int bidNum=0;
			BidDAO bidDao = new BidDAO();

			String serchExhibitID=request.getParameter("action");
			HttpSession session = request.getSession();
			try{
				//出品情報をDAOから取得
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID);

				//出品者情報をDAOから取得
				exhibitUser = accountDao.getAccountOnID(exhibitInfo.getUserID());
				bidInfo = bidDao.getTopBid(exhibitInfo.getExhibitID());
				bidSuccessUser = accountDao.getAccountOnID(exhibitInfo.getBidSuccessUserID());
				bidNum = bidDao.getNum(exhibitInfo.getExhibitID());

			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("result", true);
			session.setAttribute("exhibitInfo", exhibitInfo);
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			request.setAttribute("bidNum", bidNum);
		}
		getServletContext().getRequestDispatcher("/admin/auctionEndAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
