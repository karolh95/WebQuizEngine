package engine.controllers;

import engine.models.Answer;
import engine.models.CompletedQuiz;
import engine.models.Quiz;
import engine.models.UserAnswer;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getQuizzes(page);
    }

    @GetMapping("my")
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page, @AuthenticationPrincipal UserDetails userDetails){
        return quizService.getQuizzes(page, userDetails.getUsername());
    }

    @GetMapping("completed")
    public Page<CompletedQuiz> getCompletedQuizzes(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(defaultValue = "0") int page) {
        return quizService.getCompletedQuizzes(userDetails, page);
    }

    @PostMapping("{id}/solve")
    public Answer solveQuiz(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id, @RequestBody UserAnswer userAnswer) {
        return quizService.solve(id, userDetails, userAnswer.getAnswer());
    }

    @PutMapping("{id}")
    public Quiz updateQuiz(@PathVariable int id, @AuthenticationPrincipal UserDetails user, @RequestBody Quiz quiz) {

        return quizService.updateQuiz(id, user, quiz);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@AuthenticationPrincipal UserDetails user, @PathVariable int id) {
        quizService.deleteQuiz(user, id);
    }
}
