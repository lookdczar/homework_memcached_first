package cn.gyyx.java.test;

import cn.gyyx.java.bll.GameBLL;
import cn.gyyx.java.model.Game;
import cn.gyyx.java.utility.Memcached;
import cn.gyyx.java.dao.GameDAO;

import org.junit.Before;
import org.junit.Test;


public class test
{
	@Test
	public  void test() {
		
		Memcached client = Memcached.getInstance();
		GameBLL gameBll = new GameBLL();
		GameDAO gameDao = new GameDAO();
		Game gameFDB;
		Game gameFMemcached;
		
		Game theGame = new Game();
		//theGame.setId(31);
		theGame.setGameDes("this is a test");
		theGame.setGameName("test");
		
		gameFDB = gameBll.AddNewGame(theGame.getGameName(), theGame.getGameDes());
		System.out.println("new Game Added:gameName:"+gameFDB.getGameName()+",gameDes:"+gameFDB.getGameDes()+",gameId:"+gameFDB.getId());
		
		gameFMemcached = gameBll.GetGameById(gameFDB.getId());
		System.out.println("new Game Added to Memcached:gameName:"+gameFMemcached.getGameName()+",gameDes:"+gameFMemcached.getGameDes());
		
		theGame.setGameDes("this is a test2");
		theGame.setGameName("test2");
		gameFDB = gameBll.updateGame(theGame.getGameName(), theGame.getGameDes(),  gameFDB.getId());
		System.out.println("Game upDated：gameName:"+gameFDB.getGameName()+",gameDes:"+gameFDB.getGameDes());

		gameFMemcached = gameBll.GetGameById(gameFDB.getId());
		System.out.println("Game in Memcached was upDated:gameName:"+gameFMemcached.getGameName()+",gameDes:"+gameFMemcached.getGameDes());
//		

	}
}