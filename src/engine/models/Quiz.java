package engine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue
    @JsonProperty(access = Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Title should not be empty.")
    private String title;
    @NotEmpty(message = "Text should not be empty.")
    private String text;
    @NotNull(message = "Options should not be empty.")
    @Size(min = 2, message = "Options should contain at least 2 elements.")
    @ElementCollection
    private List<String> options;
    @ElementCollection
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Answer testAnswer(List<Integer> answer) {

        boolean isCorrect = answer.equals(getAnswer());
        return isCorrect ? Answer.CORRECT : Answer.WRONG;
    }
}
