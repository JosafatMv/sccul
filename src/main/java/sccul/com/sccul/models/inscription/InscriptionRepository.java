package sccul.com.sccul.models.inscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InscriptionRepository extends JpaRepository<Inscription, Long>{
    boolean existsByUserIdAndCourseIdAndStatus(Long userId, Long courseId, String status);

    boolean existsByIdAndStatus(Long id, String status);

    @Modifying
    @Query(
            value = "UPDATE inscriptions SET status = 'comprado' WHERE id = :id",
            nativeQuery = true
    )
    void changeStatus(@Param("id") Long id);

    @Modifying
    @Query(
            value = "UPDATE inscriptions SET full_percentage = :newPercentage WHERE id = :id",
            nativeQuery = true
    )
    void changePercentage(@Param("id") Long id, @Param("newPercentage") Double newPercentage);
}
