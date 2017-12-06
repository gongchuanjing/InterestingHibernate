package tk.chuanjing.chapter03.select;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月17日 下午2:49:04
 * @version 1.0
 */
public class QBCTest {

	// 基本检索
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> list = criteria.list();
		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 排序检索
	@Test
	public void test2() {
		// 查询订单信息 根据订单的价格进行排序
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
		// 指定排序
//		criteria.addOrder(org.hibernate.criterion.Order.desc("money"));
		criteria.addOrder(org.hibernate.criterion.Order.asc("money"));
		List<Order> list = criteria.list();
		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 条件检索
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.查询名称叫张某客户 赵__
		Criteria criteria = session.createCriteria(Customer.class);
		Criterion like = Restrictions.like("name", "赵__");// 其它的条件 lt < gt > le <= ge>= eq==
		criteria.add(like);// 添加条件
		Customer c = (Customer) criteria.uniqueResult();
		System.out.println("结果：" + c);
		
		
		// 2.查询订单价格在1050以上的，并且它的客户是赵某
		Criteria criteria2 = session.createCriteria(Order.class);
		Criterion gt = Restrictions.gt("money", 150d);// >150
		Criterion eq = Restrictions.eq("customer", c);// 与c客户相同
		Criterion and = Restrictions.and(gt, eq);
		criteria2.add(and);
		List<Order> list = criteria2.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 分页检索
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Order.class);
		criteria.setFirstResult((2-1) * 6);
		criteria.setMaxResults(6);
		List<Order> list = criteria.list();
		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 统计检索
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.统计订单总数
//		Criteria criteria = session.createCriteria(Order.class);
//		criteria.setProjection(Projections.rowCount());
//		Object result = criteria.uniqueResult();
//		System.out.println(result);
		
		
		// 2.订单的总价格----分组统计根据客户
		Criteria criteria = session.createCriteria(Order.class);
		criteria.setProjection( Projections.projectionList().add( Projections.sum("money") ).add(Projections.groupProperty("customer") ) );
		List<Object[]> list = criteria.list();// 在这个集合中保存的是Object[money的统计信息,客户信息]
		for (Object[] objs : list) {
			for (Object object : objs) {
				System.out.print(object + "\t");
			}
			System.out.println();
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 离线的检索
	@Test
	public void test6() {
		// 1.得到一个DetachedCriteria
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.like("name", "赵__"));
		
		// 2.生成Criteria执行操作
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Customer> list = criteria.list();

		System.out.println(list);
		session.getTransaction().commit();
		session.close();
	}
}
