package tk.chuanjing.chapter03.oneToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ChuanJing
 * @date 2017年7月16日 下午6:53:27
 * @version 1.0
 */

@Entity
@Table(name="t_user")
public class User {
	
	@Id
	@GenericGenerator(name="myuuid", strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	
	private String name;
	
	@OneToOne(targetEntity=IDCard.class, mappedBy="user")
	private IDCard idCard;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}
}