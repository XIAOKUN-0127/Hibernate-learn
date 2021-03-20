package com.hibernate06.bean;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer cid;
	private String cname;
	/**
	 * 声明集合时需要初始化
	 * 声明集合时必须用接口  底层hibernate使用内置集合
	 */
	private Set<Order> orders=new HashSet<Order>();//多个订单的集合
	public Customer() {}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname +"]";
	}
	
	
}
