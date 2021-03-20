package com.hibernate08.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate08.bean.CardId;
import com.hibernate08.bean.People;

/**
 * 按主键 一对一映射
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
	
	/**
	 * 基于主键的一对一 插入数据
	 */
	@Test
	public void test01OneToOnePrimarySave() {
		People people=new People();
		people.setAge(11);
		people.setGender("女");
		people.setPname("李谨容");
		CardId cardId=new CardId();
		cardId.setCardNo("437837637");
		cardId.setPeople(people);
		people.setCardId(cardId);
		//一下两条语句无论顺序如何  都先执行被参照对象的持久化  因为主键不能为空
		session.save(cardId);
		session.save(people);
	}
	/**
	 * 从 CardId到 People的查询
	 * 懒加载  第二次sql是连接查询  因为Carid端有参照 people的外键约束
	 */
	@Test
	public void test01OneToOnePrimaryGet01() {
		CardId cardId=(CardId) session.get(CardId.class, 1);
		System.out.println("carid:--"+cardId.getCardNo());
		System.out.println("people:"+cardId.getPeople().getPname());
	}
	
	/**
	 * 从People 到  CardId的查询
	 * 无懒加载  一次连接查询people和cardId的信息   没有导航到cardid的关联
	 */
	@Test
	public void test01OneToOnePrimaryGet02() {
		People people=(People) session.get(People.class, 1);
		System.out.println("people:--"+people.getPname());
		System.out.println("carid:"+people.getCardId().getCardNo());
	}
}
