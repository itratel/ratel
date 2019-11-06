package org.ratelframework.ratel.common.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.common.core.utils.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return ResponseResult
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseResult exception(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return ResponseResult.error(e);
	}

	/**
	 * validation Exception
	 *
	 * @param exception
	 * @return ResponseResult
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseResult bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		ResponseResult result = new ResponseResult();
		result.setMsg(fieldErrors.get(0).getDefaultMessage());
		log.warn(fieldErrors.get(0).getDefaultMessage());
		return result;
	}

}
