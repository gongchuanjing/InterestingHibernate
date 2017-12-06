package tk.chuanjing.chapter02.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午8:20:49
 * @version 1.0
 */

//客户 ------一的一方
public class Customer {

	private Integer id; // 主键
	private String name; // 姓名
	
	// 描述客户可以有多个订单
	Set<Order> orders = new HashSet<Order>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", orders=" + orders
				+ "]";
	}
}