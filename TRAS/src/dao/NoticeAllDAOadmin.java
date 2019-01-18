package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import model.Account;
import model.NoticeAll;

public class NoticeAllDAOadmin {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;


	//noticeidのチェックとランダム生成
	public String createNoticeAllID() throws SQLException{
		Connection connection;
		String noticeAllID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select noticeallid from noticeall where noticeallid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//IDが重複していないかチェック
			while(check==false){
				noticeAllID="N"+ new Random().nextInt(9999);

				pstmt.setString(1, noticeAllID);

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
		return noticeAllID;
	}

	//INSERT INTO
	public void createNoticeAll(NoticeAll noticeall) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into noticeall values(?, ?, current_timestamp(0), ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createNoticeAllID());
			pstmt.setString(2, noticeall.getNoticeTitle());
			pstmt.setString(3, noticeall.getMessage());

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//ユーザごとの出品情報を表示する
		public ArrayList<NoticeAll> getNoticeAllList(Account account) throws SQLException{
			Connection connection;
			Statement statement;
			String sql = "select * from noticeall";
			NoticeAll noticeAll;
			ArrayList<NoticeAll> NoticeAllList = new ArrayList<NoticeAll>();
			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();;
				System.out.println(sql);
				ResultSet resultSet = statement.executeQuery(sql);

				while(resultSet.next()){
					System.out.println("yeah");
					noticeAll = new NoticeAll();
					noticeAll.setNoticeAllID(resultSet.getString("noticeallid"));
					noticeAll.setNoticeTitle(resultSet.getString("noticetitle"));
					noticeAll.setUserID(resultSet.getString("userid"));
					noticeAll.setMessage(resultSet.getString("message"));
					noticeAll.setNoticeTime(resultSet.getDate("noticetime"));
					NoticeAllList.add(noticeAll);
				}
					resultSet.close();
					statement.close();
					connection.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				return NoticeAllList;
		}
}
