package com.hibernate06.test;
/**
 * 数据库连接环境测试
 * @author admin
 *
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate06.bean.Customer;
import com.hibernate06.bean.Order;
import com.hibernate06.bean.User;
import com.hibernate06.util.SessionFactoryUtil;
/**
 * 单向多对一  环境测试
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
	 * 单表环境  根据标识属性 uid=1加载user信息
	 */
	@Test
	public void test01Session() {
		System.out.println("连接环境测试:"+session);
		User user=(User) session.get(User.class, 1);
		System.out.println("查询tb_user表的uid=2的记录信息:"+user);
	}
	
	/**
	 * 多对一环境测试  先插入一的一方数据  --发送3条插入sql语句
	 */
	@Test
	public void test02InsertManyToOne01() {
		//Transaction transaction=session.beginTransaction();
		Customer customer=new Customer();
		customer.setCname("customer-01");
		session.save(customer);
		Order order01=new Order();
		order01.setOname("order-01");
		order01.setCustomer(customer);
		session.save(order01);
		Order order02=new Order();
		order02.setOname("order-02");
		order02.setCustomer(customer);
		session.save(order02);
		//transaction.commit();
		//SessionFactoryUtil.closeSession();
		//SessionFactoryUtil.getSessionFactory().close();
		
	}
	
	/**
	 * 多对一环境测试  先插入多的一方数据  --发送3条插入sql语句和两条更新语句
	 * order插入时 customer没有插入 则order里的外键列为空
	 * 当customer插入后 将两个order的外键列值更新
	 */
	@Test
	public void test0Insert2ManyToOne02() {
		Customer customer=new Customer();
		Order order03=new Order();
		order03.setOname("order-03");
		order03.setCustomer(customer);
		//order03插入
		session.save(order03);
		Order order04=new Order();
		order04.setOname("order-04");
		order04.setCustomer(customer);
		////order04插入
		session.save(order04);
		
		customer.setCname("customer-02");
		////customer的插入
		session.save(customer);
		
	}
	
	/**
	 * 测试 多对一的查询   只使用多的一端   、使用一的一端  懒加载效果
	 * 当未使用customer时 不查询该一的一端对象   当使用时才发送sql查询
	 */
	@Test
	public void test03GetManyToOne() {
		System.out.println("=========输出除customer外的order==========");
		Order order=(Order) session.get(Order.class, 2);
		System.out.println("order对象的信息:"+order.getOname());
		System.out.println("=========输出order.customer==========");
		System.out.println("order.customer对象的信息:"+order.getCustomer()+order.getCustomer().getClass().getName());
	}
	/**
	 * 删除一的一端时 如果有多的一端引用了一的一端 则删除时报异常 ConstraintViolationException
	 */
	@Test
	public void test04DeleteManyToOne() {
		Customer customer=(Customer) session.load(Customer.class, 1);
		session.delete(customer);
	}
}
