package engine.controllers;

import engine.models.Quiz;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {

    private final QuizService quizService;

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }


}
