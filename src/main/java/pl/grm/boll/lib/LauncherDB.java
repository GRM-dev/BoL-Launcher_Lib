package pl.grm.boll.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LauncherDB extends Remote {
	
	public Result checkIfExists(String str) throws RemoteException;
	
	public Result checkPasswd(String str) throws RemoteException;
	
	public Result checkIfActivated(String str) throws RemoteException;
	
	public Player getPlayerData(String str) throws RemoteException;
	
}
