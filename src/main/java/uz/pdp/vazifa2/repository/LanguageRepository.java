package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
