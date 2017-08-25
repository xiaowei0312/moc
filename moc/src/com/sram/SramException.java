package com.sram;

public class SramException extends RuntimeException {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	public SramException() {
		super();
	}

	public SramException(String msg) {
		super(msg);
	}

	public SramException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SramException(Throwable cause) {
		super(cause);
	}

}