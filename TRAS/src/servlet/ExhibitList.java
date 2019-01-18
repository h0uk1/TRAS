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
 * Servlet implementation class exhibitList
 */
@WebServlet("/ExhibitList")
public class ExhibitList extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExhibitList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwardURL = request.getParameter("action");
		System.out.println("a");
		HttpSession session = request.getSession();
		ExhibitDAO exhibitDao = new ExhibitDAO();
		List<ExhibitInfo> userExhibitList = null;
		if(forwardURL.equals("ExhibitList")){
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
			getServletContext().getRequestDispatcher("/exhibitList.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] value = request.getParameterValues("exhibitList");
		if(value != null){
			if(value[0].equals("exhibitEndList")){
				HttpSession session = request.getSession();
				ExhibitDAO exhibitDao = new ExhibitDAO();
				List<ExhibitInfo> userExhibitList = null;
					try {
						Account account = new Account();
						account = (Account)session.getAttribute("account");

						userExhibitList = exhibitDao.getUserExhibitList(account, "オークション終了");

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
				getServletContext().getRequestDispatcher("/exhibitEndList.jsp").forward(request, response);
			}


			if(value[0].equals("exhibitList")){
				HttpSession session = request.getSession();
				ExhibitDAO exhibitDao = new ExhibitDAO();
				List<ExhibitInfo> userExhibitList = null;
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
					getServletContext().getRequestDispatcher("/exhibitList.jsp").forward(request, response);
				}
			}


		}
}
