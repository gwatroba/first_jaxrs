package com.greg.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.greg.messenger.model.Message;
import com.greg.messenger.service.MessageService;



@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start, @QueryParam("size") int size){
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		else if (start >= 0  && size >= 0){
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Message addMessage(Message message) {
		messageService.addMessage(message);
		return message;
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);		
	}
		
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") Long messageId) {
		return messageService.removeMessage(messageId);		
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId){
		return messageService.getMessage(messageId);
	}
	
	
	
}
