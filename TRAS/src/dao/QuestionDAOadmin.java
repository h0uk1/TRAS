package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Question_m;

public class QuestionDAOadmin {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;


	//問い合わせ一覧を条件なしで抽出
	public ArrayList<Question_m> getList() throws SQLException {
		Connection connection;
		String sql = "select * from question";

		Question_m questionInfo;
		ArrayList<Question_m> questionList = new ArrayList<Question_m>();

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				questionInfo = new Question_m();
				questionInfo.setQuestionID(resultSet.getString("questionid"));
				questionInfo.setUserID(resultSet.getString("userid"));
				questionInfo.setQuestionGroup(resultSet.getString("questiongroup"));
				questionInfo.setTextBox(resultSet.getString("content"));


				questionList.add(questionInfo);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}



	public ArrayList<Question_m> getList(String group) throws SQLException {
		Connection connection;
		String sql = "select * from question where questiongroup = ?";

		Question_m questionInfo;
		ArrayList<Question_m> questionList = new ArrayList<Question_m>();

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, group);

			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				questionInfo = new Question_m();
				questionInfo.setQuestionID(resultSet.getString("questionid"));
				questionInfo.setUserID(resultSet.getString("userid"));
				questionInfo.setQuestionGroup(resultSet.getString("questiongroup"));
				questionInfo.setTextBox(resultSet.getString("content"));


				questionList.add(questionInfo);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	public Question_m getElement(String questionID) throws SQLException{
		Question_m questionInfo = new Question_m();
		Connection connection;
		String sql = "select * from question where questionid = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, questionID);

			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				questionInfo.setQuestionID(resultSet.getString("questionid"));
				questionInfo.setUserID(resultSet.getString("userid"));
				questionInfo.setQuestionGroup(resultSet.getString("questiongroup"));
				questionInfo.setTextBox(resultSet.getString("content"));
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return questionInfo;
	}
}
