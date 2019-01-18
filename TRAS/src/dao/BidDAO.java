package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import model.BidInfo;

public class BidDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;



	// 出品ID検索
	public BidInfo getTopBid(String exhibitID) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select * from nyusatu where price in(select max(price) from nyusatu) and syuppinid='"+ exhibitID +"'";
		System.out.println(sql);
		BidInfo bidInfo = new BidInfo();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				bidInfo.setBidID(resultSet.getString("nyusatuid"));
				bidInfo.setUserID(resultSet.getString("userid"));
				bidInfo.setBidPrice(resultSet.getInt("price"));
				bidInfo.setExhibitID(resultSet.getString("syuppinid"));
				bidInfo.setBidTime(resultSet.getDate("nyusatutime"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(bidInfo!=null){
			System.out.println("GetBidInfo : seccess");
		}
		return bidInfo;
	}


	// 入札数
	public int getNum(String exhibitID) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select count(*) from nyusatu where syuppinid= '" + exhibitID +"';";
		System.out.println(sql);
		BidInfo bidInfo = new BidInfo();
		int	num = 0;
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				num=resultSet.getInt("count");
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(bidInfo!=null){
			System.out.println("GetBidInfo : seccess");
		}
		return num;
	}


	//入札中の出品ID
	public ArrayList<String> getBidExhibitId(String userID) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select * from nyusatu where userid = '"+ userID +"' and syuppinid in(select syuppinid from syuppin where tradestate = 'オークション中')";
		ArrayList<String> exhibitIdList = new ArrayList<String>();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				boolean hasID=false;
				String getId=resultSet.getString("syuppinid");
				for(String id:exhibitIdList){
					if(id.equals(getId))hasID=true;
				}
				if(hasID==false){
					exhibitIdList.add(resultSet.getString("syuppinid"));
				}
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


	//入札
	public void  register(BidInfo bidInfo) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into nyusatu values(?, ?, ?, ?, current_timestamp(0))";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createNyusatuID());
			pstmt.setString(2, bidInfo.getUserID());
			pstmt.setInt(3, bidInfo.getBidPrice());
			pstmt.setString(4, bidInfo.getExhibitID());

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public String createNyusatuID() throws SQLException{
		Connection connection;
		String nyusatuID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select userid from nyusatu where nyusatuid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//出品IDが重複していないかチェック
			while(check==false){
				nyusatuID="B"+ new Random().nextInt(999);

				pstmt.setString(1, nyusatuID);

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
		return nyusatuID;
	}
}
