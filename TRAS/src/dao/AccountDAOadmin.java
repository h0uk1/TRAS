package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Account;

public class AccountDAOadmin {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;


	//アカウント一覧を作成
	public ArrayList<Account> getList(String g, String d, String ug, String un) throws SQLException {
		Connection connection;
		String sql = "select * from account where grade like ? and department like ? and undergraduate like ? and username like ?";
		Account account = null;
		ArrayList<Account> accountList = new ArrayList<Account>();

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, g);
			pstmt.setString(2, d);
			pstmt.setString(3, ug);
			pstmt.setString(4, un);

			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				account = new Account();
				account.setUserID(resultSet.getString("userid"));
				account.setName(resultSet.getString("name"));
				account.setUserName(resultSet.getString("username"));
				account.setGrade(resultSet.getString("grade"));
				account.setDepartment(resultSet.getString("department"));
				account.setUnderGraduate(resultSet.getString("undergraduate"));

				accountList.add(account);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountList;
	}
}
