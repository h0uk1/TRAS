package servletAdmin;

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
 * Servlet implementation class HomeAdmin
 */
@WebServlet("/HomeAdmin")
public class HomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serchTextName = request.getParameter("textName");
		String serchPrice = request.getParameter("price");
		String serchTradeState = request.getParameter("tradeState");

		ExhibitDAO exhibitDao = new ExhibitDAO();
		List<ExhibitInfo> exhibitList = null;
		try {
			//出品情報を抽出
			if(serchTextName==""){
				exhibitList = exhibitDao.getExhibitList(serchPrice,serchTradeState);
			}else{
				exhibitList = exhibitDao.getExhibitList(serchTextName,serchPrice,serchTradeState);
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
		request.setAttribute("selectTradeState", serchTradeState);
		getServletContext().getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
	}

}
