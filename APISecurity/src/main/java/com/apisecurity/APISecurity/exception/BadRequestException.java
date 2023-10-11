package com.apisecurity.APISecurity.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpStatusException {
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}