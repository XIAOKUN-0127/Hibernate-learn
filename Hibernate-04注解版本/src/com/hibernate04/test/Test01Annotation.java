package com.hibernate04.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate04.bean.Account;
import com.hibernate04.util.SessionFactoryUtil;

public class Test01Annotation {

	Session session;
	Transaction transaction;
	@Before
	public void init() {
		session=SessionFactoryUtil.getsSession();
		transaction=session.beginTransaction();
		
	}
	
	@After
	public void destory() {
		transaction.commit();
		SessionFactoryUtil.closeSession();
	}
	
	@Test
	public void test01Insert() {
		Account account=new Account("account02", "222", 1123.1);
		session.save(account);
	}
	
}
