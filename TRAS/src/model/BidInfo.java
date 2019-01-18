package model;

import java.sql.Date;

public class BidInfo {
	private String bidID;
	private String exhibitID;
	private String userID;
	private int bidPrice;
	private Date bidTime;


	public String getBidID() {
		return bidID;
	}
	public void setBidID(String bidID) {
		this.bidID = bidID;
	}
	public String getExhibitID() {
		return exhibitID;
	}
	public void setExhibitID(String exhibitID) {
		this.exhibitID = exhibitID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Date getBidTime() {
		return bidTime;
	}
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
}
