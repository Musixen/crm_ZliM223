package ch.zli.m223.ksh18a.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad arguments")
public class InvalidArgumentException 
	extends NoStackTraceException {
}
