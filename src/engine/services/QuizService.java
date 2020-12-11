package engine.services;

import engine.exceptions.QuizNotFoundException;
import engine.models.Answer;
import engine.models.Quiz;
import engine.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository repository;

    public Quiz addQuiz(Quiz quiz) {

        if (quiz.getAnswer() == null) {
            quiz.setAnswer(Collections.emptyList());
        }

        return repository.save(quiz);
    }

    public Quiz getQuiz(int id) {

        return repository.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    public List<Quiz> getAllQuizzes() {

        return repository.findAll();
    }

    public Answer solve(int id, List<Integer> answer) {
        return getQuiz(id).testAnswer(answer);
    }
}
