package service;

import java.util.UUID;

import exception.InvalidGameException;
import exception.InvalidParamException;
import exception.NotFoundExceptionG;
import model.Game;
import model.GamePlay;
import model.GameStatus;
import model.Player;
import model.TicToe;
import storage.GameStorage;

public class GameService {

	public Game createGame(Player player) {
		Game game= new Game();
		game.setBoard(new int[3][3]);
		game.setGameId(UUID.randomUUID().toString());
		game.setPlayer1(player);
		game.setStatus(GameStatus.NEW);
		//store our game
		GameStorage.getInstance().setGame(game);
		return game;
	}
	public Game connectToGame(Player player2,String gameId) throws InvalidParamException, InvalidGameException
	{
		if(!GameStorage.getInstance().getGames().containsKey(gameId))
		{
			throw new InvalidParamException("Game with provided Id dosn't exist");
		}
		
		Game game=GameStorage.getInstance().getGames().get(gameId);
		if(game.getPlayer2()!=null)
		{
			throw new InvalidGameException("Game is not valid anymore");
		}

		game.setPlayer2(player2);
		game.setStatus(GameStatus.IN_PROGRESS);
		
		GameStorage.getInstance().setGame(game);
		return game;
	}
	public Game connectToRandomGame(Player player2) throws NotFoundExceptionG
	{
		Game game=GameStorage.getInstance().getGames().values().stream()
		.filter(it->it.getStatus().equals(GameStatus.NEW))
		.findFirst().orElseThrow(()->new NotFoundExceptionG("Game not found"));
		game.setPlayer2(player2);
		game.setStatus(GameStatus.IN_PROGRESS);
		GameStorage.getInstance().setGame(game);
		return game;
	}
	
	public Game gamePlay(GamePlay gamePlay) throws NotFoundExceptionG, InvalidGameException
	{
		if(!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId()))
		{
			throw new NotFoundExceptionG("Game not found");
		}
		Game game =GameStorage.getInstance().getGames().get(gamePlay.getGameId());
		if(game.getStatus().equals(GameStatus.FINISHED))
		{
			throw new InvalidGameException("Game has already finished");
		}
		int [][] board = game.getBoard();
		board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()]=gamePlay.getType().getValue();
		
		Boolean xwinner = checkWiner(game.getBoard(),TicToe.X);
		Boolean owinner=checkWiner(game.getBoard(),TicToe.O);
		if(xwinner)
		{
			game.setWinner(TicToe.X);
		}else if(owinner)
		{
			game.setWinner(TicToe.O);
		}
		GameStorage.getInstance().setGame(game);
		return game;
	}
	
	public Boolean checkWiner(int[][] board,TicToe ticToe)
	{
		int[] boardArray = new int[9];
		int counterIndex = 0 ;
		for(int i=0 ;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				boardArray[counterIndex]=board[i][j];
				counterIndex++;
			}
		}
		
		int [][]winnerCombinations= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		for(int i=0;i<winnerCombinations.length;i++)
		{
			int counter=0;
			for(int j=0;j<winnerCombinations[i].length;j++)
			{
				if(boardArray[winnerCombinations[i][j]]==ticToe.getValue())
				{
					counter++;
					System.out.println(counter);
					if(counter==3)
					{
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
}
