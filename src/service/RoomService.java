package service;

import java.util.List;

import dao.RoomDao;
import dao.RoomDaoImpl;
import dto.RoomDto;

public class RoomService implements RoomServiceImpl{

	RoomDaoImpl roomDao = new RoomDao();
	
	@Override
	public RoomDto checkRoom(String user, String target) {
		RoomDto room = roomDao.checkRoom(user, target);
		return room;
	}
	

    @Override
    public List<RoomDto> getRoomByUesrId(String user_id) {
        return roomDao.getRoomByUesrId(user_id);
    }


	@Override
	public RoomDto makeRoom(String user, String target) {
		boolean result = roomDao.makeRoom(user, target);
		RoomDto room = null;
		
		if(result) {
			room = roomDao.checkRoom(user, target);
		}
		System.out.println(room);
		return room;
	}

}
