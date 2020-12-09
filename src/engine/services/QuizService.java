package engine.services;

import engine.models.Quiz;
import org.springframework.stereotype.Service;

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

}
