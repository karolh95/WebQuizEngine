package engine;

import engine.answers.Answer;
import engine.answers.CorrectAnswer;
import engine.answers.WrongAnswer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quiz")
public class SimpleQuizController {

    private static final String TITLE = "The Java Logo";
    private static final String TEXT = "What is depicted on the Java logo?";
    private static final String[] OPTIONS = {
            "Robot", "Tea leaf", "Cup of coffee", "Bug"
    };
    private static final int CORRECT = 2;

    private final Quiz quiz = new Quiz(TITLE, TEXT, OPTIONS);
    private final Answer correctAnswer = new CorrectAnswer();
    private final Answer wrongAnswer = new WrongAnswer();

    @GetMapping
    public Quiz getQuiz() {

        return quiz;
    }

    @PostMapping
    public Answer solveQuiz(int answer) {
        return answer == CORRECT ? correctAnswer : wrongAnswer;
    }
}
