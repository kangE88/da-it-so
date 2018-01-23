package dto;

import java.io.Serializable;

public class Category implements Serializable{
	private int seq;
	private String title;
	private String description;
	private int state;	// 0은 물품 판매 카테고리, 1은 인력 카테고리
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(String title, String description, int state) {
		super();
		this.title = title;
		this.description = description;
		this.state = state;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Category [seq=" + seq + ", title=" + title + ", description=" + description + ", state=" + state + "]";
	}
}
