package tk.chuanjing.chapter01.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import tk.chuanjing.chapter01.domain.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月15日 上午5:01:20
 * @version 1.0
 */
public class HibernateTest3 {
	// 使用hql完成查询所有操作
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("from Customer");// from后面是类名
		List<Customer> list = query.list();
		System.out.println(list.toString());
		session.close();
	}
	
	// 向表中插入100条记录
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		for (int i = 0; i < 100; i++) {
			
			Customer c = new Customer();
			c.setName("姓名"+i);
			c.setAddress("郑州"+i);
			c.setSex("男");
			
			session.save(c);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	// 分页查询 一页显示10条 要得到第二页数据
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 一页显示10条 要得到第二页数据
		Query query = session.createQuery("from Customer");
		query.setFirstResult(10);// 开始位置
		query.setMaxResults(10);// 本次查询结果回显的条数
		List<Customer> list = query.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 查询指定列信息
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		//这时我们得到的不在是Customer对象，而是Object[]
//		Query query = session.createQuery("select name,address from Customer");
//		List<Object[]> list = query.list();
		
		// 我们可不可以得到List<Customer>,我们要想得到这个结果，可以使用hibernate中投影查询。
		// 我们只需要在Customer类中提供name与address做为参数的构造方法
		Query query = session.createQuery("select new Customer(name,address) from Customer");
		List<Customer> list = query.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 条件查询
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 查询name=姓名0的信息
		
//		Query query = session.createQuery("from Customer where name=?");//无名称参数
//		query.setParameter(0, "姓名1");// 要对参数进行赋值
//		List<Customer> list = query.list();
//		System.out.println(list);
		
//		Query query = session.createQuery("from Customer where name=?");
//		query.setParameter(0, "姓名1");
//		Customer c = (Customer) query.uniqueResult();// 如果能保证结果就是唯一的，那么可以使用
//		System.out.println(c);
		
		Query query = session.createQuery("from Customer where name=:myname");
		query.setParameter("myname", "姓名1");// 对有名称参数进行赋值
		List<Customer> list = query.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 执行本地sql----查询全部
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 执行select * from t_customer;
		SQLQuery sqlQuery = session.createSQLQuery("select * from t_customer");
		// List<Object[]> list = sqlQuery.list();
		
		// 想要将结果封装到Customer对象中
		sqlQuery.addEntity(Customer.class);
		List<Customer> list = sqlQuery.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 执行本地sql----条件查询
	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 查询name=姓名0的信息
		
		// 执行select * from t_customer where name=?;
//		SQLQuery sqlQuery = session.createSQLQuery("select * from t_customer where name=?");
//		sqlQuery.setParameter(0, "姓名1");// 要对参数进行赋值
		
//		// 想要将结果封装到Customer对象中
//		sqlQuery.addEntity(Customer.class);
//		List<Customer> list = sqlQuery.list();
//		System.out.println(list);
		
		SQLQuery sqlQuery = session.createSQLQuery("select * from t_customer where name=?");
		sqlQuery.setParameter(0, "姓名1");
		sqlQuery.addEntity(Customer.class);
		Customer c = (Customer) sqlQuery.uniqueResult();// 如果能保证结果就是唯一的，那么可以使用
		System.out.println(c);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试Criteria
	@Test
	public void test8() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 得到Criteria
		Criteria criteria = session.createCriteria(Customer.class);
		
		// 查询所有
//		List<Customer> list = criteria.list();
//		System.out.println(list);
		
		// 分页查询
//		criteria.setFetchSize(10);
//		criteria.setMaxResults(10);
		
		// 多条件查询
		// 1.查询name='姓名1'
//		criteria.add(Restrictions.eq("name", "姓名1"));// where name='姓名1';
//		// 2.查询address='郑州1'
//		criteria.add(Restrictions.eq("address", "郑州1"));
//		Customer c = (Customer) criteria.uniqueResult();
//		System.out.println(c);
		
		//查询name='姓名1' 或者   address='上海'
		criteria.add(Restrictions.or(Restrictions.eq("name", "姓名1"), Restrictions.eq("address", "上海")));
		List<Customer> list = criteria.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
}
