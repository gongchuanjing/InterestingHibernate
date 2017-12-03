package tk.chuanjing.chapter01.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import tk.chuanjing.chapter01.domain.Customer;

/**
 * @author ChuanJing
 * @date 2017年7月14日 上午7:30:05
 * @version 1.0
 */
public class HibernateTest1 {

	/**
	 * 保存一个Customer
	 */
	@Test
	public void saveCustomerTest() {
		// 创建一个Customer
		Customer c = new Customer();
		c.setName("李四");
		c.setAddress("上海");
		c.setSex("男");
		
		// 使用hibernate的api来完成将customer信息保存到mysql中操作
		Configuration config = new Configuration().configure();// 加载hibernate.cfg.xml
	
		SessionFactory sessionFactory = config.buildSessionFactory();
	
		Session session = sessionFactory.openSession();// 相当于得到一个Connection。
		
		// 开启事务
		Transaction transaction = session.beginTransaction();
		
		// 操作
		session.save(c);
		
		// 事务提交
		// session.getTransaction().commit();
		transaction.commit();
		
		session.close();
		sessionFactory.close();
	}
	
	// 根据id查询一个Customer对象
	@Test
	public void findCustomerByIdTest() {
		Configuration config = new Configuration().configure();	// 加载hibernate.cfg.xml
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();			// 相当于得到一个Connection。
		Transaction transaction = session.beginTransaction();	// 开启事务
		
		// 根据业务来编写代码
//		Customer customer = session.get(Customer.class, 1);
		Customer customer = session.load(Customer.class, 1);
		System.out.println(customer.toString());
		
		transaction.commit();		// 事务提交
		session.close();
		sessionFactory.close();
	}
	
	// 修改操作
	@Test
	public void updateCustomerTest() {
		Configuration config = new Configuration().configure();	// 加载hibernate.cfg.xml
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();			// 相当于得到一个Connection。
		Transaction transaction = session.beginTransaction();	// 开启事务
		
		// 根据业务来编写代码
		Customer customer = session.get(Customer.class, 1);
		customer.setName("咳咳");
		session.update(customer);// 修改操作
		
		transaction.commit();		// 事务提交
		session.close();
		sessionFactory.close();
	}
	
	// 删除操作-----根据id进行删除
	@Test
	public void deleteCustomerTest() {
		Configuration config = new Configuration().configure();	// 加载hibernate.cfg.xml
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();			// 相当于得到一个Connection。
		Transaction transaction = session.beginTransaction();	// 开启事务
		
		// 根据业务来编写代码
		Customer customer = session.get(Customer.class, 1);
		session.delete(customer);	// 删除操作
		
		transaction.commit();		// 事务提交
		session.close();
		sessionFactory.close();
	}
	
	// 查询所有Customer
	@Test
	public void findAllCustomerTest() {
		Configuration config = new Configuration().configure();	// 加载hibernate.cfg.xml
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();			// 相当于得到一个Connection。
		Transaction transaction = session.beginTransaction();	// 开启事务
		
		// 根据业务来编写代码
		Query query = session.createQuery("from Customer");		// HQL 它是类似于sql
		List<Customer> list = query.list();
		System.out.println(list.toString());
		
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}