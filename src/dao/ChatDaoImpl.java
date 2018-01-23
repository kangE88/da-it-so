package dao;

import java.util.List;

import dto.ChatDto;

public interface ChatDaoImpl {
	public List<ChatDto> getChatByRoomSeq(int seq);
    public boolean insert(ChatDto chat);
}
