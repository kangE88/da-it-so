package service;

import java.util.List;

import dto.ChatDto;

public interface ChatServiceImpl {
	public List<ChatDto> getChatByRoomSeq(int seq);
    public boolean insert(ChatDto chat);
}
