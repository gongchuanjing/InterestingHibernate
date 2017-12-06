package tk.chuanjing.chapter02.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter01.domain.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午4:54:29
 * @version 1.0
 */
public class HibernateTest4 {

	// 测试持久化对象三种状态
	@Test
	public void test2() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Customer c = new Customer(); // 瞬时态 (无oid 与session无关联)
		c.setName("张三");
		c.setSex("男");

		session.save(c); // 建立了c与session关联关系，它就是持久态，(有oid)

		// 2.事务提供，关闭
		session.getTransaction().commit();
		session.close();

		System.out.println(c.getId()); // 断开了与session的关联，它是托管态(有oid)
	}
	
	// 测试一级缓存
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 查询id=1的customer对象,如果查询到，会将c存储到一级缓存中。
		Customer c = session.get(Customer.class, 1);
		
		// 会从一级缓存中查询，而不会向数据库在发送sql
		Customer cc = session.get(Customer.class, 1);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 持久化对象具有自动更新数据库能力
	@Test
	public void test4() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Customer c = session.get(Customer.class, 1);
		c.setName("晶晶");
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试一级缓存操作常用API
	@Test
	public void test5() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		List<Customer> list = session.createQuery("from Customer").list(); // 会存储数据到一级缓存
		session.clear();
		Customer c = session.get(Customer.class, 1);// 会先从session一级缓存中获取，如果不存在，才会从数据库获取
		
		session.evict(c);// 从一级缓存 中删除一个指定的对象
		Customer cc = session.get(Customer.class, 1);
		
		cc.setName("mmm");
		session.refresh(cc);// 重新查询数据库，用数据库中信息来更新一级缓存与快照
		
		session.getTransaction().commit();
		session.close();
	}
	
	// session的update操作
	@Test
	public void test6() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
//		Customer cc = session.get(Customer.class, 2);//session一级缓存中存在了一个oid为2的Customer
		
		// 执行update来操作一个脱管对象
		Customer c = new Customer();
		c.setAddress("广州");
		c.setName("赵六");
		c.setId(12);
		
		session.update(c);//当执行update时会将c放入到session一级缓存
		
		session.getTransaction().commit();
		session.close();
	}
}
