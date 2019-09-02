package com.qkby.exception;

public class BusinessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;//错误码
	private String msg;//错误信息
	public BusinessException(){
		super();
	}
	
	public BusinessException(String code,String msg){
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
}
