package com.hibernate07.bean;
/**
 * 经理类
 * @author admin
 *
 */
public class Manager {

	private Integer mgrId;
	private String mgrIdName;
	
	private Department department;

	public Integer getmgrId() {
		return mgrId;
	}

	public void setmgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public String getmgrIdName() {
		return mgrIdName;
	}

	public void setmgrIdName(String mgrIdName) {
		this.mgrIdName = mgrIdName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Manager [mgrId=" + mgrId + ", mgrIdName=" + mgrIdName + ", department=" + department + "]";
	}
	
	
}
