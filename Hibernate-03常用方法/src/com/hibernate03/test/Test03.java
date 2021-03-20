package com.hibernate03.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate03.bean.UserInfo;
import com.hibernate03.util.SessionFactoryUtil;

/**
 * 删除测试
 * @author admin
 *
 */
public class Test03 {

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
	 * 先查询出来持久化对象 然后删除该对象 
	 * 删除不存在对象 抛出非法参数异常
	 */
	@Test
	public void test01Delete() {
		//先加载要删除的对象
		UserInfo delUserInfo=(UserInfo) session.get(UserInfo.class, 112);
		//持久化对象才能删除
		session .delete(delUserInfo);
	}
}
