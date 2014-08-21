package cn.gyyx.java.bll;

import java.util.Date;
import java.util.List;

import cn.gyyx.java.dao.GameDAO;
import cn.gyyx.java.model.Game;
import cn.gyyx.java.utility.Memcached;




public class GameBLL{
	private static GameDAO gameDao;

	public GameBLL()
	{
		GameBLL.gameDao = new GameDAO();
		
	}

	public Game GetGameById(int id){
		Game theGame;
		theGame=gameDao.memcachedGetByGameId(id);
		if(theGame==null){
			theGame=gameDao.selectByGameId(id);
			gameDao.memcachedSetByGame(theGame);
		}
		return theGame;
	}
	
public List<Game> GetGameList(){
		return  gameDao.selectGameList();
	}

public Game AddNewGame(String gameName,String gameDes){

	Game theGame = new Game();
	theGame.setGameDes(gameDes);
	theGame.setGameName(gameName);
	int id = gameDao.insertGame(theGame);
	
	if(id>0)
	{
		theGame.setId(id);
		gameDao.memcachedSetByGame(theGame);
		return theGame;
	}
	return null;
}

public Game updateGame(String gameName,String gameDes,int id){

	Game theGame = new Game();
	theGame.setGameDes(gameDes);
	theGame.setGameName(gameName);
	theGame.setId(id);
	int res = gameDao.updateGame(theGame);
	
	if(res>0)
	{
		gameDao.memcachedSetByGame(theGame);
		return theGame;
	}
	return null;
}
	

}
