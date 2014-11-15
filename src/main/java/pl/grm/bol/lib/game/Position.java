package pl.grm.bol.lib.game;

import java.io.Serializable;

public class Position implements Serializable {
	private float	posX;
	private float	posY;
	private float	posZ;
	private float	rotX;
	private float	rotY;
	private float	rotZ;
	private int		var;
	
	public float getPosX() {
		return this.posX;
	}
	
	public void setPosX(float posX) {
		this.posX = posX;
	}
	
	public float getPosY() {
		return this.posY;
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public float getPosZ() {
		return this.posZ;
	}
	
	public void setPosZ(float posZ) {
		this.posZ = posZ;
	}
	
	public float getRotX() {
		return this.rotX;
	}
	
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	
	public float getRotY() {
		return this.rotY;
	}
	
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	
	public float getRotZ() {
		return this.rotZ;
	}
	
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	
	public int getVar() {
		return this.var;
	}
	
	public void setVar(int var) {
		this.var = var;
	}
}
