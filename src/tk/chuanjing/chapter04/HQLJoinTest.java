package tk.chuanjing.chapter04;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter03.select.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月18日 上午3:46:40
 * @version 1.0
 */

//hql多表查询
public class HQLJoinTest {

	// 测试显示内连接
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// sql连接 select * from t_customer inner join t_order on 条件
		
		String hql = "from Customer c inner join c.orders with c.id = 1";
//		String hql = "from Customer c inner join c.orders where c.id = 1";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();	// 结果是List<Object[]>
		for (Object[] objs : list) {		// 而Object[]中装入的是Customer与Order对象。
			for (Object object : objs) {
				System.out.print(object + "\t");
			}
			System.out.println();
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试隐式内连接
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// sql连接 select * from t_customer,t_order where 条件
		String hql = "from Order o where o.customer.id = 1";
		Query query = session.createQuery(hql);
		List list = query.list();
		System.out.println("测试隐式内连接：" + list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 迫切内连接
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 迫切内连接 inner join fetch 注意：使用迫切连接结果可能出现重复，所以要使用distinct来去重复
		String hql = "select distinct c from Customer c inner join fetch c.orders";
		// 底层也是执行的inner join 只不过结果封装到对象中。
		
		Query query = session.createQuery(hql);
		List<Customer> list = query.list(); // 结果是List<>,集合中装入的From后面的对象。

		for (Customer o : list) {
			System.out.println(o);
		}

		session.getTransaction().commit();
		session.close();
	}
	
	// 演示外连接
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "from Customer c left outer join c.orders";// 左外连接
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj + "\t");
			}
			System.out.println();
		}
		session.getTransaction().commit();
		session.close();
	}
	
	// 演示迫切左外连接
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 注意:fetch不可以与单独条件的with一起使用
		String hql = "select distinct c from Customer c left outer join fetch c.orders where c.id = 1";
		
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();

		for (Customer c : list) {
			System.out.println(c);
		}

		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
//		String sql = "select * from t_customer4 c left outer join t_order4 o on c.id=o.o_customer_id";
//		原因：关联查询的两个表有相同的列名,Query须要使用别名来区分相同名称的列
//		解决方法：两个表中相同的列，只去其中的一个就可以了，或者像下面这样取别名
		
		String sql = "select o.*, c.name, c.id c_id from t_customer4 c left outer join t_order4 o on c.id=o.o_customer_id";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		List list = sqlQuery.list();
		System.out.println("结果：" + list);
		
		session.getTransaction().commit();
		session.close();
	}
}
