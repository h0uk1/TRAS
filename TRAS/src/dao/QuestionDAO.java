package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import model.Question_m;
public class QuestionDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public void question(Question_m question) throws SQLException{ //Modelの名前Question_mに変えた
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			String sql = "insert into question values(?, ?, ?, ?, current_timestamp(0))";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createQuestionID());
			System.out.println(question.getUserID());
			pstmt.setString(2, question.getUserID());
			pstmt.setString(3, question.getQuestionGroup());
			pstmt.setString(4, question.getTextBox());



			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

	public String createQuestionID() throws SQLException{
		Connection connection;
		String questionID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select questionid from question where questionid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//ユーザIDが重複していないかチェック
			while(check==false){
				questionID="A"+ new Random().nextInt(999);

				pstmt.setString(1, questionID);

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
		return questionID;
	}

}
