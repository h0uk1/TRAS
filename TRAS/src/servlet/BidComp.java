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
 * Servlet implementation class BidComp
 */
@WebServlet("/BidComp")
public class BidComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidComp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BidInfo bidInfo = new BidInfo();
		BidDAO bidDao = new BidDAO();
		ExhibitInfo exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");
		ExhibitDAO exhibitDao = new ExhibitDAO();
		AccountDAO accountDao = new AccountDAO();
		Account exhibitUser = new Account();
		int bidNum=0;


		//add
		NoticeDAO noticeDao = new NoticeDAO();

		bidInfo.setBidPrice((int)(session.getAttribute("bidPrice")));
		bidInfo.setExhibitID(exhibitInfo.getExhibitID());
		bidInfo.setUserID(((Account)session.getAttribute("account")).getUserID());

		exhibitInfo.setPrice((int)session.getAttribute("bidPrice"));
		exhibitInfo.setBidSuccessUserID(((Account)session.getAttribute("account")).getUserID());

		try {
			bidDao.register(bidInfo);
			exhibitDao.bidUpdate(bidInfo);
			exhibitUser=accountDao.getAccountOnID(exhibitInfo.getUserID());
			bidNum=bidDao.getNum(exhibitInfo.getExhibitID());

			noticeDao.createNotice(exhibitUser, exhibitInfo, "入札");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("exhibitInfo", exhibitInfo);
		request.setAttribute("bidSuccessUser", ((Account)session.getAttribute("account")));
		request.setAttribute("exhibitUser", exhibitUser);
		request.setAttribute("bidNum", bidNum);
		getServletContext().getRequestDispatcher("/auction.jsp").forward(request, response);
	}

}
