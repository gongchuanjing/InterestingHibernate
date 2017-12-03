package tk.chuanjing.chapter01.domain;

public class Customer {

	private int id;
	private String name;
	private String address;
	private String sex;

	public Customer() {
		super();
	}

	public Customer(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", sex=" + sex + "]";
	}

}
