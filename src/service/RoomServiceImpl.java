package service;

import java.util.List;

import dto.Person;
import dto.RoomDto;

public interface RoomServiceImpl {
	public RoomDto checkRoom(String user, String target);
	public RoomDto makeRoom(String user, String target);
	public List<RoomDto> getRoomByUesrId(String user_id);
}
