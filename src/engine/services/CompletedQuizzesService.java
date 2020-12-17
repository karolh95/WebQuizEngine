package engine.services;

import engine.models.CompletedQuiz;
import engine.models.Quiz;
import engine.models.User;
import engine.repositories.CompletedQuizzesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CompletedQuizzesService {

    private final static int DEFAULT_PAGE_SIZE = 10;

    private final CompletedQuizzesRepository repository;

    public void completeQuiz(User user, Quiz quiz) {

        CompletedQuiz completedQuiz = new CompletedQuiz();

        completedQuiz.setUser(user);
        completedQuiz.setQuiz(quiz);
        completedQuiz.setCompletedAt(new Date());
        repository.save(completedQuiz);
    }

    public Page<CompletedQuiz> getCompletedQuizzesByUsername(String username, int page) {

        Sort sortByCompletedAt = Sort.by("completedAt").descending();
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, sortByCompletedAt);
        return repository.findAllByUserUsername(username, pageable);

    }
}
