package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/ExhibitListAdmin")
public class ExhibitListAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExhibitListAdmin() {
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
		List<ExhibitInfo> userExhibitList = new ArrayList<ExhibitInfo>();
		Account account = new Account();
			System.out.println("b");
			try {
				account = (Account)session.getAttribute("targetAccount");

				System.out.println(account.getUserID());
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
			getServletContext().getRequestDispatcher("/admin/exhibitListAdmin.jsp").forward(request, response);


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
						account = (Account)session.getAttribute("targetAccount");

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
				getServletContext().getRequestDispatcher("/admin/exhibitEndListAdmin.jsp").forward(request, response);
			}


			if(value[0].equals("exhibitList")){
				HttpSession session = request.getSession();
				ExhibitDAO exhibitDao = new ExhibitDAO();
				List<ExhibitInfo> userExhibitList = null;
					try {
						Account account = new Account();
						account = (Account)session.getAttribute("targetAccount");

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
					getServletContext().getRequestDispatcher("/admin/exhibitListAdmin.jsp").forward(request, response);
				}
			}


		}
}
