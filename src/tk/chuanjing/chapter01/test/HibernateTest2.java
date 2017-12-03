package tk.chuanjing.chapter01.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import tk.chuanjing.chapter01.domain.Customer;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月14日 下午10:06:41
 * @version 1.0
 */
public class HibernateTest2 {

	/**
	 * 测试手动加载映射配置文件
	 */
	@Test
	public void text1(){
		// 1.创建Configuration来加载配置文件
		Configuration config = new Configuration().configure();
		
		// 2.得到SessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//手动加载映射：直接加载映射配置文件
		//config.addResource("com/itheima/hibernateday01/domain/Customer.hbm.xml");
		
		//手动加载映射：这种方式它会直接在实体类所在包下查找规范映射配置文件
		//config.addClass(Customer.class);
		
		// 3.得到session
		Session session = sessionFactory.openSession();
		
		// 开启事务
		Transaction transaction = session.beginTransaction();
		
		Customer customer = session.get(Customer.class, 2);
		System.out.println(customer);
		
		// 事务提交
		transaction.commit();
		
		// 关闭session
		session.close();
		
		// 关闭sessionFactory
		sessionFactory.close();
	}
	
	/**
	 * 测试HibernateUtils工具使用
	 */
	@Test
	public void text2(){
		// 得到session
		Session session = HibernateUtils.openSession();
		
		// 开启事务
		Transaction transaction = session.beginTransaction();
		
		Customer customer = session.get(Customer.class, 2);
		System.out.println(customer);
		
		// 事务提交
		transaction.commit();
		
		// 关闭session
		session.close();
	}
	
	/**
	 * 测试Transaction
	 */
	@Test
	public void text3(){
		// 得到session
		Session session = HibernateUtils.openSession();
		
		// 2.操作
		Customer c1 = new Customer();
		c1.setName("jack");
		session.save(c1);
		
//		int i = 10 / 0; //抛出异常
		
		Customer c2 = new Customer();
		c2.setName("fox");
		session.save(c2);
		
		// 关闭session
		session.close();
	}
}
