package com.crud.dynamodb.exception;

public class EntityNotFoundExeceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundExeceptions(String msg) {
		super(msg);
	}
	
}
