package tk.chuanjing.chapter03.oneToMany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ChuanJing
 * @date 2017年7月15日 下午8:50:55
 * @version 1.0
 */

//订单-----多的一方
@Entity
@Table(name="t_order3")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double money;
	private String receiverInfo; // 收货地址
	
	// 订单与客户关联,描述订单属于某一个客户
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="o_customer_id")// 指定外键列
	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverInfo="
				+ receiverInfo + ", customer=" + customer + "]";
	}
}