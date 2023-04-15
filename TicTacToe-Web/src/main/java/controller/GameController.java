package controller;

import java.io.IOException;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.InvalidGameException;
import exception.InvalidParamException;
import exception.NotFoundExceptionG;
import model.GamePlay;
import model.Player;
import service.GameService;
@Path("/game")
public class GameController {
	private final GameService gameService = new GameService();
	@POST
	@Path("/start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("log")String login) throws JsonParseException, JsonMappingException, IOException
	{
		Player player= new Player();
		byte[] jsonData = login.getBytes();
        ObjectMapper mapper = new ObjectMapper();
		player = mapper.readValue(jsonData, Player.class);
		ObjectMapper obj= new ObjectMapper();
		String jsonstring="{}";
		jsonstring = obj.writeValueAsString(gameService.createGame(player));
		
		return jsonstring;
		
	}
	
	@POST
	@Path("/connect")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String connect(@PathParam("request")String request) throws InvalidParamException, InvalidGameException, JsonParseException, JsonMappingException, IOException
	{
		ConnectRequest requet =new ConnectRequest();
		byte[] jsonData = request.getBytes();
        ObjectMapper mapper = new ObjectMapper();
        requet = mapper.readValue(jsonData, ConnectRequest.class);
       // System.out.println( requet.getGameId());
		ObjectMapper obj= new ObjectMapper();
		String jsonstring="{}";
		jsonstring = obj.writeValueAsString(gameService.connectToGame(requet.getPlayer(), requet.getGameId()));
		return jsonstring;
		
	}
	
	@POST
	@Path("/connect/random")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String connectRandom(@PathParam("logg")String login) throws NotFoundExceptionG, JsonParseException, JsonMappingException, IOException
	{
		//System.out.println("test random");
		Player player= new Player();
		byte[] jsonData = login.getBytes();
        ObjectMapper mapper = new ObjectMapper();
		player = mapper.readValue(jsonData, Player.class);
		ObjectMapper obj= new ObjectMapper();
		String jsonstring="{}";
		jsonstring = obj.writeValueAsString(gameService.connectToRandomGame(player));

		return jsonstring;
	}

	@POST
	@Path("/gameplay")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String gameplay(String gamePlay) throws NotFoundExceptionG, InvalidGameException, IOException
	{
		GamePlay gameplay1= new GamePlay();
		byte[] jsonData = gamePlay.getBytes();
		//System.out.println(gamePlay);
        ObjectMapper mapper = new ObjectMapper();
        gameplay1= mapper.readValue(jsonData, GamePlay.class);
		ObjectMapper obj= new ObjectMapper();
		String jsonstring="{}";
		jsonstring = obj.writeValueAsString(gameService.gamePlay(gameplay1));
		return jsonstring;
		
	}
}
