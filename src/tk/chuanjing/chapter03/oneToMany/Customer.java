package tk.chuanjing.chapter03.oneToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午8:20:49
 * @version 1.0
 */

//客户 ------一的一方

@Entity
@Table(name="t_customer3")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; // 主键
	private String name; // 姓名
	
	// 描述客户可以有多个订单
	/*
	 * targetEntity相当于<one-to-many class="">
	 * mappedBy相当于inverse=true
	 */
	@OneToMany(targetEntity=Order.class, mappedBy="customer", orphanRemoval=true)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<Order> orders = new HashSet<Order>();

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