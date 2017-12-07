package tk.chuanjing.chapter04;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter03.select.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月18日 下午5:16:53
 * @version 1.0
 */

//演示延迟加载
public class LoadTest {

	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Customer c1 = session.load(Customer.class, 1);
		
		// Customer c1 = session.get(Customer.class, 1);
		//String name = c1.getName();
		//System.out.println(name);

		// 如果对一个延迟代理对象进行初始化?
		Hibernate.initialize(c1);
		
		session.getTransaction().commit();
		session.close();
	}
}
