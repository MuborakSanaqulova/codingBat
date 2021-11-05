package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);

    boolean existsByNameAndCategoryIdAndIdNot(String name, Integer category_id, Integer id);

}
