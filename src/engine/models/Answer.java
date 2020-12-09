package engine.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonFormat(shape = Shape.OBJECT)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Answer {

    CORRECT(true, "Congratulations, you're right!"),
    WRONG(false, "Wrong answer! Please, try again.");

    private final boolean success;
    private final String feedback;
}
