package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import db.MySqlConnection;
import db.OracleConnection;
import delegator.Delegator;
import dto.ChatDto;

public class ChatDao implements ChatDaoImpl {

	public List<ChatDto> getChatByRoomSeq(int seq) {
		String sql = "select * from chat where room_id=" + seq + " order by seq";

		List<ChatDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		try {
			conn = Delegator.getInstance().DBConnector.makeConnection();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				ChatDto dto = new ChatDto();

				dto.setContent(rs.getString("content"));
				dto.setRoom_id(rs.getInt("room_id"));
				dto.setSeq(rs.getInt("seq"));
				dto.setUser_id(rs.getString("user_id"));

				list.add(dto);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public boolean insert(ChatDto chat) {
		String sql;

		if (Delegator.getInstance().DBConnector.getClass().getName().equals("db.MySqlConnection")) {
			sql = " insert into chat(room_id, user_id, content, created_at) "
					+ "values(" + chat.getRoom_id() + ", '"+ chat.getUser_id() + "', '" + chat.getContent() + "', now())";
		} else {
			sql = " insert into chat "
				+ "values ( chat_seq.nextval, " + chat.getRoom_id() + ", '"
				+ chat.getUser_id() + "', '" + chat.getContent() + "', sysdate)";
		}

		Connection conn = null;
		PreparedStatement ptmt = null;
		int count = -1;

		try {
			conn = Delegator.getInstance().DBConnector.makeConnection();
			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return count > 0 ? true : false;
	}
}
