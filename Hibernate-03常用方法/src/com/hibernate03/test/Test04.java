package com.hibernate03.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate03.bean.UserInfo;
import com.hibernate03.util.SessionFactoryUtil;

/**
 * 更新测试
 * @author admin
 *
 */
public class Test04 {

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
	 * update更新
	 */
	@Test
	public void tes01Update() {
		UserInfo updateUserInfo=(UserInfo) session.get(UserInfo.class, 3);
		
		updateUserInfo.setLoginName("铭铭2");
		session.update(updateUserInfo);
	}
	/**
	 * SaveOrUpdate更新
	 */
	@Test
	public void test02SaveOrUpdate() {
		UserInfo updateUserInfo=(UserInfo) session.get(UserInfo.class, 3);
		updateUserInfo.setLoginName("铭铭");
		session.saveOrUpdate(updateUserInfo);
	}
	
	@Test
	public void testMerge() {
		
	}
}
