package com.hibernate05.bean;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
	Integer pid;
	String pname;
	Set<Book> books=new HashSet<Book>();
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Publisher() {
		super();
	}
	public Publisher(String pname) {
		super();
		this.pname = pname;
	}
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
	@Override
	public String toString() {
		return "Publisher [pid=" + pid + ", pname=" + pname + "]";
	}
	
	
	
}
