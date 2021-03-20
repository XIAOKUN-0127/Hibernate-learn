package com.hibernate02.test;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate02.bean.User;

import junit.framework.TestCase;
/**
 * 
 * @author admin
 *
 */
public class Test01 extends TestCase{
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
	 * hbm2ddl.auto 值为 create  每次都创建新表覆盖旧表
	 */
	public void test01Insert01() {
		Transaction transaction=session.beginTransaction();
		User user=new User("依依别离9", new Date());
		//返回主键值
		Serializable result=session.save(user);
		System.out.println(result);
		transaction.commit();
	}
	
	/**
	 * hbm2ddl.auto 值为 create-drop  sessionFactroy.close();表被删除
	 */
	public void test01Insert02() {
		Transaction transaction=session.beginTransaction();
		User user=new User("依依别离9", new Date());
		//返回主键值
		Serializable result=session.save(user);
		System.out.println(result);
		transaction.commit();
	}
	/**
	 * hbm2ddl.auto 值为 update  不改变原来表结构 有新的结构 添加进去
	 */
	public void test01Insert03() {
		Transaction transaction=session.beginTransaction();
		User user=new User("李金玲", new Date());
		//返回主键值
		Serializable result=session.save(user);
		System.out.println(result);
		transaction.commit();
	}
	
	/**
	 * hbm2ddl.auto 值为 validate  当表不存在或表结构变化 会抛出异常 
	 */
	public void test01Insert04() {
		Transaction transaction=session.beginTransaction();
		User user=new User("李金玲", new Date());
		//返回主键值
		Serializable result=session.save(user);
		System.out.println(result);
		transaction.commit();
	}
	/**
	 * 测试session的一级缓存
	 */
	public void test02SessionCache() {
		Transaction transaction=session.beginTransaction();
		
		User u1=(User) session.get(User.class, 1);
		System.out.println("第一次查询:"+u1);
		System.out.println("============================");
		u1.setUname("木子李");
		User u2=(User) session.get(User.class, 1);
		System.out.println("第二次查询:"+u1);
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
