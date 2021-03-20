package com.hibernate09.bean;

import java.util.HashSet;
import java.util.Set;

public class Item {

	Integer id;
	String name;
	Set<Category>categorys=new HashSet<Category>();
	
	public Set<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
	
}
