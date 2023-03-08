package sccul.com.sccul.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import sccul.com.sccul.models.bank_card.BankCard;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "lastname", length = 45)
    private String lastname;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @Column(name = "role", nullable = false, length = 30, columnDefinition = "varchar(30) default 'user' ", insertable = false, updatable = false)
    private String role;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1", insertable = false, updatable = false)
    private Boolean status;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BankCard> bankCards;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Score> scores;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Inscription> inscriptions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Comment> comments;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private UserAnswer userAnswer;

}
