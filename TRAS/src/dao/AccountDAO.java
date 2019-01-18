package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import model.Account;


public class AccountDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public boolean loginCheck(Account account) throws SQLException {
		// ログインできるアカウントかを調べる
		boolean result = false;
		Connection connection;
		String sql = "select username,pass from account where username=? and pass=?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, account.getUserName());
			pstmt.setString(2, account.getPass());

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) result = true;

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public Account getAccount(String userName) throws SQLException{
		Connection connection;
		String sql = "select * from account where username=?";
		Account account = new Account();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, userName);

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()){
				account.setUserID(resultSet.getString("userid"));
				account.setName(resultSet.getString("name"));
				account.setUserName(resultSet.getString("username"));
				account.setUserID(resultSet.getString("userid"));
				account.setPass(resultSet.getString("pass"));
				account.setGrade(resultSet.getString("grade"));
				account.setUnderGraduate(resultSet.getString("undergraduate"));
				account.setDepartment(resultSet.getString("department"));
				account.setSecretQuestion(resultSet.getString("secretquestion"));
				account.setSecretQuestionAnswer(resultSet.getString("secretanswer"));
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(account.getUserName());
		return account;
	}


	public Account getAccountOnID(String userID) throws SQLException{
		Connection connection;
		String sql = "select * from account where userid=?";
		Account account = new Account();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, userID);

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()){
				account.setUserID(resultSet.getString("userid"));
				account.setName(resultSet.getString("name"));
				account.setUserName(resultSet.getString("username"));
				account.setUserID(resultSet.getString("userid"));
				account.setPass(resultSet.getString("pass"));
				account.setGrade(resultSet.getString("grade"));
				account.setUnderGraduate(resultSet.getString("undergraduate"));
				account.setDepartment(resultSet.getString("department"));
				account.setSecretQuestion(resultSet.getString("secretquestion"));
				account.setSecretQuestionAnswer(resultSet.getString("secretanswer"));
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}


	public boolean createCheck(Account account) throws SQLException{
		//新規作成できるユーザ名かを調べる
		boolean result = false;
		Connection connection;
		String sql = "select username from account where username=? ";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, account.getUserName());

			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				result = false;
			}else{
				result = true;
			}

			resultSet.close();
			connection.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}



	public String createUserID() throws SQLException{
		Connection connection;
		String userID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select userid from account where userid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//ユーザIDが重複していないかチェック
			while(check==false){
				userID="A"+ new Random().nextInt(999);

				pstmt.setString(1, userID);

				ResultSet resultSet = pstmt.executeQuery();
				if(resultSet.next()){
					check=false;
				}else{
					check=true;
				}
				resultSet.close();
			}
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userID;
	}



	public void create(Account account) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into account values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createUserID());
			pstmt.setString(2, account.getName());
			pstmt.setString(3, account.getUserName());
			pstmt.setString(4, account.getPass());

			//任意項目
			if(account.getGrade()==null){
				pstmt.setString(5, "未設定");
			}else{
				pstmt.setString(5, account.getGrade());
			}
			if(account.getUnderGraduate()==null){
				pstmt.setString(6, "未設定");
			}else{
				pstmt.setString(6, account.getUnderGraduate());
			}
			if(account.getDepartment()==null){
				pstmt.setString(7, "未設定");
			}else{
				pstmt.setString(7, account.getDepartment());
			}

			pstmt.setString(8, account.getSecretQuestion());
			pstmt.setString(9, account.getSecretQuestionAnswer());
			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	//MyPageEditでSetした情報をUPDATE
		public void editAccount(Account account) throws SQLException{
			System.out.println("a");
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("b");
				String sql = "update account set pass=?, grade=?, undergraduate=?, department=?, secretquestion=?, secretanswer=? where userid='" + account.getUserID() +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, account.getPass());

				//任意項目
				if(account.getGrade()==null){
					System.out.println("ab");
					pstmt.setString(2, "未設定");
				}else{
					System.out.println("bb");
					pstmt.setString(2, account.getGrade());
				}
				if(account.getUnderGraduate()==null){
					System.out.println("bc");
					pstmt.setString(3, "未設定");
				}else{
					pstmt.setString(3, account.getUnderGraduate());
				}
				if(account.getDepartment()==null){
					System.out.println("bd");
					pstmt.setString(4, "未設定");
				}else{
					pstmt.setString(4, account.getDepartment());
				}

				pstmt.setString(5, account.getSecretQuestion());
				pstmt.setString(6, account.getSecretQuestionAnswer());
				pstmt.executeUpdate();
				System.out.println("c");
				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		//MyPage編集のときにパスワードがあっているかチェック
		public boolean editCheck(Account account) throws SQLException {
			boolean result = false;
			Connection connection;
			String sql = "select username,pass from account where userid=?, pass=?";

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, account.getUserID());
				pstmt.setString(2, account.getPass());

				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()) result = true;

				resultSet.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}


		public void delete(String userID) throws SQLException{
			Connection connection;
			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				String sql = "delete from account where userid ='" + userID +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.executeUpdate();

				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		//パスワード再発行のためのユーザ認証
		public boolean fogotUserCheck(Account account) throws SQLException {
			boolean result = true;
			Connection connection;
			String sql = "select userid from account where username=? and secretquestion=? and secretanswer=?";

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, account.getUserName());
				pstmt.setString(2, account.getSecretQuestion());
				pstmt.setString(3, account.getSecretQuestionAnswer());

				System.out.println(account.getUserName());
				System.out.println(account.getSecretQuestion());
				System.out.println(account.getSecretQuestionAnswer());
				System.out.println("d1");
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()){
					result = false;
					System.out.println("false");
					account.setUserID(resultSet.getString("userid"));
				}

				resultSet.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}


		//MyPageEditでSetした情報をUPDATE
		public void passReset(Account account) throws SQLException{
			System.out.println("a");
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("b");
				String sql = "update account set pass=? where username='" + account.getUserName() +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, account.getPass());

				pstmt.executeUpdate();
				System.out.println("c");
				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
}
