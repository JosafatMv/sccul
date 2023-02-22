package sccul.com.sccul.models.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findById(Long id);

    Optional<Course> findByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);

    boolean existsByNameAndIdNot(String name, Long id);

    @Modifying
    @Query(
            value = "UPDATE courses SET discount = :discount WHERE id = :id",nativeQuery = true
    )
    int updateDiscountById(@Param("discount") double discount, @Param("id") Long id);
}
