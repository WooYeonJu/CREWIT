package com.ssafy.ws.model.dto;

public class User {

	private String id; // 아이디
	private String password; // 비밀번호
	private String name; // 이름
	private String email;// 이메일
	private String phone;
	private int age;
	private String img;
	private String accessToken; // JWT 토큰

	// 기본 생성자
	public User() {
	}

	public User(String id, String password, String name, String email, String phone, int age) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.img = img;
	}

	public User(String id, String password, String name, String email, String phone, int age, String img,
			String accessToken) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.img = img;
		this.accessToken = accessToken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", age=" + age
				+ ", img=" + img + ", accessToken=" + accessToken + "]";
	}
}
