package engine.exceptions;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = Unauthorized.MESSAGE)
public class Unauthorized extends RuntimeException {

    static final String MESSAGE = "User is not the author";

    public Unauthorized() {
        super(MESSAGE);
    }
}
