package com.apisecurity.APISecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Insufficient Number of header column")
public class InsufficientNoOfHeadersException extends RuntimeException{
}
