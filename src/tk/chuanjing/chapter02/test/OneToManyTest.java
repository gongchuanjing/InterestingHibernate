package tk.chuanjing.chapter02.test;

import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter02.domain.Customer;
import tk.chuanjing.chapter02.domain.Order;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午10:33:29
 * @version 1.0
 */
public class OneToManyTest {

	// 测试保存
	@Test
	public void test1() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 2.操作
		// 2.1创建一个客户
		Customer c = new Customer();
		c.setName("张三");

		// 2.2创建两个订单
		Order o1 = new Order();
		o1.setMoney(1000d);
		o1.setReceiverInfo("北京");

		Order o2 = new Order();
		o2.setMoney(2000d);
		o2.setReceiverInfo("上海");

		// 2.3建立关系
		// 2.3.1 订单关联客户
		o1.setCustomer(c);
		o2.setCustomer(c);

		// 2.3.2 客户关联订单
		c.getOrders().add(o1);
		c.getOrders().add(o2);

		session.save(o1);
		session.save(o2);
		session.save(c);

		// 3.事务提交，关闭
		session.getTransaction().commit();
		session.close();
	}

	// 测试保存---单向操作(保存订单并自动保存客户)，如果不配置  级联操作  是会失败的
	@Test
	public void test2() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 2.操作
		// 2.1创建一个客户
		Customer c = new Customer();
		c.setName("张三");

		// 2.2创建两个订单
		Order o1 = new Order();
		o1.setMoney(1000d);
		o1.setReceiverInfo("北京");

		Order o2 = new Order();
		o2.setMoney(2000d);
		o2.setReceiverInfo("上海");

		// 2.3建立关系
		// 2.3.1 订单关联客户
		o1.setCustomer(c);
		o2.setCustomer(c);

		session.save(o1);
		session.save(o2);

		// 3.事务提交，关闭
		session.getTransaction().commit();
		session.close();
	}

	// 测试保存---单向操作(保存客户并自动保存订单)，如果不配置  级联操作  是会失败的
	@Test
	public void test3() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 2.操作
		// 2.1创建一个客户
		Customer c = new Customer();
		c.setName("张三");

		// 2.2创建两个订单
		Order o1 = new Order();
		o1.setMoney(1000d);
		o1.setReceiverInfo("北京");

		Order o2 = new Order();
		o2.setMoney(2000d);
		o2.setReceiverInfo("上海");

		// 2.3建立关系
		// 2.3.2 客户关联订单
		c.getOrders().add(o1);
		c.getOrders().add(o2);

		session.save(c);

		// 3.事务提交，关闭
		session.getTransaction().commit();
		session.close();
	}

	// 测试级联删除
	@Test
	public void test5() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 操作----删除订单时，不需要删除客户，当我们删除一个客户时，应该将客户对应订单也删除。
		Customer c = session.get(Customer.class, 2);
		session.delete(c);

		session.getTransaction().commit();
		session.close();
	}

	// 级联 cascade= delete-orphan
	@Test
	public void test6() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 得到客户
		Customer c = session.get(Customer.class, 1);
		// 得到客户的订单
		Order o = session.get(Order.class, 1);
		c.getOrders().remove(o);
		
		session.getTransaction().commit();
		session.close();
	}
}
