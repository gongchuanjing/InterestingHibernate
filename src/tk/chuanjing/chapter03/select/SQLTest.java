package tk.chuanjing.chapter03.select;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月17日 下午4:34:59
 * @version 1.0
 */
public class SQLTest {

	// 测试执行本地sql
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 执行本地sql
		SQLQuery sqlQuery = session.createSQLQuery("select * from t_customer4");
//		List<Object[]> list = sqlQuery.list();
		
		sqlQuery.addEntity(Customer.class);// 将查询结果绑定到指定对象
//		sqlQuery.setParameter(position, val)//给参数赋值
		List<Customer> list = sqlQuery.list();
		
		System.out.println(list);
		session.getTransaction().commit();
		session.close();
	}
	
	//测试本地sql命名查询
	@Test
	public void test2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("findCustomer");
		List list = query.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void test3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		String hql = "delete FROM Customer Where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, 4);
		int update = query.executeUpdate();
		System.out.println("结果：" + update);
		
		session.getTransaction().commit();
		session.close();
	}
}
