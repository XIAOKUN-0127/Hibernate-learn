package com.hibernate04.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 标识 实体类 @Entity
 * 标识表 属性name是指定表名 catalog指定数据库名  @Table
 */
@Entity
@Table(name = "tb_account")
public class Account {

	Integer aid;
	String aname;
	String apwd;
	Double balance;
	@Id//标识id主键
	@GeneratedValue//指定主键生成策略
	
	@Column(name = "aid",unique = true,nullable = false)
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	@Column(name = "aname")//数据表的列 name指定列名  类型如何处理？？？
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Column(name = "apwd")
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	@Column(name = "balance")
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Account(String aname, String apwd, Double balance) {
		super();
		this.aname = aname;
		this.apwd = apwd;
		this.balance = balance;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", aname=" + aname + ", apwd=" + apwd + ", balance=" + balance + "]";
	}
	
	
}
