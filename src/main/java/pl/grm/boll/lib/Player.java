package pl.grm.boll.lib;

import java.io.Serializable;

public class Player implements Serializable {
	private String				playerName;
	private int					level;
	private double				exp;
	private Position			position;
	private PlayerConfiguration	pConfiguration;
}
