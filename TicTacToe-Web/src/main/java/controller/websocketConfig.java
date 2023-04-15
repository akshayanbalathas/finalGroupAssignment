package controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.InvalidGameException;
import exception.NotFoundExceptionG;
import model.GamePlay;
import service.GameService;

@ServerEndpoint(value="/gameplay/{gameID}")
public class websocketConfig {
	@OnOpen
	public void open()
	{
		//LOG.info("openning the socket serever");
		System.out.println("Open connecting");
	
	}
	@OnMessage
	public void handleMessage(Session session,String message) throws IOException, NotFoundExceptionG, InvalidGameException
	{
		JSONObject json = new JSONObject(message);
		System.out.println("Message recu "+message);
		System.out.println("JSON Message recu "+json.toString());
		for(Session s1 : session.getOpenSessions())
		{
			s1.getAsyncRemote().sendText(json.toString());
		}
	}
				
	
	@OnClose
	public void close(Session userSession)
	{
		
	}
	
	private String buildJsonData(String username, String message)
	{
		JsonObject jsonObject = Json.createObjectBuilder().add("message",username+": "+message).build();
		StringWriter stringWriter=new StringWriter();
		try(JsonWriter jsonwriter=Json.createWriter(stringWriter))
		{
			jsonwriter.write(jsonObject);
			return stringWriter.toString();
		}
		
				
	}
	

	
}
