package tk.chuanjing.chapter04;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter03.select.Customer;
import tk.chuanjing.chapter03.select.Order;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月19日 上午3:26:30
 * @version 1.0
 */
public class FetchLazyTest {
	@Test
	public void setFetchTest1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.得到id=1的Customer
		Customer c = session.get(Customer.class, 1);

		// 2.得到customer关联的order信息
		int size = c.getOrders().size();

		System.out.println(size);

		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void setFetchTest2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.查询出所有的客户信息
		List<Customer> list = session.createQuery("from Customer").list();

		for (Customer c : list) {
			System.out.println(c.getOrders().size());
		}

		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void oneFetchTest1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.得到一个订单
		Order order = session.get(Order.class, 1);
		
		//2.得到订单对应的客户
		Customer c = order.getCustomer();

		System.out.println(c.getName());
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	// ----------------------------演示批量抓取------------
	// 查询出所有用户的订单信息
	@Test
	public void batchFetchTest1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.得到所有客户
		List<Customer> list = session.createQuery("from Customer").list();

		// 2.得到客户的订单信息
		for (Customer c : list) {
			System.out.println(c.getOrders().size());
		}

		session.getTransaction().commit();
		session.close();
	}
	// 上述代码操作，当我们执行时，首先发出一条sql来查询所有客户信息，根据客户的id在查询订单信息，因为有三个客户，所以发送了三条sql完成查询订单信息操作，
	// 以上一共执行了四条sql语句 完成操作。
	// 以上就是一个经典问题 N+1

	// 查询出所有的订单，根据订单在查询客户信息
	@Test
	public void batchFetchTest2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.得到所有订单
		List<Order> list = session.createQuery("from Order").list();

		// 2.得到客户信息
		for (Order order : list) {
			System.out.println(order.getCustomer().getName());
		}

		session.getTransaction().commit();
		session.close();
	}
	//订单一共有两种，在查询时会首先发送一条sql查询出所有订单，在根据订单查询客户 一共3条语句完成。
}
