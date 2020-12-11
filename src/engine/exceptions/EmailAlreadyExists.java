package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = EmailAlreadyExists.MESSAGE)
public class EmailAlreadyExists extends RuntimeException {

    static final String MESSAGE = "Email already taken.";
}
