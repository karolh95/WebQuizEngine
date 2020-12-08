package engine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Quiz {
    private final String title;
    private final String text;
    private final String[] options;
}
