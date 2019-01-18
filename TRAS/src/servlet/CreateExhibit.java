package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ExhibitDAO;
import model.Account;
import model.ExhibitInfo;

/**
 * Servlet implementation class CreateExhibit
 */
@WebServlet("/CreateExhibit")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)
public class CreateExhibit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateExhibit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/exhibit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("exhibit") != null) {
			getServletContext().getRequestDispatcher("/exhibit.jsp").forward(request, response);
			return;
		}

		if(request.getParameter("exhibitConfirm")!=null){
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			HttpSession session = request.getSession();

			// イメージをアップロード
			Part part = request.getPart("tradeTextImage");
			String name = this.getFileName(part);
			if (name.matches(".*..*")) {
				int randamNum = new Random().nextInt(999999);
				name = randamNum + name;
				part.write(getServletContext().getRealPath("/exhibitImage") + "/" + name);

				//ファイルパス作成（サーバーで使えなさそうだから取り消し）
				//String imagePass = getServletContext().getRealPath("/exhibitImage") + "/" + name;
				//imagePass = imagePass.replace("\\", "/");
				//imagePass = imagePass.replace("C:/pleiades/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/kenta/","");
				//System.out.println("imagePass:"+imagePass);

				//イメージ画像セット
				exhibitInfo.setImage("exhibitImage/"+name);
			}

			//出品情報セット
			exhibitInfo.setUserID(((Account) session.getAttribute("account")).getUserID());
			exhibitInfo.setName(request.getParameter("tradeTextName"));
			exhibitInfo.setPrice(Integer.parseInt(request.getParameter("tradeTextPrice")));
			exhibitInfo.setTextState(request.getParameter("tradeTextState"));
			exhibitInfo.setTradeTime(request.getParameter("tradeTime"));
			exhibitInfo.setTradePlace(request.getParameter("tradePlace"));
			exhibitInfo.setDescription(request.getParameter("tradeTextExplain"));
			int auctionEndTime = Integer.parseInt(request.getParameter("auctionEndTime"));

			session.setAttribute("exhibitInfo", exhibitInfo);
			session.setAttribute("auctionEndTime", auctionEndTime);

			getServletContext().getRequestDispatcher("/exhibitConfirm.jsp").forward(request, response);
		}


		if(request.getParameter("exhibitEdit")!=null){
			HttpSession session = request.getSession();
			ExhibitInfo exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");
			File image = new File(getServletContext().getRealPath("/") + exhibitInfo.getImage());
			if(image.exists()){
				if(image.delete()){
					System.out.println("画像を削除しました");
				}
			}
			getServletContext().getRequestDispatcher("/exhibit.jsp").forward(request, response);
		}


		if (request.getParameter("exhibitComp") != null) {
			ExhibitInfo exhibitInfo = new ExhibitInfo();
			ExhibitDAO exhibitDao = new ExhibitDAO();
			HttpSession session = request.getSession();

			exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");
			int auctionEndTime = (int) session.getAttribute("auctionEndTime");

			try {
				if(auctionEndTime==15){
					exhibitDao.registerTest(exhibitInfo, auctionEndTime);
				}else{
					exhibitDao.register(exhibitInfo, auctionEndTime);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/exhibitComp.jsp").forward(request, response);
		}

		if(request.getParameter("toHome")!=null){
			HttpSession session = request.getSession();
			session.setAttribute("exhibitInfo", null);
			session.setAttribute("auctionEndTIme", null);

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
	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
