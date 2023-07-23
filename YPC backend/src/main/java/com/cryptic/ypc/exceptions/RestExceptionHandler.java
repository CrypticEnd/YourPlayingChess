package com.cryptic.ypc.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(NotFoundException e) {
		logger.error("NOT_FOUND 404 : " + e.getMessage());
		return new ApiErrorResponse(e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handle(BadRequestException e) {
		logger.error("BAD_REQUEST 400 : " + e.getMessage());
		return new ApiErrorResponse(e.getMessage());
	}

	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ApiErrorResponse handle(ForbiddenException e) {
		logger.error("FORBIDDEN 403 : " + e.getMessage());
		return new ApiErrorResponse(e.getMessage());
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiErrorResponse handle(UnauthorizedException e) {
		logger.error("UNAUTHORIZED 401 : " + e.getMessage());
		return new ApiErrorResponse(e.getMessage());
	}

	/**
	 * Throws a 418 because the data is correctly formated, and you have the rights
	 * to make a move. But cannot make the move due to incorrect piece or piece is
	 * moving in incorrect way.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvaildMoveException.class)
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public ApiErrorResponse handle(InvaildMoveException e) {
		logger.error("INVAILD_MOVE 418 : " + e.getMessage());
		return new ApiErrorResponse(e.getMessage());
	}
}
