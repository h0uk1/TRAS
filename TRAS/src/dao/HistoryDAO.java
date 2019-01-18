package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import model.HistoryInfo;

public class HistoryDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public void  register(HistoryInfo historyInfo) throws SQLException{
		Connection connection;
		HistoryDAO historyDao=new HistoryDAO();

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into history values(?, ?, ?, current_timestamp(0))";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, historyDao.createHistoryID());
			pstmt.setString(2, historyInfo.getExhibitID());
			pstmt.setString(3, historyInfo.getUserID());

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public String createHistoryID() throws SQLException{
		Connection connection;
		String historyID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select userid from history where historyid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//出品IDが重複していないかチェック
			while(check==false){
				historyID="H"+ new Random().nextInt(999);

				pstmt.setString(1, historyID);

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
		if(historyID==null){
			System.out.println("createExhibit : " + historyID);
		}
		return historyID;
	}


	//閲覧したの出品ID
	public ArrayList<String> getHistoryExhibitId(String userID) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select * from history where userid = '"+ userID +"' and syuppinid in(select syuppinid from syuppin where tradestate = 'オークション中')";
		ArrayList<String> exhibitIdList = new ArrayList<String>();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				exhibitIdList.add(resultSet.getString("syuppinid"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(exhibitIdList==null){
			System.out.println("GetExhibitList : error");
		}else{
			System.out.println("GetExhibitList : seccess");
		}
		return exhibitIdList;
	}

}
