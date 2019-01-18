package model;

import java.sql.Date;

public class NoticeAll {
	private String noticeAllID;
	private String userID;
	private String noticeTitle;
	private String message;
	private Date noticeTime;

	public String getNoticeAllID() {
		return noticeAllID;
	}
	public void setNoticeAllID(String noticeAllID) {
		this.noticeAllID = noticeAllID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
}
