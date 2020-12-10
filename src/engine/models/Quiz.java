package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public class Quiz {

    @NotEmpty
    private final String title;
    @NotEmpty
    private final String text;
    @NotNull
    @Size(min = 2)
    private final String[] options;
    @JsonProperty(access = Access.WRITE_ONLY)
    private final int[] answer;
    @Setter
    private int id;

    public Answer testAnswer(int[] answer) {

        boolean isCorrect = Arrays.equals(this.answer, answer);
        return isCorrect ? Answer.CORRECT : Answer.WRONG;
    }
}
