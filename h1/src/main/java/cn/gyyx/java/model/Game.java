package cn.gyyx.java.model;

import java.io.Serializable;


public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String gameName;
	private String gameDes;
	
	public int getId(){
		return id;
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public String getGameDes(){
		return gameDes;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	
	public void setGameDes(String gameDes){
		this.gameDes = gameDes;
	}

}
