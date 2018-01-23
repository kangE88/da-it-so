package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import db.OracleConnection;
import delegator.Delegator;
import dto.ItemBbs;

public class ItemBbsDao implements ItemBbsDaoImpl{

	DBConnection DBConnector = new OracleConnection();
//	DBConnection DBConnector = new MySqlConnection();


	public List<ItemBbs> allItemList() {

		String sql = "SELECT * FROM ITEM_BBS WHERE STATE=0 OR STATE=1 ORDER BY CREATED_AT";

		Connection conn = DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<ItemBbs> itemList = new ArrayList<>();

		System.out.println(">>>	ItemBbsDao .allItemList() sql : " + sql);


		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql); // query 를 실행하라 그리고 그 값을 rs에 저장해라.

			while (rs.next()) {
				ItemBbs itemdto = new ItemBbs();

				itemdto.setSeq(rs.getInt("SEQ"));
				itemdto.setCategory_id(rs.getInt("CATEGORY_ID"));
				itemdto.setTitle(rs.getString("TITLE"));
				itemdto.setImgurl1(rs.getString("IMGURL1"));
				itemdto.setImgurl2(rs.getString("IMGURL2"));
				itemdto.setImgurl3(rs.getString("IMGURL3"));
				itemdto.setImgurl4(rs.getString("IMGURL4"));
				itemdto.setPrice(rs.getInt("PRICE"));
				itemdto.setKeyword(rs.getString("KEYWORD"));
				itemdto.setContent(rs.getString("CONTENT"));
				itemdto.setCreated_at(rs.getString("CREATED_AT"));
				itemdto.setUser_id(rs.getString("USER_ID"));

				itemList.add(itemdto);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, conn, rs);
		}
		return itemList;
	}

	public boolean addItem(ItemBbs itemDto) {
		Delegator delegator = Delegator.getInstance();

		String id = delegator.getCurrent_user().getId();

		String sql;

		if (DBConnector.getClass().getName().equals("db.MySqlConnection")) {
			sql = " insert into item_bbs(category_id, user_id, title, imgurl1, imgurl2, imgurl3, imgurl4, keyword, content, state, created_at) "
					+ " values( "+itemDto.getCategory_id()+", '"+delegator.getCurrent_user().getId()+"', '"+itemDto.getTitle()+"', '"
					+ itemDto.getImgurl1() +"', '"+itemDto.getImgurl2()+"', '"+itemDto.getImgurl3()+"', '"+itemDto.getImgurl4()+"', '"+itemDto.getKeyword()+"', '"+itemDto.getContent()+"', 1, now());";
		} else {
			sql ="INSERT INTO ITEM_BBS(SEQ, CATEGORY_ID, TITLE, IMGURL1, IMGURL2, IMGURL3, IMGURL4, PRICE, KEYWORD, CONTENT, STATE, CREATED_AT, USER_ID)"
					+" VALUES(IBBS_SEQ.NEXTVAL, '"+itemDto.getCategory_id()+"','"+itemDto.getTitle()+"','"+itemDto.getImgurl1()+"','"+itemDto.getImgurl2()+"','"+itemDto.getImgurl3()+"','"+itemDto.getImgurl4()+"','"+itemDto.getPrice()+"','"+itemDto.getKeyword()+"','"+itemDto.getContent()+"', 0, SYSDATE, '"+id+"')";

		}

		Connection conn = DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		int count = -1;

		System.out.println(">>>	ItemBbsDao .addItem() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			count = pstmt.executeUpdate(); // query 를 실행하라 그리고 그 값을 rs에 저장해라.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return (count > 0) ? true : false;
	}

	public List<ItemBbs> SelectItemCategories(int category_id) {

		String sql = "SELECT * FROM ITEM_BBS WHERE CATEGORY_ID = "+ category_id;
		
		Connection conn = DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		List<ItemBbs> itemList = new ArrayList<>();
		ResultSet rs = null;

		System.out.println(">>>	CategoryDao .SelectItemCategories() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql); // query 를 실행하라 그리고 그 값을 rs에 저장해라.

			while (rs.next()) {
				ItemBbs itemBbs = new ItemBbs();
				
				itemBbs.setSeq(rs.getInt("SEQ"));
				itemBbs.setCategory_id(rs.getInt("CATEGORY_ID"));
				itemBbs.setTitle(rs.getString("TITLE"));
				itemBbs.setImgurl1(rs.getString("IMGURL1"));
				itemBbs.setImgurl2(rs.getString("IMGURL2"));
				itemBbs.setImgurl3(rs.getString("IMGURL3"));
				itemBbs.setImgurl4(rs.getString("IMGURL4"));
				itemBbs.setPrice(rs.getInt("PRICE"));
				itemBbs.setKeyword(rs.getString("KEYWORD"));
				itemBbs.setContent(rs.getString("CONTENT"));
				itemBbs.setCreated_at(rs.getString("CREATED_AT"));
				itemBbs.setUser_id(rs.getString("USER_ID"));
				itemBbs.setState(rs.getInt("STATE"));
				
				
	
				itemList.add(itemBbs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return itemList;
	}

	@Override
	public List<ItemBbs> searchList(String searchWord) {

		List<ItemBbs> searchList = new ArrayList<ItemBbs>();

		ResultSet rs = null;
		PreparedStatement ptmt = null;

		String sql = " SELECT * FROM ITEM_BBS "
				+ " WHERE (TITLE LIKE '%" + searchWord +"%'"
				+ " OR CONTENT LIKE '%" + searchWord + "%'"
				+ " OR KEYWORD LIKE '%" + searchWord + "%')"//제목 컨텐츠 키워드
				+ " AND (STATE=0 OR STATE=1)";

		System.out.println(">>>	ItemBbsDao .searchList() sql : " + sql);
		//select * from item_bbs where title like %something% or content like %something% or ability like %something%;

		Connection conn = DBConnector.makeConnection();

		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while(rs.next()) {
				ItemBbs itemBbs = new ItemBbs();
				
				itemBbs.setSeq(rs.getInt("SEQ"));
				itemBbs.setCategory_id(rs.getInt("CATEGORY_ID"));
				itemBbs.setTitle(rs.getString("TITLE"));
				itemBbs.setImgurl1(rs.getString("IMGURL1"));
				itemBbs.setImgurl2(rs.getString("IMGURL2"));
				itemBbs.setImgurl3(rs.getString("IMGURL3"));
				itemBbs.setImgurl4(rs.getString("IMGURL4"));
				itemBbs.setPrice(rs.getInt("PRICE"));
				itemBbs.setKeyword(rs.getString("KEYWORD"));
				itemBbs.setContent(rs.getString("CONTENT"));
				itemBbs.setCreated_at(rs.getString("CREATED_AT"));
				itemBbs.setUser_id(rs.getString("USER_ID"));
				itemBbs.setState(rs.getInt("STATE"));
				
				
	
				searchList.add(itemBbs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(ptmt, conn, rs);
		}
		return searchList;
	}

	// 관리자 전용  
	public List<ItemBbs> getAllItemBbs() {

		String sql = " SELECT * FROM ITEM_BBS ";

		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;

		ResultSet rs = null;

		List<ItemBbs> list = new ArrayList<>();

		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while(rs.next()) {
				ItemBbs item = new ItemBbs();

				item.setSeq(rs.getInt("seq"));
				item.setCategory_id(rs.getInt("category_id"));
				item.setContent(rs.getString("content"));
				item.setCreated_at(rs.getString("created_at"));
				item.setImgurl1(rs.getString("imgurl1"));
				item.setImgurl2(rs.getString("imgurl2"));
				item.setImgurl3(rs.getString("imgurl3"));
				item.setImgurl4(rs.getString("imgurl4"));
				item.setKeyword(rs.getString("keyword"));
				item.setPrice(rs.getInt("price"));
				item.setState(rs.getInt("state"));
				item.setTitle(rs.getString("title"));
				item.setUser_id(rs.getString("user_id"));

				list.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<ItemBbs> getItemBbsByUserId(String user_id) {
		String sql = " SELECT * FROM ITEM_BBS WHERE USER_ID='"+user_id+"'";

		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;

		ResultSet rs = null;

		List<ItemBbs> list = new ArrayList<>();

		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while(rs.next()) {
				ItemBbs item = new ItemBbs();

				item.setSeq(rs.getInt("seq"));
				item.setCategory_id(rs.getInt("category_id"));
				item.setContent(rs.getString("content"));
				item.setCreated_at(rs.getString("created_at"));
				item.setImgurl1(rs.getString("imgurl1"));
				item.setImgurl2(rs.getString("imgurl2"));
				item.setImgurl3(rs.getString("imgurl3"));
				item.setImgurl4(rs.getString("imgurl4"));
				item.setKeyword(rs.getString("keyword"));
				item.setPrice(rs.getInt("price"));
				item.setState(rs.getInt("state"));
				item.setTitle(rs.getString("title"));
				item.setUser_id(rs.getString("user_id"));

				list.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean DeleteItemBbsByAdmin(ItemBbs item) {
		// TODO Auto-generated method stub
		// state가 3이면 관리자에 의한 삭제.
		String sql = " update item_bbs set state = 3 where seq="+item.getSeq();

		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;

		int count = -1;

		try {

			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (count > 0) ? true : false;
	}

	@Override
	public boolean CompleteItemBbsByAdmin(ItemBbs item) {
		// TODO Auto-generated method stub
		// state가 3이면 관리자에 의한 삭제.
		String sql = " update item_bbs set state = 1 where seq="+item.getSeq();

		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;

		int count = -1;

		try {

			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (count > 0) ? true : false;
	}

	@Override
	public boolean ContinueItemBbsByAdmin(ItemBbs item) {
		// TODO Auto-generated method stub
		// state가 3이면 관리자에 의한 삭제.
		String sql = " update item_bbs set state = 0 where seq="+item.getSeq();

		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;

		int count = -1;

		try {

			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (count > 0) ? true : false;
	}

	// 관리자 전용 
	@Override
	public List<ItemBbs> AdminSearch(String search) {
		// TODO Auto-generated method stub
		List<ItemBbs> searchList = new ArrayList<ItemBbs>();

		ResultSet rs = null;
		PreparedStatement ptmt = null;

		String sql = " SELECT * FROM ITEM_BBS "
				+ " WHERE TITLE LIKE '%" + search +"%'"
				+ " OR CONTENT LIKE '%" + search + "%'"
				+ " OR KEYWORD LIKE '%" + search + "%'";//제목 컨텐츠 키워드

		System.out.println(">>>	ItemBbsDao .AdminSearch() sql : " + sql);

		Connection conn = DBConnector.makeConnection();

		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while(rs.next()) {
				ItemBbs item = new ItemBbs();
				
				item.setSeq(rs.getInt("seq"));
				item.setCategory_id(rs.getInt("category_id"));
				item.setContent(rs.getString("content"));
				item.setCreated_at(rs.getString("created_at"));
				item.setImgurl1(rs.getString("imgurl1"));
				item.setImgurl2(rs.getString("imgurl2"));
				item.setImgurl3(rs.getString("imgurl3"));
				item.setImgurl4(rs.getString("imgurl4"));
				item.setKeyword(rs.getString("keyword"));
				item.setPrice(rs.getInt("price"));
				item.setState(rs.getInt("state"));
				item.setTitle(rs.getString("title"));
				item.setUser_id(rs.getString("user_id"));
				
				searchList.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(ptmt, conn, rs);
		}
		
		return searchList;
	}
}
