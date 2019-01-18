package model;

import java.sql.Date;

public class Notice {
	private String noticeID;
	private String noticeGroup;
	private String userID;
	private String message;
	private Date noticeTime;
	private String exhibitID;
	private String name;
	private String image;
	public String getNoticeID() {
		return noticeID;
	}
	public void setNoticeID(String noticeID) {
		this.noticeID = noticeID;
	}
	public String getNoticeGroup() {
		return noticeGroup;
	}
	public void setNoticeGroup(String noticeGroup) {
		this.noticeGroup = noticeGroup;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getExhibitID() {
		return exhibitID;
	}
	public void setExhibitID(String exhibitID) {
		this.exhibitID = exhibitID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


}
