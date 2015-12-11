package com.greg.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.greg.messenger.database.DatabaseClass;
import com.greg.messenger.model.Message;
import java.util.Map;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Siema", "Zenek"));
		messages.put(2L, new Message(2, "Hey", "Tadek"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if (calendar.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}			
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start+size > list.size()){
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
		
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	

}
