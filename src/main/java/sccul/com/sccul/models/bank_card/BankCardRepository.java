package sccul.com.sccul.models.bank_card;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankCardRepository extends JpaRepository<BankCard,Long> {
    Optional<BankCard> findById(Long id);
    Optional<BankCard> findByCardNumber(String card_number);
    Optional<List<BankCard>> findByOwnerName(String owner_name);
    boolean existsByCardNumber(String card_number);

    boolean existsByCardNumberAndIdNot(String card_number, Long id);
    boolean existsByOwnerNameAndIdNot(String owner_name, Long id);

    @Modifying
    @Query(
        value = "UPDATE bank_cards SET status = :status WHERE id = :id",nativeQuery = true
    )
    int updateStatusById(@Param("status") boolean status, @Param("id") Long id);

}
