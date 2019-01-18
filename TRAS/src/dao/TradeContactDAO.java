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
import model.Chat;
import model.ExhibitInfo;

public class TradeContactDAO {
	final private static String dbname = "testsaba";   // データベース名
	final private static String user = "wspuser";      // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public void  send(ExhibitInfo exhibitInfo, Account account, Chat chat) throws SQLException{
		Connection connection;

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into chat values(?, ?, ?, ?, current_timestamp(0))";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, createChatID());
			pstmt.setString(2, exhibitInfo.getExhibitID());
			pstmt.setString(3, account.getUserID());
			pstmt.setString(4, chat.getContent());


			pstmt.executeUpdate();

			connection.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public String createChatID() throws SQLException{
		Connection connection;
		String chatID="未設定";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select chatid from chat where chatid=?";
			Class.forName(driverClassName);
			PreparedStatement pstmt = connection.prepareStatement(sql);


			boolean check = false;		//ユーザIDが重複していないかチェック
			while(check==false){
				chatID="C"+ new Random().nextInt(999);

				pstmt.setString(1, chatID);

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
		return chatID;
	}

	/*public ArrayList<Chat> getExhibitUserChatList(Account account) throws SQLException{
		Connection connection;
		Statement statement;
		String sql = "select * from chat where syuppinid ='"+ account.getUserID()+"'";
		Chat chat;
		ArrayList<Chat> userChatList = new ArrayList<Chat>();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();;
			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);


			while(resultSet.next()){
				System.out.println("yeah");
				chat = new Chat();
				chat.setChatID(resultSet.getString("chatid"));
				chat.setUserID(resultSet.getString("userid"));
				chat.setContent(resultSet.getString("content"));
				chat.setDateTime(resultSet.getString("chattime"));


				userChatList.add(chat);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(Chat ct: userChatList){
			System.out.println("sql"+ct.getContent());
		}
		return userChatList;
	}*/

	public ArrayList<Chat> getChatList(ExhibitInfo exhibitInfo) throws SQLException{
		Connection connection;
		Statement statement;
		String sql = "select * from chat where syuppinid ='"+ exhibitInfo.getExhibitID() +"'";
		Chat chat;
		ArrayList<Chat> userChatList = new ArrayList<Chat>();
		System.out.println("確認"+exhibitInfo.getUserID());
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();;
			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);


			while(resultSet.next()){
				System.out.println("yeah");
				chat = new Chat();
				chat.setChatID(resultSet.getString("chatid"));
				chat.setUserID(resultSet.getString("userid"));
				chat.setContent(resultSet.getString("content"));
				chat.setDateTime(resultSet.getString("chattime"));
				System.out.println(resultSet.getString("content"));


				userChatList.add(chat);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(Chat ct: userChatList){
			System.out.println("sql"+ct.getContent());
		}
		return userChatList;
}
}
