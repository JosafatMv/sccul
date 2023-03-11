package sccul.com.sccul.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    @Modifying
    @Query(
            value = "UPDATE categories SET status = :status WHERE id = :id",nativeQuery = true
    )
    int updateStatusById(@Param("status") int status, @Param("id") Long id);


}
