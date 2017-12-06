package tk.chuanjing.chapter03.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author ChuanJing
 * @date 2017年7月16日 上午11:25:24
 * @version 1.0
 */
@Entity
@Table(name="t_person", catalog="interestingssh")
public class Person {

	@Id
	@GenericGenerator(name="myuuid", strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	
	@Type(type="string")
	private String name;
	
	@Transient
	private String msg;// 现在这个属性不想生成在表中

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", msg=" + msg + "]";
	}
}