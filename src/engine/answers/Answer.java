package engine.answers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Answer {

    private final boolean success;
    private final String feedback;
}
