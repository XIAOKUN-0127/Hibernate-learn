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
 * Oracle数据库 的HQL-1
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
	 * 测试查询
	 */
	@Test
	public void test01() {
		Employee employee=(Employee) session.get(Employee.class, 1);
		System.out.println(employee.getEname());
		System.out.println(employee.getDepartment().getDname());
	}
	/**
	 * hql查询    占位符 ？  绑定时 用索引  从0开始  Query支持方法链的风格  绑定参数后返回 query对象
	 * 1.创建 Query对象
	 * 2.绑定参数
	 * 3.执行查询
	 */
	@Test
	public void test02HQL01() {
		//创建 Query对象
		String queryString="from Employee e where e.age > ? and e.ename like ?";
		Query query=session.createQuery(queryString);
		//绑定参数
		query.setInteger(0, 17)
			 .setString(1, "李%");
		//执行查询
		List<Employee>emps=query.list();
		System.out.println(emps.size());
	}
	/**
	 * 使用命名参数
	 * 占位符处  :+参数名  绑定参数时 用 参数名绑定
	 */
	@Test
	public void test02HQL02() {
		//创建 Query对象
		String queryString="from Employee e where e.age > :age and e.ename like :ename";
		Query query=session.createQuery(queryString);
		//绑定参数
		query.setInteger("age", 17)
			 .setString("ename", "李%");
		//执行查询
		List<Employee>emps=query.list();
		System.out.println(emps.size());
	}
	/**
	 * 参数为 实体类对象
	 */
	@Test
	public void test02HQL03() {
		//创建 Query对象
		String queryString="from Employee e where e.department= ?";
		Query query=session.createQuery(queryString);
		//绑定参数
		Department dept=new Department();
		dept.setDid(2);
		
		query.setEntity(0, dept);
		//执行查询
		List<Employee>emps=query.list();
		System.out.println(emps.size());
	}
	@Test
	public void test03AddData() {
		for(int i=1;i<=100;i++) {
			Department department=new Department();
			department.setDname(i+":dept_name");
			Employee employee=new Employee();
			employee.setAge(i);
			employee.setEname(i+":emp_name");
			employee.setDepartment(department);
			session.save(department);
			session.save(employee);
		}
	}
	/**
	 * hql的分页
	 * setFirstResult()第几页  0对应第一页
	 * setMaxResults()每页显示的条数
	 */
	@Test
	public void test04PageHQL01() {
		String hql="from Employee";
		Query query=session.createQuery(hql);
		int pageNo=17;
		int pageSize=6;//第三页 每页5条
		List<Employee>emps=query.setFirstResult((pageNo-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		System.out.println(emps);
	}
	/**
	 * 将 查询语句定义到 Employee.hbm.xml与class标签同级处
	 */
	@Test
	public void test05NamedHQL() {
		Query query=session.getNamedQuery("enameQuery");
		List<Employee> emps=query.setParameter("ename", "1%").list();
		System.out.println(emps);
	}
	/**
	 * hql查询字段
	 */
	@Test
	public void test06HQLSelect01() {
		String hql="select e.ename,e.age,e.department from Employee e where e.eid > :eid";
		Query query=session.createQuery(hql);
		List<Object[]>objs=query.setInteger("eid", 50).list();
		for(Object[] obj:objs) {
			System.out.println(Arrays.asList(obj));
		}
	}
	/**
	 * hql
	 * 将 字段封装成对象
	 */
	@Test
	public void test06HQLSelect02() {
		String hql="select new Employee(e.eid,e.ename,e.age,e.department) from Employee e where e.eid > :eid";
		Query query=session.createQuery(hql);
		List<Employee>objs=query.setInteger("eid", 300).list();
		for(Employee obj:objs) {
			System.out.println(obj.getEid()+"\t"+obj.getEname());
		}
	}
}
