package model;

public class Question_m {
	private String questionID;//管理者側で使う
	private String questionGroup;
	private String textBox;
	private String userID;


	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionGroup() {
		return questionGroup;
	}
	public void setQuestionGroup(String questionGroup) {
		this.questionGroup = questionGroup;
	}
	public String getTextBox() {
		return textBox;
	}
	public void setTextBox(String textBox) {
		this.textBox = textBox;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
