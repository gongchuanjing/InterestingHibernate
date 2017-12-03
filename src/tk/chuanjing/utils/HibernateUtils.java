package tk.chuanjing.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ChuanJing
 * @date 2017年7月15日 上午1:28:16
 * @version 1.0
 */
public class HibernateUtils {

	private static Configuration config = null;
	private static SessionFactory sessionFactory = null;
	
	static{
		config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
