package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExhibitDAO;
import model.ExhibitInfo;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(request.getParameter("fromQuestionComp")!=null){
			session.setAttribute("question",null);
		}

		ExhibitDAO exhibitDao = new ExhibitDAO();
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
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String serchTextName = request.getParameter("textName");
		String serchPrice = request.getParameter("price");

		ExhibitDAO exhibitDao = new ExhibitDAO();
		List<ExhibitInfo> exhibitList = null;
		try {
			//出品情報を抽出
			if(serchTextName==""){
				exhibitList = exhibitDao.getExhibitList(serchPrice,"オークション中");
			}else{
				exhibitList = exhibitDao.getExhibitList(serchTextName,serchPrice,"オークション中");
			}

			if(exhibitList!=null){
				System.out.println("CatchExhibitList : success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("exhibitList", exhibitList);
		request.setAttribute("selectPrice", serchPrice);
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
