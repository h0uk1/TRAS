package model;

import java.util.Date;

public class HistoryInfo {
	private String historyID;
	private String exhibitID;
	private String userID;
	private Date historyTime;


	public String getHistoryID() {
		return historyID;
	}
	public void setHistoryID(String historyID) {
		this.historyID = historyID;
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
	public Date getHistoryTime() {
		return historyTime;
	}
	public void setHistoryTime(Date historyTime) {
		this.historyTime = historyTime;
	}
}
