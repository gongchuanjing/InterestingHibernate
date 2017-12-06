package tk.chuanjing.chapter03.oneToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author ChuanJing
 * @date 2017年7月16日 下午6:53:46
 * @version 1.0
 */

@Entity
@Table(name="t_idcard")
public class IDCard {
	
	@Id
	@GenericGenerator(name="myuuid", strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	
	private String cardNum;

	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="i_user_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}