package com.hibernate05.bean;

public class Book {

	Integer bid;
	String bname;
	String auctor;
	Integer publisherId;
	Publisher publisher;
	
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Book() {
		super();
	}
	public Book(String bname, String auctor, Integer publisherId) {
		super();
		this.bname = bname;
		this.auctor = auctor;
		this.publisherId = publisherId;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuctor() {
		return auctor;
	}
	public void setAuctor(String auctor) {
		this.auctor = auctor;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", auctor=" + auctor + ", publisherId=" + publisherId
				+ ", publisher=" + publisher + "]";
	}
	
	
}
