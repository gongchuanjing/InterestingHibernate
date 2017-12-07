package tk.chuanjing.chapter03.select;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Proxy;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午8:20:49
 * @version 1.0
 */

//客户 ------一的一方

@Entity
@Table(name="t_customer4")
@NamedQuery(name="myHql", query="from Customer")

@SqlResultSetMapping(name="customerSetMapping", entities = {
			@EntityResult(entityClass = Customer.class, fields = {
				@FieldResult(name = "id", column = "id"),
				@FieldResult(name = "name", column = "name")
			})
		})
@NamedNativeQuery(name="findCustomer", query="select * from t_customer", resultSetMapping="customerSetMapping")
@Proxy(lazy=true)
@BatchSize(size=10)
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
	@OneToMany(targetEntity=Order.class, mappedBy="customer")
//	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.SELECT)
	@LazyCollection(LazyCollectionOption.TRUE)
	@BatchSize(size=3)
	private Set<Order> orders = new HashSet<Order>();

	public Customer() {
		super();
	}

	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
		return "Customer [id=" + id + ", name=" + name + "]";
	}
}