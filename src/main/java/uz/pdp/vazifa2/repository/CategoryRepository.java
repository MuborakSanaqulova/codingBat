package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByNameAndLanguageId(String name, Integer language_id);

    boolean existsByNameAndLanguageIdAndIdNot(String name, Integer language_id, Integer id);

}
