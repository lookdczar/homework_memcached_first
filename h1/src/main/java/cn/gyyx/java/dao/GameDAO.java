package cn.gyyx.java.dao;


import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import cn.gyyx.java.model.Game;
import cn.gyyx.java.utility.Memcached;




public class GameDAO {
	private SqlSessionFactory sqlSessionFactory;
	private String resource = "mybatis-config.xml";
	
	 
    public GameDAO(){
    	 Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			 sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 
       // sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }
    
    public Game selectByGameId(int gId){
    	SqlSession session = sqlSessionFactory.openSession();

		try {
			Game theGame = (Game) session.selectOne("Game.getGameById",gId);
			return theGame;
		} finally {
			session.close();
		}
    }
    
    public List<Game> selectGameList(){
    	 
        SqlSession session = sqlSessionFactory.openSession();
 
        try {
            List<Game> list = session.selectList("Game.getGameList");
            return list;
        } finally {
            session.close();
        }
    }
    
    public int insertGame(Game theGame){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		session.insert("Game.addGame", theGame);
    		session.commit();
    		return  theGame.getId();
    	}finally{
    		session.close();
    	}
    }
    
    public int updateGame(Game theGame){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		int res =  session.update("Game.updateGame", theGame);
    		session.commit();
    		return res;
    	}finally{
    		session.close();
    	}
    }
    	
    public Game memcachedGetByGameId(int id){
    	Memcached client = Memcached.getInstance();
		return (Game) client.get("gameId:"+id);	
    }
    
    public void memcachedSetByGame(Game theGame){
    	Memcached client = Memcached.getInstance();
		client.set("gameId:"+theGame.getId(), 3600, theGame);
    }
    
}
