package com.hibernate03.bean;

public class UserInfo {

	private Integer id;//主键id
	private String loginName;//登录名称
	private String loginPwd;//登录密码
	private String name;//真实姓名
	private String address;//家庭地址
	private String phone;//手机号
	/**
	 * hibernate的底层使用反射 需要无参构造器
	 */
	public UserInfo() {}
	public UserInfo(String loginName, String loginPwd, String name, String address, String phone) {
		super();
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", name=" + name
				+ ", address=" + address + ", phone=" + phone + "]";
	}
}
