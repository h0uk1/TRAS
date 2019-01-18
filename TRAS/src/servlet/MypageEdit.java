package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class MypageEdit
 */
@WebServlet("/MypageEdit")
public class MypageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwardURL = request.getParameter("action");
		if(forwardURL.equals("MypageEdit")){

			getServletContext().getRequestDispatcher("/mypage.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("a");
		if(request.getParameter("edit") != null){
			//System.out.println("b");
			getServletContext().getRequestDispatcher("/mypageEdit.jsp").forward(request, response);
			return;
		}


		if(request.getParameter("editComp") != null){
			HttpSession session = request.getSession();
			//System.out.println("c");

			Account account = new Account();
			AccountDAO accountDao = new AccountDAO();

			account = (Account)session.getAttribute("account");

			account.setPass(request.getParameter("pass"));
			System.out.println("pass:"+account.getPass());
			boolean result = false;
			try{
				result = accountDao.loginCheck(account);
			}catch(SQLException e){
				e.printStackTrace();
			}

			session.setAttribute("result", result);
			if(result==false){
				//System.out.println("d");
				// 失敗している場合
				System.out.println("no");
				getServletContext().getRequestDispatcher("/mypage.jsp").forward(request, response);
			}else{
				//System.out.println("e");
				// 成功している場合
				if(request.getParameter("newPass")==""){
					account.setPass(account.getPass());
				}else{
					account.setPass(request.getParameter("newPass"));
				}
				System.out.println(account.getPass());


				if(request.getParameter("grade")==""){
					account.setGrade(account.getGrade());
				}else{
					account.setGrade(request.getParameter("grade"));
				}
				System.out.println(account.getGrade());


				if(request.getParameter("underGraduate")==""){
					account.setUnderGraduate(account.getUnderGraduate());
				}else{
					account.setUnderGraduate(request.getParameter("underGraduate"));
				}
				System.out.println(account.getUnderGraduate());


				if(request.getParameter("department")==""){
					account.setDepartment(account.getDepartment());
				}else{
					account.setDepartment(request.getParameter("department"));
				}
				System.out.println(account.getDepartment());


				if(request.getParameter("secretQuestion")==""){
					account.setSecretQuestion(account.getSecretQuestion());
				}else{
					account.setSecretQuestion(request.getParameter("secretQuestion"));
				}
				System.out.println(account.getSecretQuestion());


				if(request.getParameter("secretQuestionAnswer")==""){
					account.setSecretQuestionAnswer(account.getSecretQuestionAnswer());
				}else{
					account.setSecretQuestionAnswer(request.getParameter("secretQuestionAnswer"));
				}
				System.out.println(account.getSecretQuestionAnswer());


				try {
					accountDao.editAccount(account);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				getServletContext().getRequestDispatcher("/mypage.jsp").forward(request, response);
			}

		}

		if(request.getParameter("cancel")!=null){
			getServletContext().getRequestDispatcher("/mypage.jsp").forward(request, response);
		}
	}

}
