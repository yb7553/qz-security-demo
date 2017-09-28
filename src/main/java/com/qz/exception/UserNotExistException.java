/**
 * 
 */
package com.qz.exception;

/**
 * Author:yb
 *
 * Time:2017年9月18日下午5:28:08
 */
public class UserNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6112780192479692859L;
	
	private String id;
	
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
