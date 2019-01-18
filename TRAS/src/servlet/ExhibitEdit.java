package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExhibitDAO;
import model.ExhibitInfo;

/**
 * Servlet implementation class ExhibitEdit
 */
@WebServlet("/ExhibitEdit")
public class ExhibitEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExhibitEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null) {
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();

			String serchExhibitID = request.getParameter("action");
			System.out.println(serchExhibitID);

			try {
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID); // 出品情報をDAOから取得

			} catch (SQLException e) {
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			session.setAttribute("exhibitInfo", exhibitInfo);
			getServletContext().getRequestDispatcher("/exhibitEdit.jsp").forward(request, response);
		}

		//if (request.getParameter("edit") != null) {
			/*
			ExhibitInfo exhibitInfo = (ExhibitInfo) request.getAttribute("exhibitInfo");
			ExhibitDAO exhibitDao = new ExhibitDAO();
			String serchExhibitID = (String) request.getAttribute("edit");
			System.out.println(serchExhibitID);
			try {
				exhibitInfo = exhibitDao.getExhibitInfo(serchExhibitID); // 出品情報をDAOから取得

			} catch (SQLException e) {
				e.printStackTrace();
			}
			*/
			HttpSession session = request.getSession();
			ExhibitInfo exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");

			getServletContext().getRequestDispatcher("/exhibitEdit.jsp").forward(request, response);
		}
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ExhibitInfo exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");
		ExhibitDAO exhibitDao = new ExhibitDAO();

      if(request.getParameter("confirm")!=null){
		//String str1 = exhibitInfo.getTradeTime() + request.getParameter("tradeTime");
		//String str2 = exhibitInfo.getTradePlace() + request.getParameter("tradePlace");
		//String str3 = exhibitInfo.getDescription() + request.getParameter("description");

		//request.getParameter("tradeTime");
		//request.getParameter("tradePlace");
		//request.getParameter("description");

		//session.setAttribute("addTradeTime", request.getParameter("tradeTime"));
		//session.setAttribute("addTradePlace", request.getParameter("tradePlace"));
		//session.setAttribute("addDescription", request.getParameter("description"));
  		exhibitInfo.setAddTradeTime(request.getParameter("tradeTime"));
  		exhibitInfo.setAddTradePlace(request.getParameter("tradePlace"));
  		exhibitInfo.setAddDescription(request.getParameter("description"));
		System.out.println(request.getParameter("tradeTime"));
		session.setAttribute("exhibitInfo", exhibitInfo);


      }else if(request.getParameter("edit")!=null){
    	  getServletContext().getRequestDispatcher("/exhibitEdit.jsp").forward(request, response);
      }


			session.setAttribute("exhibitInfo", exhibitInfo);
			getServletContext().getRequestDispatcher("/exhibitEditConfirm.jsp").forward(request, response);
		}
	}
