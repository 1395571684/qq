package qqServer.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	private String id;
	private String sickname;
	private int age;
	private String email;
	private String img;
	private String password;
	private List<User> friend;

	public List<User> getFriend() {
		return friend;
	}

	public void setFriend(List<User> friend) {
		this.friend = friend;
	}

	public User() {
		super();
	}

	public User(String id, String sickname, int age, String email, String password) {
		super();
		this.id = id;
		this.sickname = sickname;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", sickname=" + sickname + ", age=" + age + ", email=" + email + ", img=" + img
				+ ", password=" + password + "]";
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSickname() {
		return sickname;
	}

	public void setSickname(String sickname) {
		this.sickname = sickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
