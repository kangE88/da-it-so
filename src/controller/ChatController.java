package controller;

import dto.ChatDto;
import service.ChatService;
import service.ChatServiceImpl;

public class ChatController {

	ChatServiceImpl chatService = new ChatService();
	public boolean insert(ChatDto chat){
        return chatService.insert(chat);
    }
}
