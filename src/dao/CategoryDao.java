package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import db.MySqlConnection;
import db.OracleConnection;
import delegator.Delegator;
import dto.Category;

public class CategoryDao implements CategoryDaoImpl {

	public List<Category> getAllCategories(int state) {

		String sql = "SELECT * FROM CATEGORY WHERE STATE =" + state;

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		List<Category> categoryList = new ArrayList<>();
		ResultSet rs = null;

		System.out.println(">>>	CategoryDao .getAllCategoryList() sql : " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql); // query 를 실행하라 그리고 그 값을 rs에 저장해라.

			while (rs.next()) {
				Category category = new Category();

				category.setSeq(rs.getInt("seq"));
				category.setTitle(rs.getString("title"));
				category.setState(rs.getInt("state"));
				category.setDescription(rs.getString("description"));

				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return categoryList;
	}

	public Category getCategory(int seq) {

		String sql = " SELECT * FROM CATEGORY WHERE seq =" + seq;

		Connection conn = Delegator.getInstance().DBConnector.makeConnection();
		PreparedStatement pstmt = null;

		List<Category> categoryList = new ArrayList<>();
		ResultSet rs = null;

		System.out.println(">>>   CategoryDao .getAllCategoryList() sql : " + sql);

		Category categoryDto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			if (rs.next()) {
				categoryDto = new Category();

				categoryDto.setSeq(rs.getInt("seq"));
				categoryDto.setTitle(rs.getString("title"));
				categoryDto.setState(rs.getInt("state"));
				categoryDto.setDescription(rs.getString("description"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}

		return categoryDto;
	}

}
