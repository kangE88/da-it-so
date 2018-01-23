package dto;

import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable{
	private int seq;
	private String id;
	private char[] pwd;
	private String phone;
	private String nick;
	private String created_at;
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}


	public Person(String id, char[] pwd, String phone, String nick) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.nick = nick;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public char[] getPwd() {
		return pwd;
	}


	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	@Override
	public String toString() {
		return "Person [seq=" + seq + ", id=" + id + ", pwd=" + Arrays.toString(pwd) + ", phone=" + phone + ", nick="
				+ nick + ", created_at=" + created_at + "]";
	}
	
	
	
}
