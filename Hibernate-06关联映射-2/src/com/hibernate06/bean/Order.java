package com.hibernate06.bean;

public class Order {

	private Integer oid;
	private String oname;
	private Customer customer;
	public Order() {}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", oname=" + oname + ", customer=" + customer + "]";
	}
	
}
