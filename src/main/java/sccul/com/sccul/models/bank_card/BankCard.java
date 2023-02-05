package sccul.com.sccul.models.bank_card;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.user.User;

@Entity
@Table(name = "bank_cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bank_card_id;

    @Column(name = "card_number", nullable = false, length = 16, unique = true, columnDefinition = "bigint")
    private String card_number;

    @Column(name = "owner_name", nullable = false, length = 255)
    private String owner_name;

    @Column(name = "card_expiration", nullable = false, columnDefinition = "date")
    private String card_expiration;

    @Column(name = "card_cvv", nullable = false, length = 3, columnDefinition = "int")
    private String card_cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
