package com.hibernate01.test;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate01.bean.User;

import junit.framework.TestCase;
/**
 * Junit的继承方式测试
 * @author admin
 *
 */
public class Test02 extends TestCase{
	SessionFactory sessionFactory=null;
	Session session=null;
	/**
	 * 公共资源的前置处理(测试方法运行前运行)
	 */
	@Override
	protected void setUp() throws Exception {
		System.out.println("setUp...");
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
		session=sessionFactory.openSession();
	}
	/**
	 * 插入测试  以‘test’开头为测试方法
	 */
	public void test01Insert() {
		Transaction transaction=session.beginTransaction();
		User user=new User("依依别离", new Date());
		//返回主键值
		Serializable result=session.save(user);
		System.out.println(result);
		transaction.commit();
	}
	/**
	 * load加载
	 */
	public void test02Load() {
		Transaction transaction=session.beginTransaction();
		User result=(User) session.load(User.class, 5);
		System.out.println(result);
		transaction.commit();
	}
	/**
	 * 关闭资源  后置处理   测试方法执行后执行
	 */
	@Override
	protected void tearDown() throws Exception {
		System.out.println("tearDown...");
		if(null!=session) {
			session.close();
		}
		if(null!=sessionFactory) {
			sessionFactory.close();
		}
	}

	
}
