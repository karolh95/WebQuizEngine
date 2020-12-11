package engine.services;

import engine.exceptions.QuizNotFoundException;
import engine.models.Answer;
import engine.models.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class QuizService {

    private final TreeMap<Integer, Quiz> quizzes = new TreeMap<>();
    private int currentId;

    public Quiz addQuiz(Quiz quiz) {

        if (quiz.getAnswer() == null) {
            quiz = new Quiz(quiz.getTitle(), quiz.getText(), quiz.getOptions(), Collections.emptyList());
        }
        quiz.setId(currentId);
        quizzes.put(currentId, quiz);
        currentId++;

        return quiz;
    }

    public Quiz getQuiz(int id) {

        return Optional.ofNullable(quizzes.get(id)).orElseThrow(QuizNotFoundException::new);
    }

    public List<Quiz> getAllQuizzes() {

        return new ArrayList<Quiz>(quizzes.values());
    }

    public Answer solve(int id, List<Integer> answer) {
        return getQuiz(id).testAnswer(answer);
    }
}
