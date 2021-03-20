package com.hibernate03.test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate03.bean.UserInfo;
import com.hibernate03.util.SessionFactoryUtil;
/**
 * 插入测试
 * @author admin
 *
 */
public class Test01 {
	Session session;
	Transaction transaction;
	UserInfo users;
	@Before
	public void init() {
		users=new UserInfo();
		users.setLoginName("lisi");
		users.setLoginPwd("654321");
		users.setName("李四");
		users.setAddress("浙江温州");
		users.setPhone("09876543");
		session=SessionFactoryUtil.getsSession();
		transaction=session.beginTransaction();
		
	}
	
	@After
	public void destory() {
		transaction.commit();
		SessionFactoryUtil.closeSession();
	}
	
	/**
	 * saveOrUpdate方法做插入
	 */
	@Test
	public void addUsersToSaveOrUpdate() {
		users=new UserInfo("testSaveOrUpdate02", "pwd", "刘基", "浙江青田", "19283746");
		session.saveOrUpdate(users);
	}
	
	/**
	 * save方法做插入
	 */
	@Test
	public void addUsersToSave() {
		users=new UserInfo();
		users.setLoginName("lisi");
		users.setLoginPwd("654321");
		users.setName("李四");
		users.setAddress("浙江温州");
		users.setPhone("09876543");
		Session session=SessionFactoryUtil.getsSession();
		
		try {
			session.save(users);
		} catch (Exception e) {
			if(null!=transaction) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	/**
	 * persist(obj);持久化方法   保存
	 * persistence  持久化/持久性
	 */
	@Test
	public void test() {
		//瞬时态
		users=new UserInfo("咪咪", "111", "小米", "北京朝阳区", "11002233");
		//瞬时态  到  持久态
		session.persist(users);
		//托管态  到 持久态
		System.out.println(users);
		
	}
}
