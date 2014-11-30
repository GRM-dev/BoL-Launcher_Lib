package pl.grm.bol.lib;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** Type of query listed in LauncherDB starting from 1 */
	private byte				RESULT_TYPE			= 0;
	private Exception			exception;
	private String				resultString;
	private int					resultInt;
	private long				resultLong;
	private float				resultFloat;
	private double				resutDouble;
	private boolean				resultBoolean;
	private int[]				resultIntArray;
	
	public Result(int i) {
		this.RESULT_TYPE = Byte.parseByte(Integer.toString(i));
	}
	
	public synchronized Exception getException() {
		return this.exception;
	}
	
	public synchronized void setException(Exception exception) {
		this.exception = exception;
	}
	
	public synchronized String getResultString() {
		return this.resultString;
	}
	
	public synchronized void setResultString(String resultString) {
		this.resultString = resultString;
	}
	
	public synchronized int getResultInt() {
		return this.resultInt;
	}
	
	public synchronized void setResultInt(int resultInt) {
		this.resultInt = resultInt;
	}
	
	public synchronized long getResultLong() {
		return this.resultLong;
	}
	
	public synchronized void setResultLong(long resultLong) {
		this.resultLong = resultLong;
	}
	
	public synchronized float getResultFloat() {
		return this.resultFloat;
	}
	
	public synchronized void setResultFloat(float resultFloat) {
		this.resultFloat = resultFloat;
	}
	
	public synchronized double getResutDouble() {
		return this.resutDouble;
	}
	
	public synchronized void setResutDouble(double resutDouble) {
		this.resutDouble = resutDouble;
	}
	
	public synchronized boolean isResultBoolean() {
		return this.resultBoolean;
	}
	
	public synchronized void setResultBoolean(boolean resultBoolean) {
		this.resultBoolean = resultBoolean;
	}
	
	public synchronized int[] getResultIntArray() {
		return this.resultIntArray;
	}
	
	public synchronized void setResultIntArray(int[] resultIntArray) {
		this.resultIntArray = resultIntArray;
	}
	
	public synchronized byte getRESULT_TYPE() {
		return this.RESULT_TYPE;
	}
}
