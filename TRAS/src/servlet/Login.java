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

import dao.AccountDAO;
import dao.ExhibitDAO;
import model.Account;
import model.ExhibitInfo;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns={"/Login" , "/home"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardURL = request.getParameter("action");
		if(forwardURL.equals("createAccount")){
			HttpSession session = request.getSession();
			session.setAttribute("register", true);
			getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
		}else if(request.getParameter("action")!="fogetAccount"){
			response.getWriter().append("Served at: foget").append(request.getContextPath());
		}else{
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Account account = new Account();
			AccountDAO accountDao = new AccountDAO();

			account.setUserName(request.getParameter("userName"));
			account.setPass(request.getParameter("pass"));

			boolean result = false;
			try{
				result = accountDao.loginCheck(account);
			}catch(SQLException e){
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			session.setAttribute("login", result);
			if(result==false){
				// ログインに失敗している場合はlogin.jspへ
				getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
			}else{
				// ログインに成功している場合はhome.jspへ

				try {
					session.setAttribute("account", accountDao.getAccount(account.getUserName()));
				} catch (SQLException e) {
					e.printStackTrace();
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

				String userName = account.getUserName();
				String pass = account.getPass();
				System.out.println(userName);
				System.out.println(pass);
				if(userName.equals("admin")&&pass.equals("adminPass")){
					getServletContext().getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
				}else{
					getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				}
			}
	}
}

