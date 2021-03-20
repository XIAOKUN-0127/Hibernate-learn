package com.hibernate10.bean;

import java.util.HashSet;
import java.util.Set;

public class Department {

	Integer did;
	String dname;
	Set<Employee> emps=new HashSet<Employee>();
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "Department [did=" + did + ", dname=" + dname + ", emps=" + emps + "]";
	}
	
}
