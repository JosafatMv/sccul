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
    private Long id;

    @Column(name = "card_number", nullable = false, length = 16, unique = true, columnDefinition = "bigint")
    private String cardnumber;

    @Column(name = "owner_name", nullable = false, length = 255)
    private String ownername;

    @Column(name = "card_expiration", nullable = false, columnDefinition = "date")
    private String cardexpiration;

    @Column(name = "card_cvv", nullable = false, length = 3, columnDefinition = "int")
    private String cardcvv;
    
    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
