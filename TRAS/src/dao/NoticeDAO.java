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
import model.ExhibitInfo;
import model.Notice;

public class NoticeDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;


	//noticeidのチェックとランダム生成
	public String createNoticeID() throws SQLException{
		Connection connection;
		String noticeID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select noticeid from notice where noticeid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//IDが重複していないかチェック
			while(check==false){
				noticeID="N"+ new Random().nextInt(9999);

				pstmt.setString(1, noticeID);

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
		return noticeID;
	}

	//INSERT INTO
	public void createNotice(Account account, ExhibitInfo exhibitinfo, String noticegroup) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into notice values(?, ?, ?, ?, current_timestamp(0), ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createNoticeID());
			pstmt.setString(2, account.getUserID());
			pstmt.setString(3, noticegroup);

			//noticegruopによって分岐
			if(noticegroup != null){
				if(noticegroup =="落札した"){
					pstmt.setString(4, "あなたが商品を落札しました。取引詳細から取引連絡を行ってください。");
				}else if(noticegroup == "落札された"){
					pstmt.setString(4, "あなたの商品が落札されました。取引詳細から取引連絡を行ってください。");
				}else if(noticegroup=="入札"){
					pstmt.setString(4, "あなたの商品が入札されました。");
				}else if(noticegroup=="最高入札額更新"){
					pstmt.setString(4, "あなたが入札した商品の最高入札金額が更新されました。");
				}else if(noticegroup=="期限終了"){
					pstmt.setString(4, "出品の期限が終了しました。");
				}
			}else{
				pstmt.setString(4, "未指定");
			}

			pstmt.setString(5, exhibitinfo.getExhibitID());

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//ユーザごとの出品情報を表示する
	public ArrayList<Notice> getNoticeList(Account account) throws SQLException{
		Connection connection;
		Statement statement;
		String sql = "select * from notice where userid ='"+ account.getUserID()+"';";
		Notice notice = null;
		ArrayList<Notice> NoticeList = new ArrayList<Notice>();

		ExhibitDAO exhibitDao = new ExhibitDAO();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();;
			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
				notice = new Notice();
				notice.setNoticeID(resultSet.getString("noticeid"));
				notice.setUserID(resultSet.getString("userid"));
				notice.setNoticeGroup(resultSet.getString("noticegroup"));
				notice.setMessage(resultSet.getString("message"));
				notice.setNoticeTime(resultSet.getDate("noticetime"));
				if(resultSet.getString("syuppinid")!=null){
					ExhibitInfo exhibitInfo = exhibitDao.getExhibitInfo(resultSet.getString("syuppinid"));

					notice.setExhibitID(resultSet.getString("syuppinid"));
					notice.setName(exhibitInfo.getName());
					notice.setImage(exhibitInfo.getImage());
				}
				NoticeList.add(notice);
			}


			resultSet.close();
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return NoticeList;
	}


	//個人返信
	public void createUserNotice(Account account, String noticegroup, String message) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into notice(noticeid, userid, noticegroup, message, noticetime) values(?, ?, ?, ?, current_timestamp(0))";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createNoticeID());
			pstmt.setString(2, account.getUserID());
			pstmt.setString(3, noticegroup);
			pstmt.setString(4, message);


			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
