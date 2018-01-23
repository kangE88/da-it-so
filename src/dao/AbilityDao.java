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
import dto.AbilityBbs;
import dto.Person;

public class AbilityDao implements AbilityDaoImpl {
	
	public List<AbilityBbs> allAbilityList() {

		String sql = "SELECT * FROM ABILITY_BBS WHERE STATE = 0 OR STATE = 1 ORDER BY CREATED_AT ";

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AbilityBbs> AbilityList = new ArrayList<>();

		System.out.println(">>>	AbilityBbsDao .allAbilityList() sql : " + sql);

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // query


			while(rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(Integer.parseInt(rs.getString("SEQ")));
				abilityDto.setCategory_id(Integer.parseInt(rs.getString("CATEGORY_ID")));
				abilityDto.setTitle(rs.getString("TITLE"));

				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setState(Integer.parseInt(rs.getString("STATE")));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));

				AbilityList.add(abilityDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return AbilityList;
	}

	// 관리자 전용 
	public List<AbilityBbs> getAllAbilityList() {

		String sql = "SELECT * FROM ABILITY_BBS";

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<AbilityBbs> AbilityList = new ArrayList<>();

		System.out.println(">>>	AbilityBbsDao .getAllAbilityList() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql); // query

			while (rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(rs.getInt("SEQ"));
				abilityDto.setCategory_id(rs.getInt("CATEGORY_ID"));
				abilityDto.setTitle(rs.getString("TITLE"));
				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));

				AbilityList.add(abilityDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return AbilityList;
	}

	public boolean addAbility(AbilityBbs abilityDto) {
		Delegator delegator = Delegator.getInstance();
		Person personDto = delegator.getCurrent_user();

		String sql;

		String ability = abilityDto.getAbility().replaceAll(" ", "");
		ability = ability.replaceAll("#", "-key-");
		
		System.out.println(ability);
		if (Delegator.getInstance().DBConnector.getClass().getName().equals("db.MySqlConnection")) {
			sql = " insert into ability_bbs(category_id, user_id, title, imgurl1, imgurl2, imgurl3, imgurl4, ability, content, state, created_at) "
					+ " values( " + abilityDto.getCategory_id() + ", '" + delegator.getCurrent_user().getId() + "', '"
					+ abilityDto.getTitle() + "', '" + abilityDto.getImgurl1() + "', '" + abilityDto.getImgurl2()
					+ "', '" + abilityDto.getImgurl3() + "', '" + abilityDto.getImgurl4() + "', '"
					+ ability + "', '" + abilityDto.getContent() + "', 0, now());";
		} else {
			sql ="INSERT INTO ABILITY_BBS(SEQ, CATEGORY_ID, TITLE, IMGURL1, IMGURL2, IMGURL3, IMGURL4, ABILITY, CONTENT, STATE, CREATED_AT, USER_ID)"
					+" VALUES(SEQ_ABILITY_BBS.NEXTVAL,'"+abilityDto.getCategory_id()+"','"+abilityDto.getTitle()+"','"+abilityDto.getImgurl1()+"','"+abilityDto.getImgurl2()+"','"+abilityDto.getImgurl3()+"','"+abilityDto.getImgurl4()+"','"+ability+"','"+abilityDto.getContent()+"', 0, SYSDATE, '"+personDto.getId()+"')";

		}

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		int count = -1;

		System.out.println(">>>	AbilityDao .addAbility() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			count = pstmt.executeUpdate(); // query

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return count > 0 ? true : false;
	}


	@Override
	public List<AbilityBbs> searchList(String searchWord) {

		List<AbilityBbs> searchlist = new ArrayList<AbilityBbs>();
		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement psmt = null;

		String sql = " SELECT * FROM ABILITY_BBS "
				+ " WHERE ( TITLE LIKE '%" + searchWord +"%'"
				+ " OR CONTENT LIKE '%" + searchWord + "%'"
				+ " OR ABILITY LIKE '%" + searchWord + "%')"
				+ " AND (STATE=0 OR STATE=1)";

		System.out.println(">>> AbilityDao.searchList()sql: " + sql);
		//select * from item_bbs where title like %something% or content like %something% or ability like %something%;

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);

			while(rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(rs.getInt("SEQ"));
				abilityDto.setCategory_id(rs.getInt("CATEGORY_ID"));
				abilityDto.setTitle(rs.getString("TITLE"));
				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));
				
				searchlist.add(abilityDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}

		return searchlist;
	}
	
	public List<AbilityBbs> getAbilityBbsByUserId(String user_id){

		String sql = " SELECT * FROM ABILITY_BBS WHERE (USER_ID='"+user_id+"') AND (STATE=0 OR STATE=1)";
		
		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<AbilityBbs> abilityList = new ArrayList<>();

		System.out.println(">>>	AbilityBbsDao .getAbilityBbsByUserId() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql); // query

			while (rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(rs.getInt("SEQ"));
				abilityDto.setCategory_id(rs.getInt("CATEGORY_ID"));
				abilityDto.setTitle(rs.getString("TITLE"));
				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));

				abilityList.add(abilityDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}
		
		
		return abilityList;
	}

