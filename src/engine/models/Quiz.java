package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Quiz {

    @NotEmpty(message = "Title should not be empty.")
    private final String title;
    @NotEmpty(message = "Text should not be empty.")
    private final String text;
    @NotNull(message = "Options should not be empty.")
    @Size(min = 2, message = "Options should contain at least 2 elements.")
    private final String[] options;
    @JsonProperty(access = Access.WRITE_ONLY)
    private final List<Integer> answer;
    @Setter
    private int id;

    public Answer testAnswer(List<Integer> answer) {

        boolean isCorrect = answer.equals(getAnswer());
        return isCorrect ? Answer.CORRECT : Answer.WRONG;
    }
}
