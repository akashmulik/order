package com.order.it.returncode;

public enum StatusCode {

	DONE(1007), FAIL(1008);
	private int status;
	private StatusCode(int status) {
		this.status = status;
	}
}
