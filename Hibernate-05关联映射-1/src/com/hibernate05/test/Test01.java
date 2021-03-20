package com.hibernate05.test;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate05.bean.Book;
import com.hibernate05.bean.Publisher;
import com.hibernate05.util.SessionFactoryUtil;

public class Test01 {

	Session session;
	Transaction transaction;
	@Before
	public void init() {
		session=SessionFactoryUtil.getsSession();
		transaction=session.beginTransaction();
		
	}
	
	@After
	public void destory() {
		transaction.commit();
		SessionFactoryUtil.closeSession();
	}
	/**
	 * 多对一插入
	 */
	@Test
	public void testManyToOne() {
		Book book=new Book();
		book.setBname("Java从入门到放弃");
		Publisher publisher=new Publisher();
		publisher.setPname("开发类");
		session.save(publisher);
		book.setPublisher(publisher);
		session.save(book);
	}
	/**
	 * 加载 单向多对一  发了两个查询SQL语句
	 */
	@Test
	public void testloadManyToOne() {
		Book book=(Book) session.load(Book.class, 1);
		System.out.println(book);
	}
	/**
	 * 测试 多对一插入 先插入多的一段
	 * 先插入book时  publisher为空  因此 要有更新语句 将publisherId设置
	 */
	@Test
	public void testManyToOneInsertMany() {
		Book book01=new Book();
		Book book02=new Book();
		Publisher publisher01=new Publisher();
		publisher01.setPname("测试类");
		book01.setBname("软件测试基础教程");
		book02.setBname("软件测试框架");
		book01.setAuctor("李煜");
		book02.setAuctor("张权");
		book01.setPublisher(publisher01);
		book02.setPublisher(publisher01);
		session.save(book01);
		session.save(book02);
		session.save(publisher01);
	}
	/**
	 * 懒加载  hibernate默认开启 当没有使用到关联对象时 不发送sql语句
	 */
	@Test
	public void testLazyGet() {
		Book book=(Book) session.get(Book.class, 2);
		System.out.println(book.getBname());
		System.out.println(book.getPublisher());
	}
	/**
	 * 从一的一端查询多
	 */
	@Test
	public void testLoadPublisher() {
		Publisher publisher=(Publisher) session.get(Publisher.class, 3);
		System.out.println(publisher.getPname());
		Iterator it=publisher.getBooks().iterator();
		while(it.hasNext()) {
			Book book=(Book) it.next();
			System.out.println(book);
		}
	}
}
