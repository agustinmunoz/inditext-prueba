package com.agustin.munoz.inditex.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author agust
 *
 */

public class InditexException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String inditexCode;

	public InditexException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param msg
	 * @param inditexCode
	 * @param httpStatus
	 */
	public InditexException(String msg, String inditexCode, HttpStatus httpStatus) {
		super(msg);
		this.status = httpStatus;
		this.inditexCode = inditexCode;

	}

	/**
	 * 
	 * agust
	 * @return
	 * HttpStatus
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * agust
	 * @param status
	 * void
	 */
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * agust
	 * @return
	 * String
	 */
	public String getInditexCode() {
		return inditexCode;
	}

	
	/**
	 * 
	 * agust
	 * @param inditexCode
	 * void
	 */
	public void setInditexCode(String inditexCode) {
		this.inditexCode = inditexCode;
	}

}
