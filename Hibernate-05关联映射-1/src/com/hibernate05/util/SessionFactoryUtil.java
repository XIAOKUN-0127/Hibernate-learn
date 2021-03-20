package com.hibernate05.util;
/**
 * 会话工厂类   处理多线程并发问题 SessionFactory Session
 * @author admin
 *
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryUtil {
	//配置文件路径
	private static String CONFIG_FILE="/hibernate-cfg.xml";
	//线程本地变量  存储当前线程的Session 键为线程ID  值为 session对象
	private static final ThreadLocal<Session> threadLocalSession=new ThreadLocal<Session>();
	//创建 Configuration对象 
	private static Configuration configuration=new Configuration();
	//sessionFactroy
	private static SessionFactory sessionFactory;
	private static String configFile=CONFIG_FILE;
	//静态加载 创建一个SessionFactroy实例
	static {
		//读取配置文件
		configuration.configure();
		//生成注册机对像
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
				      .applySettings(configuration.getProperties())
				      .buildServiceRegistry();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
				 
	}
	
	public SessionFactoryUtil() {};
	//重新构建 SessionFactory对像
	public static void rebuildSessionFactory() {
		//读取配置文件
				configuration.configure(configFile);
				//生成注册机对像
				ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
						      .applySettings(configuration.getProperties())
						      .buildServiceRegistry();
				sessionFactory=configuration.buildSessionFactory(serviceRegistry);
	}
	//sessionFactory的提供方法
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	//获得Session对象  保证多线程情况下 没有异常
	public static Session getsSession() {
		//以当前线程为键 获得session
		Session session=threadLocalSession.get();
		try {
			if(null==session || !session.isOpen()) {//session为空或没有打开
				if(null==sessionFactory) {//sessionFactory工厂为空
					//重新构建sessionFactory对象
					rebuildSessionFactory();
				}
				//如果工厂不为空 就创建一个session返回
				session=(sessionFactory!=null)? sessionFactory.openSession() : null;
				//将当前线程的session存入线程本地对象
				threadLocalSession.set(session);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}
	/**
	 * 关闭Session
	 */
	public static void closeSession() {
		Session session=threadLocalSession.get();
		threadLocalSession.set(null);
		
		try {
			if(null!=session && session.isOpen()) {
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	//configFile属性的set方法
	public static void setConfigFile(String configFile) {
		SessionFactoryUtil.configFile=configFile;
		sessionFactory=null;
	}
	
	//configuration的get方法
	public static Configuration getcConfiguration() {
		return configuration;
	}
}
