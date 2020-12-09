package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Quiz {

    private final String title;
    private final String text;
    private final String[] options;
    @JsonProperty(access = Access.WRITE_ONLY)
    private final int answer;
    @Setter
    private int id;
}
