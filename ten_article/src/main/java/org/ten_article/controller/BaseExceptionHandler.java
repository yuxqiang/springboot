package org.ten_article.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;

@ControllerAdvice
public class BaseExceptionHandler {
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result error(Exception e) {
		e.printStackTrace();
		return new Result(false, StatusCode.ERROR, e.getMessage());
	}
}
