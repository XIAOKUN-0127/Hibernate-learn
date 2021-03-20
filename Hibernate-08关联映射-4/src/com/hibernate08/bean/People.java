package com.hibernate08.bean;

public class People {

	Integer pid;
	String pname;
	String gender;
	Integer age;
	CardId cardId;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public CardId getCardId() {
		return cardId;
	}
	public void setCardId(CardId cardId) {
		this.cardId = cardId;
	}
	@Override
	public String toString() {
		return "People [pid=" + pid + ", pname=" + pname + ", gender=" + gender + ", age=" + age + ", cardId=" + cardId
				+ "]";
	}
	
	
}
