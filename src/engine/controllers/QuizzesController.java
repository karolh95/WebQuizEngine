package engine.controllers;

import engine.models.Answer;
import engine.models.Quiz;
import engine.models.UserAnswer;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {

    private final QuizService quizService;

    @PostMapping
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetails user) {
        return quizService.addQuiz(user, quiz);
    }

    @GetMapping("{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("{id}/solve")
    public Answer solveQuiz(@PathVariable int id, @RequestBody UserAnswer userAnswer) {
        return quizService.solve(id, userAnswer.getAnswer());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@AuthenticationPrincipal UserDetails user, @PathVariable int id) {
        quizService.deleteQuiz(user, id);
    }
}
