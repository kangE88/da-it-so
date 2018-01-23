package dto;

import java.io.Serializable;

public class ItemBbs implements Serializable{
	private int seq;
	private int category_id;
	private String user_id;
	private String title;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	private String imgurl4;
	private int price;
	private String keyword;	// 검색 키워드 ( 해쉬 태크 )
	private String content;
	private String created_at;
	private int state;	//0이면 구해요(구매), 1이면 드려(판매)
	
	
	public ItemBbs() {
		// TODO Auto-generated constructor stub
	}


	public ItemBbs(int seq, int category_id, String user_id, String title, String imgurl1, String imgurl2,
			String imgurl3, String imgurl4, int price, String keyword, String content, String created_at, int state) {
		super();
		this.seq = seq;
		this.category_id = category_id;
		this.user_id = user_id;
		this.title = title;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.imgurl3 = imgurl3;
		this.imgurl4 = imgurl4;
		this.price = price;
		this.keyword = keyword;
		this.content = content;
		this.created_at = created_at;
		this.state = state;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getImgurl1() {
		return imgurl1;
	}


	public void setImgurl1(String imgurl1) {
		this.imgurl1 = imgurl1;
	}


	public String getImgurl2() {
		return imgurl2;
	}


	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}


	public String getImgurl3() {
		return imgurl3;
	}


	public void setImgurl3(String imgurl3) {
		this.imgurl3 = imgurl3;
	}


	public String getImgurl4() {
		return imgurl4;
	}


	public void setImgurl4(String imgurl4) {
		this.imgurl4 = imgurl4;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "ItemBbs [seq=" + seq + ", category_id=" + category_id + ", user_id=" + user_id + ", title=" + title
				+ ", imgurl1=" + imgurl1 + ", imgurl2=" + imgurl2 + ", imgurl3=" + imgurl3 + ", imgurl4=" + imgurl4
				+ ", price=" + price + ", keyword=" + keyword + ", content=" + content + ", created_at=" + created_at
				+ ", state=" + state + "]";
	}

	
	
}
