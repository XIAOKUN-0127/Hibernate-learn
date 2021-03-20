package com.hibernate10.bean;

public class Employee {

	Integer eid;
	String ename;
	Integer age;
	Department department;
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	@Override
	public String toString() {
		return "Employee [ename=" + ename + ", age=" + age + ", department=" + department + "]";
	}
	public Employee(Integer eid,String ename,Integer age, Department department) {
		super();
		this.eid=eid;
		this.ename = ename;
		this.age=age;
		this.department = department;
	}
	public Employee() {}
	
}
