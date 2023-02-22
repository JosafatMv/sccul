package sccul.com.sccul.models.section;

import org.springframework.data.jpa.repository.JpaRepository;
import sccul.com.sccul.models.course.Course;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<List<Section>> findByCourse(Long id);

}
