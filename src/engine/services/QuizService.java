package engine.services;

import engine.exceptions.QuizNotFoundException;
import engine.models.Quiz;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeMap;

@Service
public class QuizService {

    private final TreeMap<Integer, Quiz> quizzes = new TreeMap<>();
    private int currentId;

    public Quiz addQuiz(Quiz quiz) {

        quiz.setId(currentId);
        quizzes.put(currentId, quiz);
        currentId++;

        return quiz;
    }

    public Quiz getQuiz(int id) {

        return Optional.ofNullable(quizzes.get(id)).orElseThrow(QuizNotFoundException::new);
    }

    public Quiz[] getAllQuizzes() {

        return quizzes.values().toArray(Quiz[]::new);
    }
}
