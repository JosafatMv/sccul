package sccul.com.sccul.models.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sccul.com.sccul.models.course.Course;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<List<Section>> findByCourse(Course course);

    boolean existsById(Long id);
    boolean existsByName(String name);


}
