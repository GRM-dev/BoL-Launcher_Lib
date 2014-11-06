package pl.grm.boll.lib;

import java.io.Serializable;

public class Result implements Serializable {
	private boolean		conn_established	= false;
	/** Type of query listed in LauncherDB starting from 1 */
	private byte		RESULT_TYPE			= 0;
	private String		resultString		= null;
	private int			resultInt			= 0;
	private Exception	e;
	
	public Result(int i) {
		this.RESULT_TYPE = Byte.parseByte(Integer.toString(i));
	}
	
	public boolean isConn_established() {
		return this.conn_established;
	}
	
	public void setConn_established(boolean conn_established) {
		this.conn_established = conn_established;
	}
	
	public byte getRESULT_TYPE() {
		return this.RESULT_TYPE;
	}
	
	public void setRESULT_TYPE(byte rESULT_TYPE) {
		this.RESULT_TYPE = rESULT_TYPE;
	}
	
	public String getResultString() {
		return this.resultString;
	}
	
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	
	public int getResultInt() {
		return this.resultInt;
	}
	
	public void setResultInt(int resultInt) {
		this.resultInt = resultInt;
	}
	
	public Exception getE() {
		return this.e;
	}
	
	public void setE(Exception e) {
		this.e = e;
	}
	
}
