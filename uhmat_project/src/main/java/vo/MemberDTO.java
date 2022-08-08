package vo;

import java.sql.Date;
/*
CREATE TABLE member (
  
    email VARCHAR(50) PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    passwd VARCHAR(16) NOT NULL,
    nick_name VARCHAR(15) unique NOT NULL,
    birth_date VARCHAR(15) NOT NULL,
    hire_date DATE NOT NULL,
    post_code VARCHAR(5),
    address1 varchar(50),
    address2 varchar(50),
    icon VARCHAR(10) df .png,
);
*/
//멤버 관리 하는 클래스
public class MemberDTO {
	private String email;
	private String name;
	private String passwd;
	private String nickName;
	private String birthdate;
	private Date hiredate;
	private String postCode;
	private String address1;
	private String address2;
	private String icon;
	
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	

<<<<<<< HEAD

=======
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", email=" + email + ", name=" + name + ", passwd=" + passwd + ", nick_name="
				+ nick_name + ", birth_date=" + birth_date + ", spicy_degree=" + spicy_degree + ", hire_date="
				+ hire_date + ", post_code=" + post_code + ", address1=" + address1 + ", address2=" + address2 + "]";
	}
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6


	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	


	 
	

}
