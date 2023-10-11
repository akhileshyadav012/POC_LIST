package com.apisecurity.APISecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Token not expired yet.Try after Sometime.")
public class TokenNotExpiredException extends RuntimeException{
}
