package com.order.it.returncode;

public class ReturnCode {

	Object[] data;
	int retCode;
	String msg;

	public Object[] getData() {
		return data;
	}
	public void setData(Object[] data) {
		this.data = data;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ReturnCode( int retCode, String msg, Object[] data) {
		super();
		this.data = data;
		this.retCode = retCode;
		this.msg = msg;
	}
	public ReturnCode() {
		super();
	}
	// codes: 1007 success
	// 1008 fail
	public ReturnCode(int retCode, String msg) {
		// TODO Auto-generated constructor stub
		super();
		this.retCode = retCode;
		this.msg = msg;
	}
}
