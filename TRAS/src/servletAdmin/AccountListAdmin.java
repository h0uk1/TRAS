package servletAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAOadmin;
import model.Account;

/**
 * Servlet implementation class AccountListAdmin
 */
@WebServlet("/AccountListAdmin")
public class AccountListAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountListAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAOadmin accountDao = new AccountDAOadmin();
		ArrayList<Account> accountList = new ArrayList<Account>();
		if(request.getParameter("toAccountList")!=null){
			try {
				accountList = accountDao.getList("%", "%", "%", "%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("accountList", accountList);
			getServletContext().getRequestDispatcher("/admin/accountListAdmin.jsp").forward(request, response);
		}

		if(request.getParameter("serch")!=null){
			//入力された項目を取得
			String sg = request.getParameter("selectGrade");
			String sd = request.getParameter("selectDepartment");
			String sug = request.getParameter("selectUnderGraduate");
			String iun = request.getParameter("inputUserName");

			//何も入力されていなかった場合の処理
			if(sg.equals(""))sg="%";
			if(sd.equals(""))sd="%";
			if(sug.equals(""))sug="%";
			if(iun.equals(""))iun="%";

			try {
				accountList = accountDao.getList(sg, sd, sug, iun);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("accountList", accountList);
			getServletContext().getRequestDispatcher("/admin/accountListAdmin.jsp").forward(request, response);
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
