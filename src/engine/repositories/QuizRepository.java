package engine.repositories;

import engine.models.Quiz;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
