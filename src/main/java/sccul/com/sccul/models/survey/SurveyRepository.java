package sccul.com.sccul.models.survey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>{
    boolean existsById(Long id);
}
