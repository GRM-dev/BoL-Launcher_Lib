package pl.grm.boll.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.grm.boll.lib.game.Player;

/**
 * Interface which extend Remote of RMI controller to game BOL.
 */
public interface LauncherDB extends Remote {
	/**
	 * Ensures that in database is @param login. If exists than will
	 * return the same name from database in Result->String.
	 * <p>
	 * If not it will be empty.
	 * <p>
	 * If there will be an exception than Result->exception would be not null.
	 * 
	 * @return {@link Result}
	 */
	public Result checkIfExists(String login) throws RemoteException;
	
	/**
	 * Compare @param login and @param password and
	 * 
	 * @return {@link Result} with boolean true if both params are equal.
	 * @throws RemoteException
	 */
	public Result checkPasswd(String login, String password) throws RemoteException;
	
	/**
	 * Checks if account is activated.
	 * 
	 * @param login
	 * @return {@link Result} boolean field true if activated.
	 * @throws RemoteException
	 */
	public Result checkIfActivated(String login) throws RemoteException;
	
	/**
	 * Checks user Salt.
	 * 
	 * @param login
	 * @return {@link Result} with resultString containing userSalt.
	 * @throws RemoteException
	 */
	public Result checkSalt(String login) throws RemoteException;
	
	/**
	 * Not implemented yet!
	 * <p>
	 * Downloads all player data from DB.
	 * 
	 * @param login
	 * @return {@link Player}
	 * @throws RemoteException
	 */
	public Player getPlayerData(String login) throws RemoteException;
}
