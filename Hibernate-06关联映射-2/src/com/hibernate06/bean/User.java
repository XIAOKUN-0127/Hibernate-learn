package com.hibernate06.bean;

import java.util.Date;
/**
 * tb_user表对应的实体类
 * @author admin
 *
 */
public class User {

	Integer uid;
	String uname;
	Date birthday;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
	
	public User() {}
	public User(String uname,Date birthday) {
		this.uname=uname;
		this.birthday=birthday;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", birthday=" + birthday + "]";
	}
	
	
}
