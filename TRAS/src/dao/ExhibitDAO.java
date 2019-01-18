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
import model.BidInfo;
import model.ExhibitInfo;

public class ExhibitDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	// 無条件で出品情報を抽出する
	public ArrayList<ExhibitInfo> getExhibitList(String tradeState) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select * from syuppin where tradestate= '" + tradeState +"'";
		ExhibitInfo exhibitInfo;
		ArrayList<ExhibitInfo> exhibitList = new ArrayList<ExhibitInfo>();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				exhibitInfo = new ExhibitInfo();
				exhibitInfo.setExhibitID(resultSet.getString("syuppinid"));
				exhibitInfo.setUserID(resultSet.getString("userid"));
				exhibitInfo.setName(resultSet.getString("textname"));
				exhibitInfo.setPrice(resultSet.getInt("price"));
				exhibitInfo.setEndTime(resultSet.getDate("endtime"));
				exhibitInfo.setTradeTime(resultSet.getString("tradetime"));
				exhibitInfo.setTradePlace(resultSet.getString("tradeplace"));
				exhibitInfo.setDescription(resultSet.getString("description"));
				exhibitInfo.setSyuppinTime(resultSet.getDate("syuppintime"));
				exhibitInfo.setImage(resultSet.getString("image"));
				exhibitInfo.setBidSuccessUserID(resultSet.getString("bidsuccessuserid"));
				exhibitInfo.setTextState(resultSet.getString("textstate"));
				exhibitInfo.setTradeState(resultSet.getString("tradestate"));

				exhibitList.add(exhibitInfo);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(exhibitList!=null){
			System.out.println("GetExhibitList : seccess");
		}
		return exhibitList;
	}


	// 教科書名と価格で抽出
	public ArrayList<ExhibitInfo> getExhibitList(String textName,String price,String tradeState) throws SQLException {
		Connection connection;
		String sql = null;
			switch(price){
				case "未設定":
					sql = "select * from syuppin where textname=? and tradestate= '" + tradeState +"'";
					break;
				case "1000未満":
					sql = "select * from syuppin where textname=? and price<1000 and tradestate= '" + tradeState +"'";
					break;
				case "1000以上2000未満":
					sql = "select * from syuppin where textname=? and 1000<=price and price<2000 and tradestate= '" + tradeState +"'";
					break;
				case "2000以上3000未満":
					sql = "select * from syuppin where textname=? and 2000<=price and price<3000 and tradestate= '" + tradeState +"'";
					break;
				case "3000以上4000未満":
					sql = "select * from syuppin where textname=? and 3000<=price and price<4000 and tradestate= '" + tradeState +"'";
					break;
				case "4000以上5000未満":
					sql = "select * from syuppin where textname=? and 4000<=price and price<5000 and tradestate= '" + tradeState +"'";
					break;
				case "5000以上":
					sql = "select * from syuppin where textname=? and price>=5000 and tradestate= '" + tradeState +"'";
			}


		ExhibitInfo exhibitInfo;
		ArrayList<ExhibitInfo> exhibitList = new ArrayList<ExhibitInfo>();

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, textName);

			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				exhibitInfo = new ExhibitInfo();
				exhibitInfo.setExhibitID(resultSet.getString("syuppinid"));
				exhibitInfo.setUserID(resultSet.getString("userid"));
				exhibitInfo.setName(resultSet.getString("textname"));
				exhibitInfo.setPrice(resultSet.getInt("price"));
				exhibitInfo.setEndTime(resultSet.getDate("endtime"));
				exhibitInfo.setTradeTime(resultSet.getString("tradetime"));
				exhibitInfo.setTradePlace(resultSet.getString("tradeplace"));
				exhibitInfo.setDescription(resultSet.getString("description"));
				exhibitInfo.setSyuppinTime(resultSet.getDate("syuppintime"));
				exhibitInfo.setImage(resultSet.getString("image"));
				exhibitInfo.setBidSuccessUserID(resultSet.getString("bidsuccessuserid"));
				exhibitInfo.setTextState(resultSet.getString("textstate"));
				exhibitInfo.setTradeState(resultSet.getString("tradestate"));

				exhibitList.add(exhibitInfo);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(exhibitList==null){
			System.out.println("GetExhibitList : error");
		}else{
			System.out.println("GetExhibitList : success");
		}
		return exhibitList;
	}



	// 価格で出品情報を抽出する
	public ArrayList<ExhibitInfo> getExhibitList(String price,String tradeState) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = null;
		switch(price){
			case "未設定":
				sql = "select * from syuppin where tradestate= '" + tradeState +"'";
				break;
			case "1000未満":
				sql = "select * from syuppin where price<1000 and tradestate='" + tradeState +"'";
				break;
			case "1000以上2000未満":
				sql = "select * from syuppin where 1000<=price and price<2000 and tradestate= '" + tradeState +"'";
				break;
			case "2000以上3000未満":
				sql = "select * from syuppin where 2000<=price and price<3000 and tradestate= '" + tradeState +"'";
				break;
			case "3000以上4000未満":
				sql = "select * from syuppin where 3000<=price and price<4000 and tradestate= '" + tradeState +"'";
				break;
			case "4000以上5000未満":
				sql = "select * from syuppin where 4000<=price and price<5000 and tradestate= '" + tradeState +"'";
				break;
			case "5000以上":
				sql = "select * from syuppin where price>=5000 and tradestate= '" + tradeState +"'";
		}
		ExhibitInfo exhibitInfo;
		ArrayList<ExhibitInfo> exhibitList = new ArrayList<ExhibitInfo>();

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				exhibitInfo = new ExhibitInfo();
				exhibitInfo.setExhibitID(resultSet.getString("syuppinid"));
				exhibitInfo.setUserID(resultSet.getString("userid"));
				exhibitInfo.setName(resultSet.getString("textname"));
				exhibitInfo.setPrice(resultSet.getInt("price"));
				exhibitInfo.setEndTime(resultSet.getDate("endtime"));
				exhibitInfo.setTradeTime(resultSet.getString("tradetime"));
				exhibitInfo.setTradePlace(resultSet.getString("tradeplace"));
				exhibitInfo.setDescription(resultSet.getString("description"));
				exhibitInfo.setSyuppinTime(resultSet.getDate("syuppintime"));
				exhibitInfo.setImage(resultSet.getString("image"));
				exhibitInfo.setBidSuccessUserID(resultSet.getString("bidsuccessuserid"));
				exhibitInfo.setTextState(resultSet.getString("textstate"));
				exhibitInfo.setTradeState(resultSet.getString("tradestate"));

				exhibitList.add(exhibitInfo);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(exhibitList==null){
			System.out.println("GetExhibitList : error");
		}else{
			System.out.println("GetExhibitList : seccess");
		}
		return exhibitList;
	}


	public void  register(ExhibitInfo exhibitInfo, int auctionEndTime) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into syuppin values(?, ?, ?, ?, current_timestamp(0) + interval '"+ auctionEndTime +" DAY', ?, ?, ?, current_timestamp(0), ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			String image;
			if(exhibitInfo.getImage()==null){
				image="no image";
			}else{
				image=exhibitInfo.getImage();
			}

			pstmt.setString(1, createSyuppinID());
			pstmt.setString(2, exhibitInfo.getUserID());
			pstmt.setString(3, exhibitInfo.getName());
			pstmt.setInt(4, exhibitInfo.getPrice());
			pstmt.setString(5, exhibitInfo.getTradeTime());
			pstmt.setString(6, exhibitInfo.getTradePlace());
			pstmt.setString(7, exhibitInfo.getDescription());
			pstmt.setString(8, "未設定");
			pstmt.setString(9, exhibitInfo.getTextState());
			pstmt.setString(10, "オークション中");
			pstmt.setString(11, image);

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//テスト用登録メソッド
	public void  registerTest(ExhibitInfo exhibitInfo, int auctionEndTime) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into syuppin values(?, ?, ?, ?, current_timestamp(0) + interval '5 MINUTE', ?, ?, ?, current_timestamp(0), ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			String image;
			if(exhibitInfo.getImage()==null){
				image="no image";
			}else{
				image=exhibitInfo.getImage();
			}

			pstmt.setString(1, createSyuppinID());
			pstmt.setString(2, exhibitInfo.getUserID());
			pstmt.setString(3, exhibitInfo.getName());
			pstmt.setInt(4, exhibitInfo.getPrice());
			pstmt.setString(5, exhibitInfo.getTradeTime());
			pstmt.setString(6, exhibitInfo.getTradePlace());
			pstmt.setString(7, exhibitInfo.getDescription());
			pstmt.setString(8, "未設定");
			pstmt.setString(9, exhibitInfo.getTextState());
			pstmt.setString(10, "オークション中");
			pstmt.setString(11, image);

			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public String createSyuppinID() throws SQLException{
		Connection connection;
		String syuppinID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select userid from syuppin where syuppinid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//出品IDが重複していないかチェック
			while(check==false){
				syuppinID="E"+ new Random().nextInt(999);

				pstmt.setString(1, syuppinID);

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
		if(syuppinID==null){
			System.out.println("createExhibit : " + syuppinID);
		}
		return syuppinID;
	}


	// ID検索
	public ExhibitInfo getExhibitInfo(String exhibitID) throws SQLException {
		Connection connection;
		Statement statement;
		String sql = "select * from syuppin where syuppinid= '" + exhibitID +"'";
		ExhibitInfo exhibitInfo = new ExhibitInfo();;

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				exhibitInfo.setExhibitID(resultSet.getString("syuppinid"));
				exhibitInfo.setUserID(resultSet.getString("userid"));
				exhibitInfo.setName(resultSet.getString("textname"));
				exhibitInfo.setPrice(resultSet.getInt("price"));
				exhibitInfo.setEndTime(resultSet.getDate("endtime"));
				exhibitInfo.setTradeTime(resultSet.getString("tradetime"));
				exhibitInfo.setTradePlace(resultSet.getString("tradeplace"));
				exhibitInfo.setDescription(resultSet.getString("description"));
				exhibitInfo.setSyuppinTime(resultSet.getDate("syuppintime"));
				exhibitInfo.setImage(resultSet.getString("image"));
				exhibitInfo.setBidSuccessUserID(resultSet.getString("bidsuccessuserid"));
				exhibitInfo.setTextState(resultSet.getString("textstate"));
				exhibitInfo.setTradeState(resultSet.getString("tradestate"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(exhibitInfo!=null){
			System.out.println("GetExhibitList : seccess");
		}
		return exhibitInfo;
	}

	//ユーザごとの出品情報を表示する
		public ArrayList<ExhibitInfo> getUserExhibitList(Account account, String tradestate) throws SQLException{
			Connection connection;
			Statement statement;
			String sql = "select * from syuppin where userid ='"+ account.getUserID()  +"' AND tradestate='" + tradestate +"'";
			ExhibitInfo exhibitInfo;
			ArrayList<ExhibitInfo> userExhibitList = new ArrayList<ExhibitInfo>();
			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();;
				System.out.println(sql);
				ResultSet resultSet = statement.executeQuery(sql);


				while(resultSet.next()){
					System.out.println("yeah");
					exhibitInfo = new ExhibitInfo();
					exhibitInfo.setExhibitID(resultSet.getString("syuppinid"));
					exhibitInfo.setUserID(resultSet.getString("userid"));
					exhibitInfo.setName(resultSet.getString("textname"));
					exhibitInfo.setPrice(resultSet.getInt("price"));
					exhibitInfo.setEndTime(resultSet.getDate("endtime"));
					exhibitInfo.setTradeTime(resultSet.getString("tradetime"));
					exhibitInfo.setTradePlace(resultSet.getString("tradeplace"));
					exhibitInfo.setDescription(resultSet.getString("description"));
					exhibitInfo.setSyuppinTime(resultSet.getDate("syuppintime"));
					exhibitInfo.setImage(resultSet.getString("image"));
					exhibitInfo.setBidSuccessUserID(resultSet.getString("bidsuccessuserid"));

					userExhibitList.add(exhibitInfo);
				}
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return userExhibitList;
		}

		//オークション終了日時を過ぎている出品情報がないか確認
		public void auctionCheck() throws SQLException{
			Connection connection;
			Statement statement;
			String sql = "select syuppinid from syuppin where endtime < current_timestamp(0);";
			ExhibitDAO exhibitDao = new ExhibitDAO();
			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();;
				ResultSet resultSet = statement.executeQuery(sql);

				while(resultSet.next()){
					exhibitDao.chengeTradeState(resultSet.getString("syuppinid"));
				}

				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}



		//出品情報の取引状態を「オークション中」から「オークション終了状態」に更新する
		public void chengeTradeState(String exhibitID) throws SQLException{
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				String sql = "update syuppin set tradestate=? where syuppinid='" + exhibitID +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, "オークション終了");
				pstmt.executeUpdate();

				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		//入札価格を確認
		public boolean bidPriceCheck(String exhibitID,int price) throws SQLException{
			Connection connection;
			Statement statement;
			String sql = "select price from syuppin where syuppinid='"+ exhibitID +"' and price >="+ price +";";
			boolean result = true;
			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				if(resultSet.next()){
					result=false;
				}
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}


		//出品情報の取引状態を「オークション中」から「オークション終了状態」に更新する
		public void bidUpdate(BidInfo bidInfo) throws SQLException{
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				String sql = "update syuppin set price=?, bidsuccessuserid=? where syuppinid='" + bidInfo.getExhibitID() +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setInt(1, bidInfo.getBidPrice());
				pstmt.setString(2, bidInfo.getUserID());
				pstmt.executeUpdate();

				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public ArrayList<String> getBidSuccessInfo(String userID) throws SQLException {
			Connection connection;
			Statement statement;
			String sql = "select * from syuppin where bidsuccessuserid = '"+ userID +"' and tradestate = 'オークション終了';";
			ArrayList<String> bidSuccessIdList = new ArrayList<String>();

			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
					bidSuccessIdList.add(resultSet.getString("syuppinid"));
				}
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return bidSuccessIdList;
		}

		public ArrayList<String> getBidSuccessUserID() throws SQLException {
			Connection connection;
			Statement statement;
			String sql = "select * from syuppin where bidsuccessuserid ;";
			ArrayList<String> bidSuccessIdList = new ArrayList<String>();

			try{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
					bidSuccessIdList.add(resultSet.getString("bidsuccessuserid"));
				}
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return bidSuccessIdList;
		}


		public boolean auctionFinishUserCheck(Account account) throws SQLException {
			// ログインできるアカウントかを調べる
			boolean result = false;
			Connection connection;
			String sql = "select userid, bidsuccessuserid from syuppin where userid=? or bidsuccessuserid=?";

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, account.getUserID());
				pstmt.setString(2, account.getUserID());
				System.out.println(account.getUserID());

				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()) result = true;

				resultSet.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}


		public void delete (ExhibitInfo exhibitInfo) throws SQLException{
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);


				String sql = "delete from syuppin where syuppinid ='" + exhibitInfo.getExhibitID()+"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);


				pstmt.executeUpdate();

				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		public void editExhibitInfo(ExhibitInfo exhibitInfo) throws SQLException{
			System.out.println("a");
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("b");
				String sql = "update syuppin set tradeTime=?, tradePlace=?, description=? where syuppinid='" + exhibitInfo.getExhibitID() +"'";
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, exhibitInfo.getTradeTime());
				pstmt.setString(2, exhibitInfo.getTradePlace());
				pstmt.setString(3, exhibitInfo.getDescription());
				System.out.println(exhibitInfo.getAddDescription());
				System.out.println(exhibitInfo.getDescription());

				pstmt.executeUpdate();
				System.out.println("c");
				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		//delete from syuppin where endtime < current_timestamp(0) - interval '20 DAY';
		//自動取引終了
		public void tradeCheck() throws SQLException{
			Connection connection;

			try {
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user, password);


				String sql = "delete from syuppin where endtime < current_timestamp(0) - interval '20 DAY'";
				PreparedStatement pstmt = connection.prepareStatement(sql);


				pstmt.executeUpdate();

				connection.close();
				pstmt.close();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
}


