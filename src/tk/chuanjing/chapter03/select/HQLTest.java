package tk.chuanjing.chapter03.select;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月17日 上午2:13:28
 * @version 1.0
 */
public class HQLTest {

	// 准备数据(2个Customer，每一个Customer有10个Order)
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Customer c = new Customer();
		c.setName("叶良辰");
		for (int i = 0; i < 10; i++) {
			Order o = new Order();
			o.setMoney(555d + i * 10);
			o.setReceiverInfo("石家庄");
			o.setCustomer(c);
			session.save(o);
		}

		session.getTransaction().commit();
		session.close();
	}

	// 基本检索
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.编写HQL,from是关键字，后面是类名,关键字是不区分大小写，但是类名是区分
		String hql = "from Customer";
		
		// 2.通过session.createQuery(hql)
		Query query = session.createQuery(hql);
		
		// 3.通过list方法得到数据
		List<Customer> list = query.list();
		
		System.out.println(list.toString());

		session.getTransaction().commit();
		session.close();
	}
	
	// 排序检索--//查询订单，根据订单的价格进行排序
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "from Order order by money";// desc 降序 默认是asc 升序
		List<Order> list = session.createQuery(hql).list();

		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 条件查询
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.2.执行hql
//		String hql = "from Order where money>?";
//		List<Order> list = session.createQuery(hql).setParameter(0, 600d).list();
		// 可以使用例如setString() setDouble这样的方法去添加参数,参数的序号是从0开始.
		
		// 2.根据名称来绑定
		String hql = "from Order where money>:mymoney";
		List<Order> list = session.createQuery(hql).setParameter("mymoney", 600d).list();
		
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 分页检索
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		String hql = "from Order";
		Query query = session.createQuery(hql);
		
		// 每页显示6条件 ，我们要得到第二页数据
		query.setFirstResult((2-1)*6);// 设定开始位置
		query.setMaxResults(6); // 设置条数
		List<Order> list = query.list();
		
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 分组统计操作
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 统计操作----统计一共有多少订单 count
//		String hql = "select count(*) from Order";
//		Query query = session.createQuery(hql);
//		Object count = query.uniqueResult();
//		System.out.println(count);
		
		// 分组统计----每一个人的订单总价
		String hql = "select sum(money) from Order group by customer";
		Query query = session.createQuery(hql);
		List list = query.list();
		System.out.println("查询结果：" + list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 投影查询
	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 1.查询出所有的Customer的name
		// String hql = "select name from Customer";
		// List list = session.createQuery(hql).list();
		// System.out.println(list); // [张龙, 张三丰]
		// 如果只查询一个列,得到的结果List<Object>

		// 2.查询所有的Customer的id,name
		// String hql = "select id,name from Customer";
		// List<Object[]> list = session.createQuery(hql).list();
		// for(Object[] objs:list){
		// for(Object obj:objs){
		// System.out.print(obj+" ");
		// }
		// System.out.println();
		// }
		// 如果是查询多列,得到的结果是List<Object[]>

		// 3.使用投影将查询的结果封装到Customer对象
		String hql = "select new Customer(id,name) from Customer";
		List<Customer> list = session.createQuery(hql).list();
		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
	
	// 命名查询
	@Test
	public void test8() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		List<Customer> list = session.getNamedQuery("myHql").list();
		
		System.out.println(list);
		session.getTransaction().commit();
		session.close();
	}
	
	// 命名查询
	@Test
	public void test9() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.我要查询张龙这个客户的订单
		Customer c = session.get(Customer.class, 1);

		Query query = session.getNamedQuery("findOrderByCustomer");// from Order  where c=:c
		
		// 2.现在hql它的参数是一个实体
		List<Order> list = query.setEntity("c", c).list();

		System.out.println(list);

		session.getTransaction().commit();
		session.close();
	}
}
