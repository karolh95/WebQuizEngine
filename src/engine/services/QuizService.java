package engine.services;

import engine.exceptions.QuizNotFoundException;
import engine.exceptions.Unauthorized;
import engine.models.Answer;
import engine.models.CompletedQuiz;
import engine.models.Quiz;
import engine.models.User;
import engine.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final static int DEFAULT_PAGE_SIZE = 10;

    private final QuizRepository repository;
    private final UserService service;
    private final CompletedQuizzesService completedQuizzesService;

    public Quiz addQuiz(UserDetails userDetails, Quiz quiz) {

        User user = service.getUserByUsername(userDetails.getUsername());

        if (quiz.getAnswer() == null) {
            quiz.setAnswer(Collections.emptyList());
        }

        quiz.setUser(user);

        return repository.save(quiz);
    }

    public Quiz getQuiz(int id) {

        return repository.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    public Page<Quiz> getQuizzes(int page) {

        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        return repository.findAll(pageable);
    }

    public Page<CompletedQuiz> getCompletedQuizzes(UserDetails userDetails, int page) {

        return completedQuizzesService.getCompletedQuizzesByUsername(userDetails.getUsername(), page);
    }

    public Answer solve(int id, UserDetails userDetails, List<Integer> userAnswer) {

        Quiz quiz = getQuiz(id);
        Answer answer = quiz.testAnswer(userAnswer);
        if (answer == Answer.CORRECT) {
            User user = service.getUserByUsername(userDetails.getUsername());
            completedQuizzesService.completeQuiz(user, quiz);
        }
        return answer;
    }

    public void deleteQuiz(UserDetails userDetails, int id) {

        Quiz quiz = getQuiz(id);
        if (userDetailsEquals(userDetails, quiz.getUser())) {
            repository.delete(quiz);
        } else {
            throw new Unauthorized();
        }
    }

    public Quiz updateQuiz(UserDetails userDetails, Quiz quiz) {

        Quiz savedQuiz = getQuiz(quiz.getId());
        if (userDetailsEquals(userDetails, savedQuiz.getUser())) {
            return repository.save(quiz);
        } else {
            throw new Unauthorized();
        }
    }

    private boolean userDetailsEquals(UserDetails userDetails, User user) {
        return userDetails.getUsername().equals(user.getUsername());
    }
}
