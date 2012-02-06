package fr.esstin.ru.ws;

public class User {

	private String lastName;
	private String firstName;
	private String nickName;
	private Integer pswHash;
	private Boolean car;
	private Integer capacity;
	private Boolean ru;
	private Boolean takeCar;
	private Integer count;

	public User() {
	}

	public User(String ln, String fn, String nn, Integer ph, Boolean c,
			Integer cap, Integer co) {

		lastName = ln;
		firstName = fn;
		nickName = nn;
		pswHash = ph;
		car = c;
		capacity = cap;
		ru = false;
		takeCar = false;
		setCount(co);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getPswHash() {
		return pswHash;
	}

	public void setPswHash(Integer pswHash) {
		this.pswHash = pswHash;
	}

	public Boolean getCar() {
		return car;
	}

	public void setCar(Boolean car) {
		this.car = car;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Boolean getRu() {
		return ru;
	}

	public void setRu(Boolean ru) {
		this.ru = ru;
	}

	public Boolean getTakeCar() {
		return takeCar;
	}

	public void setTakeCar(Boolean takeCar) {
		this.takeCar = takeCar;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}