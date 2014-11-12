package pl.grm.boll.lib.game;

import java.io.Serializable;

public class Player implements Serializable {
	private String				playerName;
	private int					level;
	private double				experience;
	private Position			position;
	private PlayerConfiguration	pConfiguration;
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public double getExperience() {
		return this.experience;
	}
	
	public void setExperience(double experience) {
		this.experience = experience;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public PlayerConfiguration getpConfiguration() {
		return this.pConfiguration;
	}
	
	public void setpConfiguration(PlayerConfiguration pConfiguration) {
		this.pConfiguration = pConfiguration;
	}
}
