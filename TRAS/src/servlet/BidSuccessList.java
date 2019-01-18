package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BidDAO;
import dao.ExhibitDAO;
import model.Account;
import model.ExhibitInfo;

/**
 * Servlet implementation class BidSuccessList
 */
@WebServlet("/BidSuccessList")
public class BidSuccessList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidSuccessList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BidDAO bidDao = new BidDAO();
		ExhibitDAO exhibitDao = new ExhibitDAO();
		Account account = (Account)session.getAttribute("account");
		Map<String,Integer> bidNumMap=new HashMap<String,Integer>();
		ArrayList<String> exhibitIdList = null;
		ArrayList<ExhibitInfo> exhibitList = new ArrayList<ExhibitInfo>();
		try {
			exhibitIdList=exhibitDao.getBidSuccessInfo(account.getUserID());
			for(String bei : exhibitIdList){
				exhibitList.add(exhibitDao.getExhibitInfo(bei));
				bidNumMap.put(bei, bidDao.getNum(bei));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("exhibitList", exhibitList);
		request.setAttribute("bidNumMap", bidNumMap);
		getServletContext().getRequestDispatcher("/bidSuccessList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
