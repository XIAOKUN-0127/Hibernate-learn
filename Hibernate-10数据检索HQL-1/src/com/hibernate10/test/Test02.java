package com.hibernate10.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate10.bean.Department;
import com.hibernate10.bean.Employee;

/**
 * Oracle数据库 的HQL-2
 * @author admin
 *
 */
public class Test02 {

	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;
	@Before
	public void initHibernate() {
		Configuration configuration=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
	    transaction=session.beginTransaction();
	}
	
	@After
	public void closeAll() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	//HQL查询 聚合函数的使用 count(*)\min(e.age)\max(e.age)
	@Test
	public void test01SQLFunction() {
		//String hql="select count(*) from Employee e where e.age> :age";
		String hql="select min(e.age),max(e.age) from Employee e ";
		Query query=session.createQuery(hql);
		List<Object[]> objs=query.list();
		System.out.println(objs.get(0)[0]+"\t"+objs.get(0)[1]);
	}
	//HQL查询 order by 、group by
	@Test
	public void test02SQLFunction() {
		String hql="";
		Query query=session.createQuery(hql);
		List<Object[]> objs=query.list();
		
	}
	//distint去重 和set集合去重
	@Test
	public void test03SQLFunction() {
		
	}
}
