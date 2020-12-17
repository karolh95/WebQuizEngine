package engine.repositories;

import engine.models.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizzesRepository extends JpaRepository<CompletedQuiz, Long> {

    Page<CompletedQuiz> findAllByUserUsername(String username, Pageable pageable);
}