	@Override
	public boolean DeleteAbilityBbsByAdmin(AbilityBbs ability) {
		String sql = " UPDATE ITEM_BBS SET STATE = 3 WHERE SEQ= "+ability.getSeq();

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
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
	public boolean CompleteAbilityBbs(AbilityBbs abilityDto) {
		// TODO Auto-generated method stub
		String sql = " UPDATE ABILITY_BBS SET STATE = 1 WHERE SEQ="+abilityDto.getSeq()+" and user_id='"+abilityDto.getUser_id()+"' ";

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
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
	public boolean DeleteAbilityBbs(AbilityBbs abilityDto) {
		// TODO Auto-generated method stub
		//0=진행중 1=완료 2=삭제 3=관리자삭제
		String sql = " UPDATE ABILITY_BBS SET STATE = 2 WHERE USER_ID = '"+ abilityDto.getUser_id() +"' AND SEQ = "+abilityDto.getSeq();

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		System.out.println(">>>	AbilityBbsDao .DeleteAbilityList() sql : " + sql);

		int count = -1;

		try {
			pstmt = conn.prepareStatement(sql);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (count > 0) ? true : false;
	}

	@Override
	public boolean ContinueAbilityBbsByAdmin(AbilityBbs ability) {
		// TODO Auto-generated method stub
		String sql = " UPDATE ITEM_BBS SET STATE = 0 WHERE SEQ="+ability.getSeq();

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
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
	public List<AbilityBbs> AdminSearch(String search) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM ABILITY_BBS "
				+ " WHERE TITLE LIKE '%" + search +"%'"
				+ " OR CONTENT LIKE '%" + search + "%'"
				+ " OR ABILITY LIKE '%" + search + "%'";
		
		System.out.println(">>> AbilityDao  .AdminSearch()  sql: " + sql);
		
		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		List<AbilityBbs> searchList = new ArrayList<>();
		
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(Integer.parseInt(rs.getString("SEQ")));
				abilityDto.setCategory_id(Integer.parseInt(rs.getString("CATEGORY_ID")));
				abilityDto.setTitle(rs.getString("TITLE"));

				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setState(Integer.parseInt(rs.getString("STATE")));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));

				searchList.add(abilityDto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchList;
	}

	@Override
	public List<AbilityBbs> SelectAbilityCategories(int category_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ABILITY_BBS WHERE CATEGORY_ID="+category_id+" AND (STATE=0 OR STATE=1) ";

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AbilityBbs> abilityList = new ArrayList<>();

		System.out.println(">>>	AbilityBbsDao .SelectAbilityCategories() sql : " + sql);

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // query


			while(rs.next()) {
				AbilityBbs abilityDto = new AbilityBbs();

				abilityDto.setSeq(Integer.parseInt(rs.getString("SEQ")));
				abilityDto.setCategory_id(Integer.parseInt(rs.getString("CATEGORY_ID")));
				abilityDto.setTitle(rs.getString("TITLE"));

				abilityDto.setImgurl1(rs.getString("IMGURL1"));
				abilityDto.setImgurl2(rs.getString("IMGURL2"));
				abilityDto.setImgurl3(rs.getString("IMGURL3"));
				abilityDto.setImgurl4(rs.getString("IMGURL4"));
				abilityDto.setAbility(rs.getString("ABILITY"));
				abilityDto.setContent(rs.getString("CONTENT"));
				abilityDto.setState(Integer.parseInt(rs.getString("STATE")));
				abilityDto.setCreated_at(rs.getString("CREATED_AT"));
				abilityDto.setUser_id(rs.getString("USER_ID"));

				abilityList.add(abilityDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return abilityList;
	}

	@Override
	public AbilityBbs getAbilityByTilteAndContent(AbilityBbs abilityDto) {
		// TODO Auto-generated method stub
		String sql = " select * from ability_bbs where title='"+abilityDto.getTitle()+"' and content='"+abilityDto.getContent()+"'";
		
		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		AbilityBbs ability = null;
		
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				ability = new AbilityBbs();
				
				ability.setSeq(Integer.parseInt(rs.getString("SEQ")));
				ability.setCategory_id(Integer.parseInt(rs.getString("CATEGORY_ID")));
				ability.setTitle(rs.getString("TITLE"));
				
				ability.setImgurl1(rs.getString("IMGURL1"));
				ability.setImgurl2(rs.getString("IMGURL2"));
				ability.setImgurl3(rs.getString("IMGURL3"));
				ability.setImgurl4(rs.getString("IMGURL4"));
				ability.setAbility(rs.getString("ABILITY"));
				ability.setContent(rs.getString("CONTENT"));
				ability.setState(Integer.parseInt(rs.getString("STATE")));
				ability.setCreated_at(rs.getString("CREATED_AT"));
				ability.setUser_id(rs.getString("USER_ID"));	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ability;
	}

}
