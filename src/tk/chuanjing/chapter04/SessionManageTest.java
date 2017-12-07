package tk.chuanjing.chapter04;

import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter03.select.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月18日 上午5:55:56
 * @version 1.0
 */
public class SessionManageTest {

	// 测试session绑定到线程中
	@Test
	public void test1() {
		// 这时每一次获取都是一个新的session
		Session s1 = HibernateUtils.openSession();
		Session s2 = HibernateUtils.openSession();
		System.out.println(s1 == s2); // false

		Session s3 = HibernateUtils.getCurrentSession();
		Session s4 = HibernateUtils.getCurrentSession();
		System.out.println(s3 == s4); // true
	}

	@Test
	public void test2() {
		Session session = HibernateUtils.getCurrentSession();
		session.beginTransaction();

		Customer c = session.get(Customer.class, 1);
		System.out.println(c);
		
		session.getTransaction().commit();
//		session.close();
	}
}
