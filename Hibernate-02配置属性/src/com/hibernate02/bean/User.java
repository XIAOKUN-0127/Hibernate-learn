package com.hibernate02.bean;

import java.io.Serializable;
import java.util.Date;

public class User {

	int uid;
	String uname;
	Date birthday;
	
	public User() {
		
	}
	
	public User(String uname, Date birthday) {
		super();
		this.uname = uname;
		this.birthday = birthday;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", birthday=" + birthday + "]";
	}
	
}
