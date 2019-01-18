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
 * Servlet implementation class ExhibitEditComp
 */
@WebServlet("/ExhibitEditComp")
public class ExhibitEditComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExhibitEditComp() {
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
		getServletContext().getRequestDispatcher("/exhibitEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("register")!=null){
			HttpSession session = request.getSession();
			ExhibitDAO dao = new ExhibitDAO();

			ExhibitInfo exhibitInfo=(ExhibitInfo)session.getAttribute("exhibitInfo");
			String addTradeTime = (String)session.getAttribute("addTradeTIme");

			exhibitInfo.setTradeTime(exhibitInfo.getTradeTime() + exhibitInfo.getAddTradeTime());
			exhibitInfo.setTradePlace(exhibitInfo.getTradePlace() + exhibitInfo.getAddTradePlace());
			exhibitInfo.setDescription(exhibitInfo.getDescription() + exhibitInfo.getAddDescription());


			exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");

			try{
				dao.editExhibitInfo(exhibitInfo);;
			}catch(SQLException e){
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/exhibitEditComp.jsp").forward(request, response);
		}
	}

}
