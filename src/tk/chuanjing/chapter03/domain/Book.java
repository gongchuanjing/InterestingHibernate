package tk.chuanjing.chapter03.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * @author ChuanJing
 * @date 2017年7月16日 上午10:50:42
 * @version 1.0
 */

@Entity		// 定义了一个实体
@Table(name="t_book", catalog="interestingssh")
public class Book {

	@Id		// 主键
	// @GeneratedValue //默认native
	@GeneratedValue(strategy=GenerationType.IDENTITY)// identity
	private Integer id;
	
	@Column(name="b_name", length=30, nullable=true)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)// 是用来定义日期类型
	private Date publicationDate;// 出版日期
	
	@Type(type="double")
	private Double price;// 价格 如果没有添加注解，也会自动的生成在表中

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

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", publicationDate="
				+ publicationDate + ", price=" + price + "]";
	}
}