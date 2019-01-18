package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
 * Servlet implementation class ExhibitDeleteComp
 */
@WebServlet("/ExhibitDeleteComp")
public class ExhibitDeleteComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExhibitDeleteComp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ExhibitDAO exhibitDao = new ExhibitDAO();
		List<ExhibitInfo> userExhibitList = null;
		if(request.getParameter("fromExhibitList") != null){
			System.out.println("b");
			try {
				Account account = new Account();
				account = (Account)session.getAttribute("account");

				userExhibitList = exhibitDao.getUserExhibitList(account, "オークション中");

				BidDAO bidDao = new BidDAO();
				Map<String, Integer> bidNumMap = new HashMap<String, Integer>();
				for(ExhibitInfo ei:userExhibitList){
					bidNumMap.put(ei.getExhibitID(), bidDao.getNum(ei.getExhibitID()));
				}
				request.setAttribute("bidNumMap", bidNumMap);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("userExhibitList", userExhibitList);

			Account account = (Account)session.getAttribute("account");
			String userName = account.getUserName();
			String pass = account.getPass();
			if(userName.equals("admin")&&pass.equals("adminPass")){
				List<ExhibitInfo> exhibitList = null;
				try {
					exhibitList = exhibitDao.getExhibitList("オークション中");
					if(exhibitList!=null){
						System.out.println("CatchExhibitList : success");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("exhibitList", exhibitList);

				getServletContext().getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/exhibitList.jsp").forward(request, response);
			}
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
