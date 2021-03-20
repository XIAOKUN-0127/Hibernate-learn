package com.hibernate01.test;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Test;
import com.hibernate01.bean.User;

/**
 * hibernate入门环境测试
 * @author 肖坤
 *
 */
public class Test01 {

	@Test
	public void test01() {
		//解析配置信息
		Configuration configuration=new Configuration().configure();
		//注册配置信息
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//创建sessionFactory对象
		SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		System.out.println(configuration);
		//创建session会话对象
		Session session=sessionFactory.openSession();
		System.out.println(session);
		//开启事务
		Transaction transaction=session.beginTransaction();
		//保存操作
		User user=new User();
		user.setUname("test03");
		user.setBirthday(new Date());
		session.save(user);
		User u=(User) session.get(User.class, 1);
		System.out.println("查询的User信息:"+u);
		//提交事务
		transaction.commit();
		//关闭资源
		session.close();
		sessionFactory.close();
		
	}
	@After
	public void destroy() {
		
	}
	
}
