package engine.controllers;

import engine.models.Answer;
import engine.models.Quiz;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {

    private final QuizService quizService;

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping
    public Quiz[] getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("{id}/solve")
    public Answer solveQuiz(@PathVariable int id, @RequestBody Map<String, int[]> body) {
        return quizService.solve(id, body.get("answer"));
    }
}
