package com.hibernate08.bean;

public class CardId {

	Integer cid;
	String cardNo;
	People people;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	@Override
	public String toString() {
		return "CardId [cid=" + cid + ", cardNo=" + cardNo + ", people=" + people + "]";
	}
	
	
}
