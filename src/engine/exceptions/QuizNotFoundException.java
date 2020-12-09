package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = QuizNotFoundException.MESSAGE)
public class QuizNotFoundException extends RuntimeException {

    static final String MESSAGE = "Quiz Not Found";

    public QuizNotFoundException() {
        super(MESSAGE);
    }
}
