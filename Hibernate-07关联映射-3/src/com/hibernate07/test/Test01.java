package com.hibernate07.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate07.bean.Department;
import com.hibernate07.bean.Manager;
/**
 * 按外键 一对一映射
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
	public void test01OneToOneSave() {
		Department department=new Department();
		department.setDeptName("开发部");
		Manager manager=new Manager();
		manager.setmgrIdName("刘和生");
		department.setManager(manager);
		manager.setDepartment(department);
		session.save(manager);
		session.save(department);
	}
	/**
	 * 查询department  从department到manager
	 */
	@Test
	public void test01OneToOneSelectDtoM() {
		Department department=(Department) session.get(Department.class, 1);
		System.out.println("deptName:---"+department.getDeptName());
		System.out.println("mgrName:---"+department.getManager().getmgrIdName());
	}
	/**
	 * 查询Manager  从Manager到department（one-to-one的一端）
	 * 		使用left join 将department的记录也查出来了 
	 * 			因为无法从manager关联查询department的记录
	 */
	@Test
	public void test01OneToOneSelectMtoD() {
		Manager manager=(Manager) session.get(Manager.class, 1);
		System.out.println("mgrName:---"+ manager.getmgrIdName());
		System.out.println("deptName:---"+manager.getDepartment().getDeptName());
	}
}
