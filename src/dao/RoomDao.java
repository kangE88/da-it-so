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
import dto.RoomDto;

public class RoomDao implements RoomDaoImpl{
	DBConnection DBConnector = new OracleConnection();
//	DBConnection DBConnector = new MySqlConnection();
	
	public List<RoomDto> getRoomByUesrId(String user_id) {
		
        String sql = "select * from room where user_id='"+user_id+"' or target_id='"+user_id+"'";

        System.out.println(">>> RoomDao .getRoomByUesrId() sql: "+sql);
        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;

        List<RoomDto> list = new ArrayList<>();


        try {
            conn = DBConnector.makeConnection();
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();

            while(rs.next()){
                RoomDto room = new RoomDto();

                room.setSeq(rs.getInt("seq"));
                room.setUser_id(rs.getString("user_id"));
                room.setTarget_id(rs.getString("target_id"));

                list.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

	public boolean makeRoom(String user, String target) {
		// TODO Auto-generated method stub
		String sql = " insert into room values(room_seq.nextval, '"+user+"', '"+target+"','"+user+"와 "+target+"의 대화 "+"' , sysdate)";
		
		System.out.println(">>> RoomDao .makeRoom() sql: "+sql);
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

	public RoomDto checkRoom(String user, String target) {
		// TODO Auto-generated method stub
		String sql = " select * from room where user_id='"+user+"' and target_id='"+target+"'";
		
		System.out.println(">>> RoomDao .checkRoom() sql: "+sql);
		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		RoomDto room = null;
		
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				room = new RoomDto();
				
				room.setSeq(rs.getInt("seq"));
				room.setTarget_id(rs.getString("target_id"));
				room.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return room;
	}
}
