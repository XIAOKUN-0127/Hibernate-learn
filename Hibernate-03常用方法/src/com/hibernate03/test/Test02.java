package com.hibernate03.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate03.bean.UserInfo;
import com.hibernate03.util.SessionFactoryUtil;
/**
 * 查询测试
 * @author admin
 *
 */
public class Test02 {

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
	 * get(实体类,主键标识) 查询
	 * 当记录不存在 返回 null
	 */
	@Test
	public void test01Get() {
		//加载存在的记录
		UserInfo u1=(UserInfo) session.get(UserInfo.class, 3);
		//加载不存在的记录
		UserInfo u2=(UserInfo) session.get(UserInfo.class, 111);
		System.out.println("get查询的id=3的userInfo信息:"+u1+"\t"+u1.getLoginName());
		System.out.println("get查询的id=111的userInfo信息:"+u2+"\t"+u2.getLoginName());
	}
	/**
	 * load(实体类,主键标识) 查询
	 * 当查询记录不存在时  抛出异常 ObjectNotFoundException
	 */
	@Test
	public void test02Load() {
		//加载存在的记录
		UserInfo u1=(UserInfo) session.load(UserInfo.class, 3);
		//加载不存在的记录
		UserInfo u2=(UserInfo) session.load(UserInfo.class, 111);
		System.out.println("get查询的id=3的userInfo信息:"+u1+"\t"+u1.getLoginName());
		System.out.println("get查询的id=111的userInfo信息:"+u2+"\t"+u2.getLoginName());
	}
	
}
