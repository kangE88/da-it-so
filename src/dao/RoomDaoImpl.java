package dao;

import java.util.List;

import dto.RoomDto;

public interface RoomDaoImpl {
	RoomDto checkRoom(String user, String target);
	boolean makeRoom(String user, String target);
	List<RoomDto> getRoomByUesrId(String user_id);
}
