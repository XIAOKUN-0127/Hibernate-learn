package com.hibernate09.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * 
 * @author admin
 *
 */
public class Test01 {

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
	
	@Test
	public void test() {
		
	}
	
	
	
}
