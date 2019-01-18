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
import model.Account;
import model.ExhibitInfo;

/**
 * Servlet implementation class Bid
 */
@WebServlet("/Bid")
public class Bid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bid() {
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
		ExhibitInfo exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");
		ExhibitDAO exhibitDao = new ExhibitDAO();
		int price = Integer.parseInt(request.getParameter("price"));
		boolean result = true;

		BidDAO bidDao = new BidDAO();
		AccountDAO accountDao = new AccountDAO();
		Account exhibitUser = new Account();
		Account bidSuccessUser = new Account();
		int bidNum=0;

		try {
			result=exhibitDao.bidPriceCheck(exhibitInfo.getExhibitID(),price);		//入力された価格より高い価格が既に登録されていた場合、falseが返り値として得る
			exhibitUser=accountDao.getAccountOnID(exhibitInfo.getUserID());
			bidSuccessUser = accountDao.getAccountOnID(exhibitInfo.getBidSuccessUserID());
			bidNum=bidDao.getNum(exhibitInfo.getExhibitID());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("bidPrice", price);
		request.setAttribute("result", result);
		if(result==false){
			request.setAttribute("bidSuccessUser", bidSuccessUser);
			request.setAttribute("exhibitUser", exhibitUser);
			request.setAttribute("bidNum", bidNum);
			getServletContext().getRequestDispatcher("/auction.jsp").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/bidConfirm.jsp").forward(request, response);
		}
	}

}
