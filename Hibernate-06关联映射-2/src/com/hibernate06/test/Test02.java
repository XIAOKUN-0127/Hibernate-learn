package com.hibernate06.test;

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
	/**
	 * 双向多对一(一对多)关联   先插入一的一端  在插入多的一端
	 * two-way：双向
	 * 发送3条sql 
	 */
	@Test
	public void test01TwoWayManyToOne01() {
		Customer customer03=new Customer();
		Order order05=new Order();
		Order order06=new Order();
		
		customer03.setCname("customer-04");
		order05.setOname("order-07");
		order06.setOname("order-08");
		order05.setCustomer(customer03);
		order06.setCustomer(customer03);
		
		session.save(customer03);
		session.save(order05);
		session.save(order06);
		
	}
	/**
	 * 双向多对一(一对多)关联   先插入多的一端  再插入一的一端
	 * two-way：双向
	 * 3条插入 两条更新语句
	 */
	@Test
	public void test01TwoWayManyToOne02() {
		Customer customer03=new Customer();
		Order order05=new Order();
		Order order06=new Order();
		
		customer03.setCname("customer-05");
		order05.setOname("order-09");
		order06.setOname("order-10");
		order05.setCustomer(customer03);
		order06.setCustomer(customer03);
		
		session.save(order05);
		session.save(order06);
		session.save(customer03);
		
		
	}
	/**
	 * 双向多对一  从一的一端查询 也是懒加载 即用到orders才发查询sql
	 * 异常:栈溢出  当set集合属性在toString方法里时
	 */
	@Test
	public void test02GetTwoWayManyToOne01() {
		Customer customer=(Customer) session.get(Customer.class, 3);
		System.out.println(customer.getCname());
		System.out.println(customer.getOrders()+"\n"+customer.getOrders().getClass().getName());
	}
	
	/**
	 * 双向多对一  从一的一端查询 也是懒加载 即用到customer才发查询sql
	 * 
	 */
	@Test
	public void test02GetTwoWayManyToOne02() {
		Order order=(Order) session.get(Order.class, 3);
		System.out.println(order.getOname());
		System.out.println(order.getCustomer());
	}
	/**
	 * 双向多对一 删除一的一方
	 * customer下有orders因此删除不成功
	 * cascade：delete 指定删除操作级联
	 */
	@Test
	public void test03DeleteTwoWayManyToOne01() {
		Customer customer=(Customer) session.get(Customer.class, 3);
		session.delete(customer);
	}
	/**
	 * 将与customer关联的orders清空
	 * cascade="delete-orphan"  此效果是 删除customer集合对应的记录
	 */
	@Test
	public void test03DeleteOrPhanTwoWayManyToOne02() {
		Customer customer=(Customer) session.get(Customer.class, 4);
		customer.getOrders().clear();
	}
	/**
	 * 查询cid=2的顾客订单  order by oid 降序 
	 * 测试失败 ，浏览
	 *
	 */
	@Test
	public void test04SelectOrderBy() {
		Customer customer=(Customer) session.get(Customer.class, 2);
		System.out.println(customer.getOrders());
	}
}
