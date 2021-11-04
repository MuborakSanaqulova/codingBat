package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    boolean existsByText(String text);
    boolean existsByTextAndIdNot(String text, Integer id);

}
