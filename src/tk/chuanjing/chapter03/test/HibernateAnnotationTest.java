package tk.chuanjing.chapter03.test;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import tk.chuanjing.chapter03.domain.Book;
import tk.chuanjing.chapter03.domain.Person;
import tk.chuanjing.chapter03.manyToMany.Student;
import tk.chuanjing.chapter03.manyToMany.Teacher;
import tk.chuanjing.chapter03.oneToMany.Customer;
import tk.chuanjing.chapter03.oneToMany.Order;
import tk.chuanjing.chapter03.oneToOne.Husband;
import tk.chuanjing.chapter03.oneToOne.IDCard;
import tk.chuanjing.chapter03.oneToOne.User;
import tk.chuanjing.chapter03.oneToOne.Wife;
import tk.chuanjing.utils.HibernateUtils;

/**
 * @author ChuanJing
 * @date 2017年7月16日 上午11:06:50
 * @version 1.0
 */
public class HibernateAnnotationTest {
	
	// 测试的po的注解开发
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 创始一个book对象
		Book b = new Book();
		b.setName("JavaEE从入门到放弃");
		b.setPrice(56d);
		b.setPublicationDate(new Date());
		
		session.save(b);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试uuid主键生成策略及不生成表中字段映射
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Person p = new Person();
		p.setName("李四");
		p.setMsg("好人啊");
		
		session.save(p);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试one-to-many注解操作(保存客户时级联保存订单)
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Customer c = new Customer();
		c.setName("王五");
		
		Order o1 = new Order();
		o1.setMoney(1000d);
		o1.setReceiverInfo("郑州");
		
		Order o2 = new Order();
		o2.setMoney(2000d);
		o2.setReceiverInfo("成都");
		
		// 3.建立关系
		// 原因:是为了维护外键
		o1.setCustomer(c);
		o2.setCustomer(c);
		
		// 原因:是为了进行级联操作
		c.getOrders().add(o1);
		c.getOrders().add(o2);
		
		// 4.保存客户，并级联保存订单
		session.save(c);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试多对多级联保存(保存学生时保存老师)
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.创建两个老师
		Teacher t1 = new Teacher();
		t1.setName("老师1");
		Teacher t2 = new Teacher();
		t2.setName("老师2");
		
		// 2.创建两个学生
		Student s1 = new Student();
		s1.setName("学生1");
		Student s2 = new Student();
		s2.setName("学生2");
		
		// 3.学生关联老师
		s1.getTeachers().add(t1);
		s1.getTeachers().add(t2);
		
		s2.getTeachers().add(t1);
		s2.getTeachers().add(t2);
		
		// 4.保存学生
		session.save(s1);
		session.save(s2);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试多对多级联删除(前提是建立了双向的级联 All)
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Student student = session.get(Student.class, 1);
		session.delete(student);
	
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试一对一操作外键映射方案(由idcard维护外键)
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 1.创建一个人
		User u = new User();
		u.setName("小明");
		
		// 2.创建身份证号
		IDCard i = new IDCard();
		i.setCardNum("12345789");
		
		// 3.身份证号关联人
		i.setUser(u);
		
		// 4.存储身份证号
		session.save(i);
		
		session.getTransaction().commit();
		session.close();
	}
	
	// 测试一对一  主键生成策略
	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		// 创建丈夫
		Husband h = new Husband();
		h.setName("武大郎");
		
		// 创建妻子
		Wife w = new Wife();
		w.setName("潘金莲");
		
		// 要做双向关联
		h.setWife(w);
		w.setHusband(h);
		
		session.save(w);
		
		session.getTransaction().commit();
		session.close();
	}
}
