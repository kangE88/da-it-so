package controller;

import java.util.ArrayList;
import java.util.List;

import delegator.Delegator;
import dto.ChatDto;
import dto.RoomDto;
import service.ChatService;
import service.ChatServiceImpl;
import service.RoomService;
import service.RoomServiceImpl;
import view.Room;
import view.RoomList;

public class RoomController {

	RoomServiceImpl roomService = new RoomService();
	ChatServiceImpl chatService = new ChatService();
	
	public void checkRoom(String target_id) {
		Delegator delegator = Delegator.getInstance();
		
		RoomDto room = roomService.checkRoom(delegator.getCurrent_user().getId(), target_id);
		
		if(room == null) {
			room = roomService.makeRoom(delegator.getCurrent_user().getId(), target_id);
		}
		
		List<ChatDto> list = chatService.getChatByRoomSeq(room.getSeq());
		
		if (list == null) {
			list = new ArrayList<>();			
		}
		
		System.out.println(room);
		
        new Room(list, room);
		
	}

	public void RoomList() {
		Delegator delegator = Delegator.getInstance();

        String user_id = delegator.getCurrent_user().getId();
        if(user_id==null){
            delegator.mainController.Main();
        }else {
            List<RoomDto> list = roomService.getRoomByUesrId(user_id);
            new RoomList(list);
        }
	}
	
	public void Chat(RoomDto room) {
		List<ChatDto> list = chatService.getChatByRoomSeq(room.getSeq());
		
		if (list == null) {
			list = new ArrayList<>();			
		}
		
        new Room(list, room);
	}
}
