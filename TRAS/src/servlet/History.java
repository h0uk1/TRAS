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
import dao.HistoryDAO;
import model.Account;
import model.ExhibitInfo;

/**
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HistoryDAO historyDao = new HistoryDAO();
		BidDAO bidDao = new BidDAO();
		ExhibitDAO exhibitDao = new ExhibitDAO();
		Account account = (Account)session.getAttribute("account");
		Map<String,Integer> bidNumMap=new HashMap<String,Integer>();
		ArrayList<String> exhibitIdList = null;
		ArrayList<ExhibitInfo> exhibitList = new ArrayList<ExhibitInfo>();
		try {
			exhibitIdList=historyDao.getHistoryExhibitId(account.getUserID());
			for(String bei : exhibitIdList){
				exhibitList.add(exhibitDao.getExhibitInfo(bei));
				bidNumMap.put(bei, bidDao.getNum(bei));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("exhibitList", exhibitList);
		request.setAttribute("bidNumMap", bidNumMap);

		String userName = account.getUserName();
		String pass = account.getPass();
		if(userName.equals("admin")&&pass.equals("adminPass")){
			getServletContext().getRequestDispatcher("/admin/historyAdmin.jsp").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/history.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
