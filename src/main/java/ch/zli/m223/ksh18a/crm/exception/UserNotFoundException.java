package ch.zli.m223.ksh18a.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User not found")
public class UserNotFoundException
	extends NoStackTraceException {
}
