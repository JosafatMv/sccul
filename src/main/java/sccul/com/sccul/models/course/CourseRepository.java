package sccul.com.sccul.models.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean findById(long id);
    Optional<Course> findByName(String name);
}
